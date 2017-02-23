public class Main {

	public static void main(String[] args) throws Exception {
		Reader ourReader = new Reader("resources/me_at_the_zoo.in");
		Distributor distributor = ourReader.readFile();
		distributor.doTheMagic();

	}
}
