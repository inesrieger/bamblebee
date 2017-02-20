package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main {
	
	static String fileName = "recources/big.in";
	
	protected static char[][] readTxt(String fileName) throws Exception {
		try (BufferedReader reader = new BufferedReader(new FileReader("resources/big.in"))) {
			
			String line = reader.readLine();
			String[] tokens = line.split(" ");
			int rowNumber = Integer.parseInt(tokens[0]);
			int columnNumber = Integer.parseInt(tokens[1]);
			int L = Integer.parseInt(tokens[2]);
			int H = Integer.parseInt(tokens[3]);
			char[][] cellList = new char[rowNumber][columnNumber];
			

			
			//line = reader.readLine();
			while (line != null) {
				
				for(int numberOfRow = 0; numberOfRow < rowNumber; numberOfRow++){
					
					line = reader.readLine();
					char[] charInLine = line.toCharArray();
					for(int numberOfColumn = 0; numberOfColumn < columnNumber; numberOfColumn++ ){
						cellList[numberOfRow][numberOfColumn] = charInLine[numberOfColumn];
						//System.out.println("Spalte eingelesen: "+numberOfColumn);
					}
					//System.out.println("Zeile eingelesen: "+numberOfRow);
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
			char[][] cellList = readTxt(fileName);
			int zaehler = 1;
			for(int i = 0;i<1000;i++){
				System.out.println("\n"+zaehler++ + ". Zeile:");
				for(int b = 0;b<1000;b++){
				
					System.out.print(cellList[i][b]);
					}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
}
