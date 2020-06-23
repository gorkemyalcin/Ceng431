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
import views.VideoListView;

public class WatchListController implements ActionListener {

	private User user;
	private VideoListView videoListView;
	private Video video;
	private List<User> allUsers;

	public WatchListController(Video video, User user, VideoListView videoListView, List<User> allUsers) {
		this.user = user;
		this.videoListView = videoListView;
		this.video = video;
		this.allUsers = allUsers;
		user.addObserver(videoListView);
	}

	//If the user has the video in his/her watchlist, removes the video, otherwise adds it to the watchlist.
	//Updates the users.xml file.
	@Override
	public void actionPerformed(ActionEvent e) {
		if (user.getWatchList().contains(video)) {
			user.removeFromWatchlist(video);
		} else {
			user.addToWatchList(video);
		}
		try {
			UserInformationWriter.writeUsersXMLToFile(allUsers);
		} catch (ParserConfigurationException | TransformerException e1) {
			String message = "Error occured while writing to the to users.xml file.";
			System.out.println(message);
			new ErrorFrame(message);
		}
	}

	public VideoListView getVideoListView() {
		return videoListView;
	}
}
