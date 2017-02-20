package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class HelloWorld {
	
	static String fileName = "recources/big.in";
	
	protected static char[][] readTxt(String fileName) throws Exception {
		try (BufferedReader reader = new BufferedReader(new FileReader("resources/big.in"))) {
			
			String line = reader.readLine();
			String[] tokens = line.split(" ");
			int rowNumber = Integer.parseInt(tokens[1]);
			int columnNumber = Integer.parseInt(tokens[2]);
			char[][] cellList = new char[rowNumber][columnNumber];
			
			line = reader.readLine();
			while (line != null) {
				
			
				for(int numberOfRow = 0; numberOfRow < rowNumber; numberOfRow++){
					line = reader.readLine();
					char[] charInLine = line.toCharArray();
					
					for(int numberOfColumn = 0; numberOfColumn < columnNumber; numberOfColumn++ ){
						cellList[numberOfRow][numberOfColumn] = charInLine[numberOfColumn];
						
					}
					System.out.println(numberOfRow);
				}
				line = reader.readLine();
			}
			return cellList;
		} catch (IOException e) {
			throw new IOException("The document couldn't be read.");
		}
	}
	
	
	
	
	public static void main(String[] args) {
		try {
			readTxt(fileName);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
}
