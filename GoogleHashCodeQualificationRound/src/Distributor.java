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
		int i = 0;
		int deleted = 0;
		while (!allVideos.isEmpty() && i < allVideos.size()) {
			Video video = allVideos.get(i);
			Boolean videoDeleted = false;
			orderRequestsPerVideo(video);
			for (Request request : video.requests) {
				if (!videoDeleted) {
					Endpoint currentEndpoint = request.endpoint;
					// System.out.println("Video: " + video.id + ", Total
					// requests:
					// " + video.getSumOfRequests()
					// + ", Request: " + request.requestQuantity + ", Endpoint:
					// " +
					// currentEndpoint.endpointID);
					sortCachesOfEndpointByLatency(currentEndpoint);
					for (CacheLatencyPair pair : currentEndpoint.cachesWithLatencies) {
						if (pair.cacheServer.insertVideo(video)) {
							usedCacheServers.add(pair.cacheServer);
							// allVideos.remove(video);
							videoDeleted = true;
							break;
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

}
