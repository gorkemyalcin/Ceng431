package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import data_access_layer.UserInformationWriter;
import models.User;
import views.ErrorFrame;
import views.UserListView;

public class FollowUserController implements ActionListener {

	private User user;
	private User followedUser;
	private UserListView userListView;
	private List<User> allUsers;

	public FollowUserController(User user, User followedUser, UserListView userListView, List<User> allUsers) {
		this.user = user;
		this.followedUser = followedUser;
		this.userListView = userListView;
		this.allUsers = allUsers;
		user.addObserver(userListView);

	}
	
	//If user is following the other user, removes the follow. If the user is not following the other user, follows the other user.
	//The reason that this function works without confusing the user is that, in the view portion, button is displayed accordingly to the users followers list.
	//If the user is not following the displayed user, it shows "follow", if the user is following the displayed user, it shows "unfollow".
	//Updates the users.xml file after the update.
	@Override
	public void actionPerformed(ActionEvent e) {
		if (user.isFollowing(followedUser)) {
			user.removeFollowedUser(followedUser);
		} else {
			user.addToFolloweds(followedUser);
		}
		try {
			UserInformationWriter.writeUsersXMLToFile(allUsers);
		} catch (ParserConfigurationException | TransformerException e1) {
			String message = "Error occured while writing to the to users.xml file.";
			System.out.println(message);
			new ErrorFrame(message);
		}
	}

	public UserListView getUserListView() {
		return userListView;
	}
}
