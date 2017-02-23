import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Distributor {

	int numberOfVideos = 0;
	int numberOfEndpoints = 0;
	int numberRequestDescriptions = 0;
	int numberOfCaches = 0;
	int sizeOfEachCache = 0;
	ArrayList<Video> allVideos = new ArrayList<>();
	ArrayList<Endpoint> allEndpoints = new ArrayList<>();
	ArrayList<Request> allRequests = new ArrayList<>();
	ArrayList<CacheServer> usedCacheServers = new ArrayList<>();

	public Distributor(int numberOfVideos, int numberOfEndpoints, int numberRequestDescriptions, int numberOfCaches,
			int sizeOfEachCache, ArrayList<Video> allVideos, ArrayList<Endpoint> allEndpoints,
			ArrayList<Request> allRequests) {
		super();
		this.numberOfVideos = numberOfVideos;
		this.numberOfEndpoints = numberOfEndpoints;
		this.numberRequestDescriptions = numberRequestDescriptions;
		this.numberOfCaches = numberOfCaches;
		this.sizeOfEachCache = sizeOfEachCache;
		this.allVideos = allVideos;
		this.allEndpoints = allEndpoints;
		this.allRequests = allRequests;
	}

	public void doTheMagic() {

		orderVideosWithMostRequests();
		boolean isFirst = true;
		while (!allVideos.isEmpty()) {
			Video video = allVideos.get(0);
			Boolean videoDeleted = false;
			if (isFirst) {
				orderRequestsPerVideo(video);
				isFirst = false;
			}
			boolean isFirst2 = true;
			for (Request request : video.requests) {
				if (!videoDeleted) {
					Endpoint currentEndpoint = request.endpoint;
					if (isFirst2) {
						sortCachesOfEndpointByLatency(currentEndpoint);
						isFirst2 = false;
					}

					for (CacheLatencyPair pair : currentEndpoint.cachesWithLatencies) {
						CacheServer cacheServer;
						if (findCSById(pair.cacheServer.id) != null && !videoDeleted) {
							cacheServer = findCSById(pair.cacheServer.id);
							cacheServer.insertVideo(video);
							videoDeleted = true;
						} else if (pair.cacheServer.insertVideo(video) && !videoDeleted) {
							usedCacheServers.add(pair.cacheServer);
							// allVideos.remove(video);
							videoDeleted = true;
						}
					}
				}
			}
			allVideos.remove(video);
		}
	}

	private void sortCachesOfEndpointByLatency(Endpoint currentEndpoint) {
		Collections.sort(currentEndpoint.cachesWithLatencies, new Comparator<CacheLatencyPair>() {
			@Override
			public int compare(final CacheLatencyPair pair1, final CacheLatencyPair pair2) {
				return Integer.compare(pair2.latency, pair1.latency);
			}
		});

	}

	public void orderVideosWithMostRequests() {
		Collections.sort(allVideos, new Comparator<Video>() {
			@Override
			public int compare(final Video vid1, final Video vid2) {
				return Integer.compare(vid2.getSumOfRequests(), vid1.getSumOfRequests());
			}
		});

	}

	public void orderRequestsPerVideo(Video video) {
		Collections.sort(video.requests, new Comparator<Request>() {
			@Override
			public int compare(final Request requ1, final Request requ2) {
				return Integer.compare(requ2.requestQuantity, requ1.requestQuantity);
			}
		});
	}

	private CacheServer findCSById(int id) {
		CacheServer current = null;
		for (CacheServer cs : usedCacheServers) {
			if (cs.id == id) {
				current = cs;
			}
		}
		return current;
	}

}
