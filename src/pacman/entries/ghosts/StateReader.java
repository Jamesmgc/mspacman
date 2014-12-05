package pacman.entries.ghosts;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class StateReader {
	
	private String path;
	String[][] states;
	
	public StateReader(String filePath) {
		path = filePath;
	}
	
	//opens the stateMachine file and stores the states in a 2D array which is returned
	public String[][] openFile() throws IOException{
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		
		int numberOfLines = readlines();
		String[] textData = new String[numberOfLines];//store the text file lines

		states = new String[numberOfLines][4];//2d array to store each state piece separately 
		
		int i;
		for (i=0; i < numberOfLines; i++) {//loop through each line and split it at each ","
			textData[ i ] = br.readLine();
			
			for (int j = 0; j < 4;){
				for (String statePiece: textData[i].split(",")){
					states[i][j] = statePiece;
					j++;
			    }
			}
		}
		br.close();
		return states;
	}
	
	//gets the number of lines in the file
	int readlines() throws IOException {
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		
		String line;
		int noOfLines = 0;
		
		while ( ( line = br.readLine( ) ) != null ) {
			noOfLines++;
		}
		br.close();
		return noOfLines;
	}
	
	//uses passed in values to find what state to move to next
	String getNextState(String[][] stateTable, String currState, String currEvent ) throws IOException{
		String newState = "";
		int numberOfLines = readlines();
		
		//go through each line and each element
		for (int i = 0; i < numberOfLines; i++) {
			//System.out.println("INFO IS: " + stateTable[i][0] + "_____" + currState);
			if (stateTable[i][0].equals(currState)  && stateTable[i][1].equals(currEvent)) {
				newState = stateTable[i][3];
				break;
			}
		}
		
		return newState;
	}
}
