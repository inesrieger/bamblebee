import java.util.ArrayList;
import java.util.List;

public class CacheServer {
	int capacity = 0;
	int id = 0;
	
	ArrayList<Video> videos = new ArrayList<Video>();
	
	public CacheServer(int id, int capacity){
		this.id = id;
		this.capacity = capacity;
	}
	
	public void insertVideo (Video video){
		videos.add(video);
	}
}
