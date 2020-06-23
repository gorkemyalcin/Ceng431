package data_access_layer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import models.User;
import models.Video;

//user.xml reader class.
public class UserInformationReader {

	// Reads and parses the xml file accordingly and returns a user list as a
	// result.
	public static List<User> readAllUsers() throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		builder = factory.newDocumentBuilder();

		if (builder != null) {
			Document document = null;
			document = builder.parse(new File("users.xml"));
			if (document != null) {
				document.getDocumentElement().normalize();
				NodeList nList = document.getElementsByTagName("rootUser");
				List<User> users = new ArrayList<>();

				for (int i = 0; i < nList.getLength(); i++) {
					Node nNode = nList.item(i);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element elem = (Element) nNode;

						Node usernameNode = elem.getElementsByTagName("username").item(0);
						String username = usernameNode.getTextContent();

						Node passwordNode = elem.getElementsByTagName("password").item(0);
						String password = passwordNode.getTextContent();

						List<User> followeds = getUserList(elem, "followedUsers");
						List<User> followers = getUserList(elem, "followerUsers");
						List<Video> watchlist = getVideoList(elem, "watchList").equals(new ArrayList<>()) ? null
								: new ArrayList<>();
						List<Video> likedVideoList = getVideoList(elem, "likedVideos");
						List<Video> dislikedVideoList = getVideoList(elem, "dislikedVideos");

						users.add(new User(username, password, followeds, followers, watchlist, likedVideoList,
								dislikedVideoList));

					}
				}
				return users;
			}
		}
		return new ArrayList<>();
	}

	// Generically reads and parses the elements in the xml file accordingly to the
	// tagname of the element and returns a user list as a result.
	private static List<User> getUserList(Element element, String tagName) {
		Node usersNode = element.getElementsByTagName(tagName).item(0);
		NodeList users = usersNode != null ? usersNode.getChildNodes() : null;
		List<User> userList = new ArrayList<>();
		if (users != null) {
			for (int k = 0; k < users.getLength(); k++) {
				Node user = users.item(k);
				Element userElement = (Element) user;
				userList.add(new User(userElement.getElementsByTagName("username").item(0).getTextContent(),
						userElement.getElementsByTagName("password").item(0).getTextContent()));
			}
		}
		return userList;

	}

	// Generically reads and parses the elements in the xml file accordingly to the
	// tagname of the element and returns a video list as a result.
	private static List<Video> getVideoList(Element element, String tagName) {
		Node videosNode = element.getElementsByTagName(tagName).item(0);
		NodeList videos = videosNode != null ? videosNode.getChildNodes() : null;
		List<Video> videoList = new ArrayList<>();
		if (videos != null) {
			for (int k = 0; k < videos.getLength(); k++) {
				Node video = videos.item(k);
				Element videoElement = (Element) video;
				videoList.add(new Video(videoElement.getElementsByTagName("title").item(0).getTextContent()));
			}
		}
		return videoList;
	}
}
