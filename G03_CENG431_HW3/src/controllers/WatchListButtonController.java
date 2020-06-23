package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import data_access_layer.UserInformationWriter;
import models.User;
import models.Video;
import views.ErrorFrame;
import views.UserView;
import views.VideoListView;

public class WatchListButtonController implements ActionListener {

	private UserView userView;
	private User user;
	private List<User> users;
	private List<Video> allVideos;

	public WatchListButtonController(UserView userView, User user, List<User> users, List<Video> allVideos) {
		this.userView = userView;
		this.user = user;
		this.users = users;
		this.allVideos = allVideos;
	}

	//If the user has a watchlist, the button shows "Go to your watchlist" and clicking that will result in user seeing his/her watchlist.
	//If the user doesn't have a watchlist, button shows "Create a watchlist" and clicking that will result in user creating his own watchlist.
	//Updates the users.xml file.
	@Override
	public void actionPerformed(ActionEvent e) {
		user.addObserver(userView);
		if (user.getWatchList() != null) {
			new VideoListView(user.getWatchList(), user.getUsername() + "'s watchlist", user, users, allVideos);
		} else {
			user.createWatchList();
			try {
				UserInformationWriter.writeUsersXMLToFile(users);
			} catch (ParserConfigurationException | TransformerException e1) {
				String message = "Error occured while writing to the to users.xml file.";
				System.out.println(message);
				new ErrorFrame(message);
			}
		}
	}
}
