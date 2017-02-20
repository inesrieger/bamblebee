package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PizzaSlicer {
	private ArrayList<Slice> slices;
	ArrayList<Cell> availableCells = new ArrayList<Cell>();
	ArrayList<Cell> unavailableCells;
	int rowNumber;
	int columnNumber;
	int minimumIngredients;
	int maximumSliceSize;
	char[][] cellList;
	Cell[][] pizza;

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
			rowNumber = Integer.parseInt(tokens[0]);
			columnNumber = Integer.parseInt(tokens[1]);
			minimumIngredients = Integer.parseInt(tokens[2]);
			maximumSliceSize = Integer.parseInt(tokens[3]);
			cellList = new char[rowNumber][columnNumber];
			pizza = new Cell[rowNumber][columnNumber];

			// line = reader.readLine();
			while (line != null) {
				for (int numberOfRow = 0; numberOfRow < rowNumber; numberOfRow++) {
					line = reader.readLine();
					char[] charInLine = line.toCharArray();
					for (int numberOfColumn = 0; numberOfColumn < columnNumber; numberOfColumn++) {
						cellList[numberOfRow][numberOfColumn] = charInLine[numberOfColumn];
						for (int i = 0; i < 1000; i++) {
							Cell cellToAdd = new Cell(charInLine[i], numberOfRow, numberOfColumn);
							availableCells.add(cellToAdd);
							pizza[numberOfRow][numberOfColumn] = cellToAdd;
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
		while (!availableCells.isEmpty()) {
			findPossibleSlice();
		}
	}

	public void findPossibleSlice() {
		Cell pointer = availableCells.get(0);
		int pointerRow = pointer.getRowPosition();
		int pointerColumn = pointer.getColumnPosition();
		ArrayList<Cell> newCells = new ArrayList<Cell>();
		boolean checkedRightSide = false;
		boolean checkedDownSide = false;
		newCells.add(pointer);

		if (pointerColumn <= columnNumber) {
			while ((newCells.size() < maximumSliceSize) && (!checkIfSliceContainsAllIngredients(newCells))
					&& !checkedRightSide) {
				ArrayList<Cell> rightExpandedCells = expandRight(newCells, pointer);
				if (!rightExpandedCells.isEmpty()) {
					newCells.addAll(rightExpandedCells);
				} else {
					checkedRightSide = true;
				}
			}
			while ((newCells.size() < maximumSliceSize) && (!checkIfSliceContainsAllIngredients(newCells))
					&& !checkedDownSide) {
				ArrayList<Cell> downExpandedCells = expandDown(newCells, pointer);
				if (!downExpandedCells.isEmpty()) {
					newCells.addAll(downExpandedCells);
				} else {
					checkedDownSide = true;
					availableCells.remove(0);
					return;
				}
			}
		}
		addSliceToListAndMarkCellsAsUnavailable(newCells);
		return;
	}

	private ArrayList<Cell> expandRight(ArrayList<Cell> newCells, Cell pointer) {
		int highestColNumber = 0;
		ArrayList<Cell> candidateCells = new ArrayList<Cell>();

		for (Cell cell : newCells) {
			if (cell.getColumnPosition() > highestColNumber) {
				highestColNumber = cell.getColumnPosition();
			}
		}
		for (Cell cell : newCells) {
			if (cell.getColumnPosition() == highestColNumber) {
				Cell cellToAdd = pizza[cell.getRowPosition()][cell.getColumnPosition() + 1];
				if (availableCells.contains(cellToAdd)) {
					candidateCells.add(cellToAdd);
				} else {
					return new ArrayList<Cell>();
				}
			}
		}

		return candidateCells;
	}

	private ArrayList<Cell> expandDown(ArrayList<Cell> newCells, Cell pointer) {
		int highestRowNumber = 0;
		ArrayList<Cell> candidateCells = new ArrayList<Cell>();

		for (Cell cell : newCells) {
			if (cell.getRowPosition() > highestRowNumber) {
				highestRowNumber = cell.getRowPosition();
			}
		}
		for (Cell cell : newCells) {
			if (cell.getColumnPosition() == highestRowNumber) {
				Cell cellToAdd = pizza[cell.getRowPosition() + 1][cell.getColumnPosition()];
				if (availableCells.contains(cellToAdd)) {
					candidateCells.add(cellToAdd);
				} else {
					return new ArrayList<Cell>();
				}
			}
		}

		return candidateCells;
	}

	private void addSliceToListAndMarkCellsAsUnavailable(ArrayList<Cell> newCells) {
		Slice foundSlice = new Slice(newCells);
		slices.add(foundSlice);
		for (Cell candidate : newCells) {
			deleteCellsFromCellsNotTaken(candidate);
		}
	}

	private boolean checkIfSliceContainsAllIngredients(ArrayList<Cell> newCells) {
		int counterMushrooms = 0;
		int counterTomatoes = 0;
		for (Cell cell : newCells) {
			if (cell.getContent() == 'T') {
				counterTomatoes++;
			} else {
				counterMushrooms++;
			}
		}
		if (counterTomatoes >= minimumIngredients && counterMushrooms >= minimumIngredients) {
			return true;
		}
		return false;
	}

	public void writeOutput() {
		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("textfile.txt")));

			writer.write(slices.size());
			System.out.println(slices.size() + "\n");

			writer.newLine();
			for (Slice slice : slices) {
				writer.write(slice.toString());
				writer.newLine();
				System.out.println(slice.toString() + "\n");
			}

			writer.close();

		} catch (IOException ex) {

			ex.printStackTrace();

		}

	}

}
