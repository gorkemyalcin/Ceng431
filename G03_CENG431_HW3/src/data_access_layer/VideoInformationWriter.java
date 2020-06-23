package data_access_layer;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;

import models.Video;

public class VideoInformationWriter {
	
	private static final String DATE_FORMAT = "yyyy-mm-dd hh:mm:ss";

	//Wrties the given video list to the videos.json file.
	@SuppressWarnings("unchecked")
	public static void writeVideosToFile(List<Video> videoList) {
		JSONArray videosArray = new JSONArray();
		for (Video video : videoList) {
			videosArray.add(video.getJSON());
		}
		try (FileWriter myWriter = new FileWriter("videos.json");) {
			myWriter.write(videosArray.toString());
			System.out.println(
					"Successfully exported to the videos.json" + new SimpleDateFormat(DATE_FORMAT).format(new Date()));
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file."
					+ new SimpleDateFormat(DATE_FORMAT).format(new Date()));
		}
	}
}
