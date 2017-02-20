package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PizzaSlicer {
	private ArrayList<Slice> slices;
	ArrayList<Cell> availableCells = new ArrayList<Cell>();
	ArrayList<Cell> unavailableCells;

	public PizzaSlicer(String string) throws Exception {
		createCellsFromInputFile(string);
	}

	public void deleteCellsFromCellsNotTaken(Cell takenCell) {
		for (Cell candidate : availableCells) {
			if (takenCell.equals(candidate)) {
				availableCells.remove(candidate);
				return;
			}
		}
	}

	public char[][] createCellsFromInputFile(String fileName) throws Exception {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

			String line = reader.readLine();
			String[] tokens = line.split(" ");
			int rowNumber = Integer.parseInt(tokens[0]);
			int columnNumber = Integer.parseInt(tokens[1]);
			int L = Integer.parseInt(tokens[2]);
			int H = Integer.parseInt(tokens[3]);
			char[][] cellList = new char[rowNumber][columnNumber];

			// line = reader.readLine();
			while (line != null) {
				for (int numberOfRow = 0; numberOfRow < rowNumber; numberOfRow++) {
					line = reader.readLine();
					char[] charInLine = line.toCharArray();
					for (int numberOfColumn = 0; numberOfColumn < columnNumber; numberOfColumn++) {
						cellList[numberOfRow][numberOfColumn] = charInLine[numberOfColumn];
						for (int i = 0; i < 1000; i++) {
							availableCells.add(new Cell(charInLine[i], numberOfRow, numberOfColumn));
						}
						// System.out.println("Spalte eingelesen:
						// "+numberOfColumn);
					}
					// System.out.println("Zeile eingelesen: "+numberOfRow);
				}
				line = reader.readLine();
			}
			return cellList;
		} catch (IOException e) {
			throw new IOException("The document couldn't be read.");
		}
	}

	public void dividePizza() {
		while (findPossibleSlice()) {

		}
	}

	public boolean findPossibleSlice(Cell pointer) {

		Slice possibleSlice = null;

		if (!possibleSlice.getCells().isEmpty()) {
			for (Cell candidate : possibleSlice.getCells()) {
				deleteCellsFromCellsNotTaken(candidate);
			}
		}

		slices.add(possibleSlice);
		return true;

	}

	public void writeOutput() {
		// TODO Auto-generated method stub

	}

}
