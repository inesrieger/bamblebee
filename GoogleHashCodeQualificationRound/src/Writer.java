import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {
	int numberOfCacheServers;
	String videosOfCacheServer = "";
	ArrayList<CacheServer> cacheServers;

	public Writer(ArrayList<CacheServer> cacheServers) {
		this.numberOfCacheServers = cacheServers.size();
	}

	public void writeOutput() {

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("textfile.txt")));

			writer.write(numberOfCacheServers + "\n");
			for (CacheServer cs : cacheServers) {
				String videosOfCacheServer = "";
				if (!cs.videos.isEmpty()) {
					for (Video video : cs.videos) {
						videosOfCacheServer = +video.id + " ";
					}
				} else if (cs.videos.isEmpty()) {
					videosOfCacheServer = " 0";
				}
				writer.write(cs.id + " " + videosOfCacheServer);

			}

			writer.newLine();

			writer.close();

		} catch (IOException ex) {

			ex.printStackTrace();

		}

	}
}
