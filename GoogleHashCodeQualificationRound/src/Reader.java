import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {
	String fileName;
	int numberOfVideos = 0;
	int numberOfEndpoints = 0;
	int numberRequestDescriptions = 0;
	int numberOfCaches = 0;
	int sizeOfEachCache = 0;
	ArrayList<Video> allVideos = new ArrayList<>();
	ArrayList<Endpoint> allEndpoints = new ArrayList<>();
	ArrayList<Request> allRequests = new ArrayList<>();

	public Reader(String fileName) {
		this.fileName = fileName;
	}

	public void readFile() throws Exception {
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
			while (line.equals("")) {
				line = reader.readLine();
			}

			tokens = line.split(" ");
			int[] sizesOfVideos = new int[numberOfVideos];
			for (int i = 0; i < tokens.length; i++) {
				sizesOfVideos[i] = Integer.parseInt(tokens[i]);
				Video video = new Video(sizesOfVideos[i], i);
				allVideos.add(video);
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
					System.out.println(Integer.parseInt(tokens[0]));

					CacheServer cacheServer = new CacheServer(Integer.parseInt(tokens[0]), sizeOfEachCache);
					endpoint.cacheServers[i1] = cacheServer;
					endpoint.latencyPerCacheServer[i1] = Integer.parseInt(tokens[1]);
				}
				allEndpoints.add(endpoint);
			}

			// Lines for Requests
			while (line != null) {
				line = reader.readLine();
				tokens = line.split(" ");
				int videoId = Integer.parseInt(tokens[0]);
				int endpointId = Integer.parseInt(tokens[1]);
				Request request = new Request(allVideos.get(videoId), allEndpoints.get(endpointId),
						Integer.parseInt(tokens[2]));
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
