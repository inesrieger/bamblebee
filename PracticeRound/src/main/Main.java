package main;

public class Main {
	public static void main(String[] args) throws Exception {
		// try {
		PizzaSlicer pizzaSlicer = new PizzaSlicer("resources/big.in");
		pizzaSlicer.dividePizza();
		pizzaSlicer.writeOutput();
		// char[][] cellList = pizzaSlicer.readTxt("resources/big.in");
		// int zaehler = 1;
		// for (int i = 0; i < 1000; i++) {
		// System.out.println("\n" + zaehler++ + ". Zeile:");
		// for (int b = 0; b < 1000; b++) {
		//
		// System.out.print(cellList[i][b]);
		// }
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}
}
