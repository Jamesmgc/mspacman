package pacman.entries.ghosts;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class StateReader {
	
	private String path;
	
	public StateReader(String filePath) {
		path = filePath;
	}
	
	public String[][] openFile() throws IOException{
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		
		int numberOfLines = readlines();
		String[] textData = new String[numberOfLines];//store the text file lines
		String[][] states = new String[numberOfLines][4];//2d array to store each state piece separately 
		
		int i;
		for (i=0; i < numberOfLines; i++) {//loop through each line and split it at each ","
			textData[ i ] = br.readLine();
			
			for (int j = 0; j < 4;){
				for (String statePiece: textData[i].split(",")){
					states[i][j] = statePiece;
					//System.out.println(statePiece);
					j++;
			    }
			}
			//System.out.println("STATES AT 0: " + states[3][0]);
		}
		
		br.close( );
		return states;//textData;//returns the text file as an array of lines
	}
	
	int readlines() throws IOException {
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		
		String line;
		int noOfLines = 0;
		
		while ( ( line = br.readLine( ) ) != null ) {
			noOfLines++;
		}
		return noOfLines;
	}
}
