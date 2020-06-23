package data_access_layer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import models.User;
import models.Video;

public class UserInformationWriter {

	private static final String VIDEO = "video";
	private static final String TITLE = "title";
	private static final String DATE_FORMAT = "yyyy-mm-dd hh:mm:ss";

	
	// Creates an xml file and writes to the xml file accordingly to the list given as a parameter.
	public static void writeUsersXMLToFile(List<User> users) throws ParserConfigurationException, TransformerException {
		String usersXML = null;
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		Element usersRoot = doc.createElement("users");
		doc.appendChild(usersRoot);
		for (User user : users) {

			Element personRootElement = doc.createElement("rootUser");
			usersRoot.appendChild(personRootElement);

			Element usernameElement = doc.createElement("username");
			usernameElement.appendChild(doc.createTextNode(user.getUsername()));
			personRootElement.appendChild(usernameElement);

			Element passwordElement = doc.createElement("password");
			passwordElement.appendChild(doc.createTextNode(user.getPassword()));
			personRootElement.appendChild(passwordElement);

			Element followedUsersRootElement = doc.createElement("followedUsers");
			for (User followedUser : user.getFollowedUsers()) {
				addUserToTheRootElement(followedUsersRootElement, followedUser, doc);
			}
			Element followerUsersRootElement = doc.createElement("followerUsers");
			for (User followerUser : user.getFollowerUsers()) {
				addUserToTheRootElement(followerUsersRootElement, followerUser, doc);
			}

			if (user.getWatchList() != null) {
				Element watchListElement = doc.createElement("watchList");
				addVideosToTheRootElement(user.getWatchList(), doc, watchListElement);
				personRootElement.appendChild(watchListElement);
			}

			if (user.getLikedVideos() != null) {
				Element likedVideosElement = doc.createElement("likedVideos");
				addVideosToTheRootElement(user.getLikedVideos(), doc, likedVideosElement);
				personRootElement.appendChild(likedVideosElement);
			}
			if (user.getDislikedVideos() != null) {
				Element dislikedVideosElement = doc.createElement("dislikedVideos");
				addVideosToTheRootElement(user.getDislikedVideos(), doc, dislikedVideosElement);
				personRootElement.appendChild(dislikedVideosElement);
			}

			personRootElement.appendChild(followedUsersRootElement);
		}

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(doc), new StreamResult(writer));
		usersXML = writer.getBuffer().toString();
		try (FileWriter myWriter = new FileWriter("users.xml");) {
			myWriter.write(usersXML);
			System.out.println(
					"Successfully exported to the users.xml" + new SimpleDateFormat(DATE_FORMAT).format(new Date()));
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file."
					+ new SimpleDateFormat(DATE_FORMAT).format(new Date()));
		}
	}

	private static void addVideosToTheRootElement(List<Video> watchList, Document doc, Element watchListElement) {
		for (Video watchListVideo : watchList) {
			Element videoElement = doc.createElement(VIDEO);
			watchListElement.appendChild(videoElement);

			Element videoTitle = doc.createElement(TITLE);
			videoTitle.appendChild(doc.createTextNode(watchListVideo.getTitle()));
			videoElement.appendChild(videoTitle);

			watchListElement.appendChild(videoElement);
		}
	}

	private static void addUserToTheRootElement(Element followedUsersRootElement, User followedUser, Document doc) {
		Element followedUserElement = doc.createElement("user");
		followedUsersRootElement.appendChild(followedUserElement);

		Element followedUserUsernameElement = doc.createElement("username");
		followedUserUsernameElement.appendChild(doc.createTextNode(followedUser.getUsername()));
		followedUserElement.appendChild(followedUserUsernameElement);

		Element followedUserPasswordElement = doc.createElement("password");
		followedUserPasswordElement.appendChild(doc.createTextNode(followedUser.getPassword()));
		followedUserElement.appendChild(followedUserPasswordElement);

		followedUsersRootElement.appendChild(followedUserElement);
	}
}
