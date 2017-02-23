import java.util.ArrayList;

public class CacheServer {
	int capacity = 0;
	int id = 0;

	ArrayList<Video> videos = new ArrayList<Video>();

	public CacheServer(int id, int capacity) {
		this.id = id;
		this.capacity = capacity;
	}

	public void insertVideo(Video video) {
		if (capacity >= video.size) {
			videos.add(video);
			capacity = capacity - video.size;

		} else {
			throw new IllegalArgumentException(
					"There is not enough place in the CacheServer. CacheServer, method: insertVideo");
		}
	}
}
