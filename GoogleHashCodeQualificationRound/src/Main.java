public class Main {

	public static void main(String[] args) throws Exception {
		Reader ourReader = new Reader("resources/kittens.in");

		Distributor distributor = ourReader.readFile();
		distributor.doTheMagic();

		Writer ourWriter = new Writer(distributor.usedCacheServers);
		ourWriter.writeOutput();

	}
}
