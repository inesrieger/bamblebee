import java.util.ArrayList;

public class Endpoint {
	public int endpointID;
	public int latencyDataCenter;
	public ArrayList<CacheLatencyPair> cachesWithLatencies;

	public Endpoint(int endpointID, int latencyDataCenter) {
		super();
		this.endpointID = endpointID;
		this.latencyDataCenter = latencyDataCenter;
		this.cachesWithLatencies = new ArrayList<CacheLatencyPair>();
	}

}
