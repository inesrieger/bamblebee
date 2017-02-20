package main;

public class Cell {
	char charInLine;
	int columnPosition;
	int rowPosition;
	boolean isPartOfASlice;

	public boolean isPartOfASlice() {
		return isPartOfASlice;
	}

	public void setPartOfASlice(boolean isPartOfASlice) {
		this.isPartOfASlice = isPartOfASlice;
	}

	public Cell(char charInLine, int numberOfRow, int numberOfColumn) {
		this.charInLine = charInLine;
		this.columnPosition = numberOfColumn;
		this.rowPosition = numberOfRow;
		this.isPartOfASlice = false;
	}

	public char getCharInLine() {
		return charInLine;
	}

	public void setCharInLine(char charInLine) {
		this.charInLine = charInLine;
	}

	public int getColumnPosition() {
		return columnPosition;
	}

	public void setColumnPosition(int columnPosition) {
		this.columnPosition = columnPosition;
	}

	public int getRowPosition() {
		return rowPosition;
	}

	public void setRowPosition(int rowPosition) {
		this.rowPosition = rowPosition;
	}
}
