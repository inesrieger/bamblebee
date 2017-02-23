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
		this.fileName = fileName;
	}

	public int readFile() throws Exception {
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
			int idOfEndpoint = 0;
			for (int i = 0; i < numberOfEndpoints; i++) {
				// 3. line
				line = reader.readLine();
				tokens = line.split(" ");
				int dataCenterLatencyInMs = Integer.parseInt(tokens[0]);
				int numberOfConnectedCaches = Integer.parseInt(tokens[1]);
				Endpoint endpoint = new Endpoint(idOfEndpoint, dataCenterLatencyInMs, numberOfConnectedCaches);
				idOfEndpoint++;

				// 4. line and onwards
				for (int i1 = 0; i1 < numberOfCaches; i1++) {
					tokens = line.split(" ");
					CacheServer cacheServer = new CacheServer(Integer.parseInt(tokens[0]), sizeOfEachCache);
					endpoint.latencyPerCacheServer[i1] = Integer.parseInt(tokens[1]);
				}
			}

			// Lines for Requests
			while (line != null) {
				line = reader.readLine();
				tokens = line.split(" ");
				Video video = new Video(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[0]));
				// videoNumber = tokens[0];
				// fromEndpoint = tokens[1];
				// numberOfRequests = tokens[2];
			}
			line = reader.readLine();

		} catch (IOException e) {
			throw new IOException("The document couldn't be read.");
		}
	}
}
