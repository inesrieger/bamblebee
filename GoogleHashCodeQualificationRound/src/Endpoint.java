
public class Endpoint {
	public int endpointID;
	public int latencyDataCenter;
	public CacheServer[] cacheServers;
	public int[] latencyPerCacheServer;

	public Endpoint(int endpointID, int latencyDataCenter, int numberOfCaches) {
		super();
		this.endpointID = endpointID;
		this.latencyDataCenter = latencyDataCenter;
		this.cacheServers = new CacheServer[numberOfCaches];
		this.latencyPerCacheServer = new int[numberOfCaches];
	}

}
