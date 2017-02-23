import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
	String fileName;
	int numberOfVideos = 0;
	int numberOfEndpoints = 0;
	int numberRequestDescriptions = 0;
	int numberOfCaches = 0;
	int sizeOfEachCache = 0;

	public Reader(String fileName) {

	}

	public int readFile(String fileName) throws Exception {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

			String line = reader.readLine();
			String[] tokens = line.split(" ");
			numberOfVideos = Integer.parseInt(tokens[0]);
			numberOfEndpoints = Integer.parseInt(tokens[1]);
			numberRequestDescriptions = Integer.parseInt(tokens[2]);
			numberOfCaches = Integer.parseInt(tokens[3]);
			sizeOfEachCache = Integer.parseInt(tokens[4]);

			// 2. line
			line = reader.readLine();
			tokens = line.split(" ");
			int[] sizesOfVideos = new int[numberOfVideos];
			for (int i = 0; i < tokens.length; i++) {
				sizesOfVideos[i] = Integer.parseInt(tokens[i]);
			}

			// 3. line
			line = reader.readLine();
			tokens = line.split(" ");
			while (line != null) {
				for (int numberOfRow = 0; numberOfRow < rowNumber; numberOfRow++) {
					line = reader.readLine();
					char[] charInLine = line.toCharArray();
					// System.out.println(charInLine);
					for (int numberOfColumn = 0; numberOfColumn < columnNumber; numberOfColumn++) {

						// cellList[numberOfRow][numberOfColumn] =
						// charInLine[numberOfColumn];
						// for (int i = 0; i < rowNumber; i++) {
						Cell cellToAdd = new Cell(charInLine[numberOfColumn], numberOfRow, numberOfColumn);
						availableCells.add(cellToAdd);
						pizza[numberOfRow][numberOfColumn] = cellToAdd;
					}

				}
				line = reader.readLine();
			}
			int counter = 0;
			for (Cell c : availableCells) {

				// System.out.println(++counter + ". cell: row: " +
				// c.getRowPosition() + ", column: "
				// + c.getColumnPosition() + ", content:" + c.getContent());
			}
			return cellList;
		} catch (IOException e) {
			throw new IOException("The document couldn't be read.");
		}
	}
}
