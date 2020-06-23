package data_access_layer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import models.Comment;
import models.User;
import models.Video;
import models.enums.Audience;

public class VideoInformationReader {

	public static List<Video> readAllVideos() throws SAXException, IOException, ParserConfigurationException, ParseException {
		JSONParser jsonParser = new JSONParser();
		List<User> userList = UserInformationReader.readAllUsers();
		try (FileReader reader = new FileReader("videos.json")) {
			Object videosObject = jsonParser.parse(reader);
			JSONArray videos = (JSONArray) videosObject;
			List<Video> videoList = new ArrayList<>();
			for (int i = 0; i < videos.size(); i++) {
				JSONObject videoObject = (JSONObject) videos.get(i);
				String title = (String) videoObject.get("title");
				String contentInformation = (String) videoObject.get("contentInformation");
				Audience intendedAudience = parseAudience(videoObject.get("intendedAudience"));
				Date date = parseDate((String) videoObject.get("date"));
				Long numberOfLikes = (Long) videoObject.get("likeAmount");
				Long numberOfDislikes = (Long) videoObject.get("dislikeAmount");
				JSONArray comments = (JSONArray) videoObject.get("comments");
				List<Comment> commentList = new ArrayList<>();
				for (int j = 0; j < comments.size(); j++) {
					JSONObject commentObject = (JSONObject) comments.get(j);
					String username = (String) commentObject.get("username");
					User user = findUserByUsername(username, userList);
					String content = (String) commentObject.get("comment");
					Date commentDate = parseDate((String) commentObject.get("date"));
					commentList.add(new Comment(user, content, commentDate));
				}
				videoList.add(new Video(title, contentInformation, intendedAudience, date, numberOfLikes,
						numberOfDislikes, commentList));
			}
			return videoList;

		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find the videos.json file. Please make sure it is located in the project folder.");
		}
		return new ArrayList<>();
	}

	private static Audience parseAudience(Object object) {
		Audience intendedAudience = null;
		try {
			intendedAudience = Audience.valueOf((String) object);
		} catch (IllegalArgumentException e) {
			System.out.println(
					"Could not read the intended audience data from the videos.json file. Please use the enumarations that are in the Audience class.");
		}
		return intendedAudience;
	}

	private static Date parseDate(String dateString) {
		DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		Date date = null;
		try {
			date = simpleDateFormat.parse(dateString);
		} catch (java.text.ParseException e) {
			System.out.println("Couldn't parse the date given");
		}
		return date;
	}

	private static User findUserByUsername(String username, List<User> userList) {
		for (User user : userList) {
			if (username.equals(user.getUsername())) {
				return user;
			}
		}
		return null;
	}
}
