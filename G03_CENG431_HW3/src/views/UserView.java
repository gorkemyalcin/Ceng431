package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import controllers.LogoutController;
import controllers.UserDisplayerController;
import controllers.VideoListDisplayerController;
import controllers.WatchListButtonController;
import models.User;
import models.Video;

public class UserView extends JFrame implements Observer {

	private static final long serialVersionUID = 7992596218573685555L;
	private User user;
	private JLabel display;
	private JPanel userButtonsPanel;
	private JButton seeAllUsersButton;
	private JButton seeAllVideosButton;
	private JButton watchListButton;
	private JButton seeFollowedsButton;
	private JButton logoutButton;

	// This class holds the data and buttons related to the user. Basically a user
	// profile page.
	// Only different thing is that if the user does not have a watchlist, here user
	// can create one, if the user has already got a watchlist, using the same
	// button(with different text of course), user can see his/her watchlist. 
	// These actions are done by the Observer design pattern.
	public UserView(User user, List<User> allUsers, List<Video> allVideos) {
		super(user.getUsername());
		this.user = user;
		user.addObserver(this);
		display = new JLabel(user.getUsername() + "'s profile", SwingConstants.CENTER);
		display.setBackground(new Color(252, 231, 174));
		add(display, BorderLayout.CENTER);
		userButtonsPanel = new JPanel();
		userButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		seeAllUsersButton = new JButton("See all the users");
		seeAllUsersButton
				.addActionListener(new UserDisplayerController(allUsers, "All users", user, allUsers, allVideos));
		seeAllVideosButton = new JButton("See all the videos");
		seeAllVideosButton.addActionListener(
				new VideoListDisplayerController(allVideos, user, "All videos", allUsers, allVideos));
		seeFollowedsButton = new JButton("See your followed users");
		seeFollowedsButton.addActionListener(new UserDisplayerController(user.getFollowedUsers(),
				user.getUsername() + "'s followers", user, allUsers, allVideos));
		userButtonsPanel.add(seeFollowedsButton);
		userButtonsPanel.add(seeAllUsersButton);
		userButtonsPanel.add(seeAllVideosButton);
		watchListButton = new JButton();
		watchListButton.addActionListener(new WatchListButtonController(this, user, allUsers, allVideos));
		if (user.getWatchList() != null) {
			watchListButton.setText("Go to your watch list.");
		} else {
			watchListButton.setText(
					"<html>Create a watch list<br/>If you do not have a watchlist, you will not have the option to add videos to your watchlist</html>");
		}
		userButtonsPanel.add(watchListButton);

		logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new LogoutController(this));
		userButtonsPanel.add(logoutButton);
		add("South", userButtonsPanel);
		setSize(1280, 960);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}

	//Updates the watchListButton text accordingly to the updated and observed user.
	@Override
	public void update(Observable obs, Object arg1) {
		this.setUser(((User) obs));
		if (user.getWatchList() != null) {
			watchListButton.setText("Go to your watch list.");
		} else {
			watchListButton.setText(
					"<html>Create a watch list<br/>If you do not have a watchlist, you will not have the option to add videos to your watchlist</html>");
		}
		revalidate();
		repaint();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
