
public class Request {
	
	Video video;
	Endpoint endpoint;
	int requestQuantity;
	
	public Request (Video video, Endpoint endpoint, int requestQuantity){
		this.video = video;
		this.endpoint = endpoint;
		this.requestQuantity = requestQuantity;
	}

}
