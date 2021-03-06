import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

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
	HashSet<CacheServer> allServers = new HashSet<>();

	public Reader(String fileName) {
		this.fileName = fileName;
	}

	public Distributor readFile() throws Exception {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

			String line = reader.readLine();
			String[] tokens = line.split(" ");
			numberOfVideos = Integer.parseInt(tokens[0]);
			numberOfEndpoints = Integer.parseInt(tokens[1]);
			numberRequestDescriptions = Integer.parseInt(tokens[2]);
			numberOfCaches = Integer.parseInt(tokens[3]);
			// System.out.println(numberOfCaches);
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
				Endpoint endpoint = new Endpoint(idOfEndpoint, dataCenterLatencyInMs);
				idOfEndpoint++;

				// 4. line and onwards
				for (int i1 = 0; i1 < numberOfConnectedCaches; i1++) {
					line = reader.readLine();
					tokens = line.split(" ");
					CacheServer cacheServer;
					if (findCSById(Integer.parseInt(tokens[0])) != null) {
						cacheServer = findCSById(Integer.parseInt(tokens[0]));
					} else {
						cacheServer = new CacheServer(Integer.parseInt(tokens[0]), sizeOfEachCache);
					}
					allServers.add(cacheServer);
					CacheLatencyPair cacheLatencyPair = new CacheLatencyPair(cacheServer, Integer.parseInt(tokens[1]));
					endpoint.cachesWithLatencies.add(cacheLatencyPair);
				}
				allEndpoints.add(endpoint);
			}

			// Lines for Requests
			line = reader.readLine();
			while (line != null) {

				tokens = line.split(" ");
				int videoId = Integer.parseInt(tokens[0]);
				int endpointId = Integer.parseInt(tokens[1]);
				Video currentVideo = findVideoById(videoId);
				Request request = new Request(currentVideo, allEndpoints.get(endpointId), Integer.parseInt(tokens[2]));
				currentVideo.requests.add(request);
				// videoNumber = tokens[0];
				// fromEndpoint = tokens[1];
				// numberOfRequests = tokens[2];
				line = reader.readLine();
			}

			line = reader.readLine();

		} catch (IOException e) {
			throw new IOException("The document couldn't be read.");
		}
		// for (Video v : allVideos) {
		// System.out.println("id: " + v.id);
		// }

		Distributor distributor = new Distributor(numberOfVideos, numberOfEndpoints, numberRequestDescriptions,
				numberOfCaches, sizeOfEachCache, allVideos, allEndpoints, allRequests);
		return distributor;
	}

	private CacheServer findCSById(int id) {
		CacheServer current = null;
		for (CacheServer cs : allServers) {
			if (cs.id == id) {
				current = cs;
			}
		}
		return current;
	}

	private Video findVideoById(int id) {
		Video current = null;
		for (Video cs : allVideos) {
			if (cs.id == id) {
				current = cs;
			}
		}
		return current;
	}

}
