import java.util.ArrayList;

public class Video {

	ArrayList<Request> requests;
	int size;
	int id;

	public Video(int size, int id) {
		this.size = size;
		this.id = id;
		this.requests = new ArrayList<Request>();
	}

	public int getSumOfRequests() {
		int sum = 0;
		for (Request request : requests) {
			sum += request.requestQuantity;
		}
		return sum;
	}

	@Override
	public boolean equals(Object obj) {
		Video cs = (Video) obj;
		return this.id == cs.id;
	}

}
