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
	
	public String[] openFile() throws IOException{
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		
		int numberOfLines = 3;
		String[ ] textData = new String[numberOfLines];
		
		int i;

		for (i=0; i < numberOfLines; i++) {
			textData[ i ] = br.readLine();
			//textData[ i ].split(",");
		}
		
		br.close( );
		return textData;//returns the text file as an array of lines
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
