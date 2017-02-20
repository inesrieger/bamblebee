package main;

import java.util.ArrayList;

public class Slice {

	int topLeftRow;
	int topLeftColumn;
	int bottomRightRow;
	int bottomRightColumn;

	ArrayList<Cell> cells;

	public Slice(ArrayList<Cell> cells) {
		this.cells = cells;
		setCoordinates(cells);
	}

	// TODO: muss am Ende vor Rueckgabe nochmal aufgerufen werden, da sich in
	// der Zwischenzeit was geaendert haben koennte
	public void setCoordinates(ArrayList<Cell> cells) {

		int minimumLeft = Integer.MAX_VALUE;
		int minimumTop = Integer.MAX_VALUE;
		int maximumRight = 0;
		int maximumBottom = 0;
		for (Cell c : cells) {
			int column = c.getColumnPosition();
			int row = c.getRowPosition();
			if (column < minimumLeft) {
				minimumLeft = column;
				// System.out.println(minimumLeft);
			}
			if (column > maximumRight) {
				maximumRight = column;
			}
			if (row < minimumTop) {
				minimumTop = row;
			}
			if (row > maximumBottom) {
				maximumBottom = row;
			}

		}
		topLeftRow = minimumTop;
		topLeftColumn = minimumLeft;
		bottomRightRow = maximumBottom;
		bottomRightColumn = maximumRight;

	}

	public ArrayList<Cell> getCells() {
		return cells;
	}

	public void setCells(ArrayList<Cell> cells) {
		this.cells = cells;
	}

	public String toString() {
		return topLeftRow + " " + topLeftColumn + " " + bottomRightRow + " " + bottomRightColumn;
	}
}
