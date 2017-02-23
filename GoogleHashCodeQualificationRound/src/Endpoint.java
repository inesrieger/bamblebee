
public class Endpoint {
	public int endpointID;
	public int latencyDataCenter;
	public CacheServer[] cacheServers;
	public int[] latencyPerCacheServer;

	public Endpoint(int endpointID, int latencyDataCenter) {
		super();
		this.endpointID = endpointID;
		this.latencyDataCenter = latencyDataCenter;
		this.cacheServers = cacheServers;
		this.latencyPerCacheServer = latencyPerCacheServer;
	}

}
