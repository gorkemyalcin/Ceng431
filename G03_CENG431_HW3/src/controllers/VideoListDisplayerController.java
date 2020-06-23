package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import models.User;
import models.Video;
import views.VideoListView;

public class VideoListDisplayerController implements ActionListener {

	private List<Video> videos;
	private User user;
	private String frameTitle;
	private List<User> allUsers;
	private List<Video> allVideos;

	public VideoListDisplayerController(List<Video> videos, User user, String frameTitle, List<User> allUsers,
			List<Video> allVideos) {
		this.videos = videos;
		this.user = user;
		this.frameTitle = frameTitle;
		this.allUsers = allUsers;
		this.allVideos = allVideos;
	}
	
	//Depending on the videos, creates a VideoListView.
	@Override
	public void actionPerformed(ActionEvent arg0) {
		new VideoListView(videos, frameTitle, user, allUsers, allVideos);
	}

}