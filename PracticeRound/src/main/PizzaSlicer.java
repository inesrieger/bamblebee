package main;

import java.io.BufferedReader;
import java.io.FileReader;
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

	}

	public boolean findPossibleSlice() {
		Cell pointer = availableCells.get(0);
		int pointerRow = pointer.getRowPosition();
		int pointerColumn = pointer.getColumnPosition();
		ArrayList<Cell> newCells = new ArrayList<Cell>();
		boolean checkedRightSide = false;
		boolean checkedDownSide = false;
		newCells.add(pointer);
		// while (newCells.size() < maximumSliceSize) {
		if (pointerColumn <= columnNumber) {
			while ((newCells.size() >= 2 * minimumIngredients) && (checkIfSliceContainsAllIngredients(newCells))
					&& !checkedRightSide) {
				ArrayList<Cell> rightExpandedCells = expandRight(newCells, pointer);
				if (!rightExpandedCells.isEmpty()) {
					addSliceToListAndMarkCellsAsUnavailable(newCells);

				} else {
					checkedRightSide = true;
				}
			}
			while ((newCells.size() >= 2 * minimumIngredients) && (checkIfSliceContainsAllIngredients(newCells))
					&& !checkedDownSide) {
				ArrayList<Cell> downExpandedCells = expandDown(newCells, pointer);
				if (!downExpandedCells.isEmpty()) {
					addSliceToListAndMarkCellsAsUnavailable(newCells);
				} else {
					checkedDownSide = true;
					// DO SOMETHING LIKE POINTER VERSCHIEBEN
				}

			}
		}
		// }
		return false;

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
			if (cell.getRowPosition() == highestRowNumber) {
				Cell cellToAdd = pizza[cell.getRowPosition()][cell.getRowPosition() + 1];
				if (availableCells.contains(cellToAdd)) {
					candidateCells.add(cellToAdd);
				} else {
					return new ArrayList<Cell>();
				}
			}
		}

		return candidateCells;
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
		// TODO Auto-generated method stub

	}

}
