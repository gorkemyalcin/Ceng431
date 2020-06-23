package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import models.User;
import models.Video;
import views.UserListView;

public class UserDisplayerController implements ActionListener {

	private List<User> users;
	private User user;
	private String frameTitle;
	private List<User> allUsers;
	private List<Video> allVideos;

	public UserDisplayerController(List<User> users, String frameTitle, User user, List<User> allUsers, List<Video> allVideos) {
		this.users = users;
		this.user = user;
		this.frameTitle = frameTitle;
		this.allUsers = allUsers;
		this.allVideos = allVideos;
	}

	//Depending on the users, creates a UserListView.
	@Override
	public void actionPerformed(ActionEvent arg0) {
		new UserListView(users, frameTitle, user, allUsers, allVideos);
	}
}