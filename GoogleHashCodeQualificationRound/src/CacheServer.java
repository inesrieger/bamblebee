import java.util.ArrayList;

public class CacheServer {
	int capacity = 0;
	int id = 0;

	ArrayList<Video> videos = new ArrayList<Video>();

	public CacheServer(int id, int capacity) {
		this.id = id;
		this.capacity = capacity;
	}

	public boolean insertVideo(Video video) {
		if (capacity >= video.size && !this.videos.contains(video)) {
			videos.add(video);
			capacity = capacity - video.size;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean equals(Object obj) {
		CacheServer cs = (CacheServer) obj;
		return this.id == cs.id;
	}
}
