import java.util.ArrayList;

public class Distributor {
	
	int numberOfVideos = 0;
	int numberOfEndpoints = 0;
	int numberRequestDescriptions = 0;
	int numberOfCaches = 0;
	int sizeOfEachCache = 0;
	ArrayList<Video> allVideos = new ArrayList<>();
	ArrayList<Endpoint> allEndpoints = new ArrayList<>();
	ArrayList<Request> allRequests = new ArrayList<>();
	
	public Distributor(int numberOfVideos, int numberOfEndpoints, int numberRequestDescriptions,
			int numberOfCaches, int sizeOfEachCache, ArrayList<Video> allVideos, ArrayList<Endpoint> allEndpoints,
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
	
	public void doTheMagic(){
		
		
	}
	
	public void orderVideosWithMostRequests(){
		for(Video v: allVideos){
			
		}
		
	}
	

}
