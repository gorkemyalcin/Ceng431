package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.FollowUserController;
import controllers.VideoListDisplayerController;
import models.User;
import models.Video;

public class UserListView extends JFrame implements Observer {

	private static final long serialVersionUID = 8648857973477718943L;

	private List<User> usersToBeListed;
	private User user;
	private String frameTitle;
	private JPanel parentComponent;
	private JLabel userLabel;
	private JFrame videoListFrame;
	private List<JButton> buttonList;

	// A user list view class that uses the usersToBeListed parameter to show
	// usersToBeListed list's users.
	// 3rd parameter is the logged in user. I needed allUsers parameter to update
	// the .xml .json files when needed.
	// This view class displays the user accordingly to their and logged in users
	// fields. For example if a displayedUser is in loggedUser's followed list, the
	// button to follow the displayedUser will say "Unfollow". If the loggedinUser
	// is not following the displayedUser, button will say "follow"..
	
	// In order to see the update from the list when removing the user from
	// loggedInUser's followerList whilist inside loggedInUser will require to close
	// and reopen the followerList. I made it this way so if a user accidentally
	// removes a user, user can reverse the mistake easily.
	public UserListView(List<User> usersToBeListed, String frameTitle, User user, List<User> allUsers,
			List<Video> allVideos) {
		this.usersToBeListed = usersToBeListed;
		this.user = user;
		setTitle(frameTitle);
		setLayout(new BorderLayout());
		parentComponent = new JPanel();
		buttonList = new ArrayList<>();
		for (User displayedUser : usersToBeListed) {
			JPanel userPanel = new JPanel();
			userPanel.setBackground(new Color(252, 231, 174));
			StringBuilder sb = new StringBuilder(displayedUser.getUserInformationHTML());

			if (displayedUser.getWatchList() != null) {
				JButton showWatchListButton = new JButton("Display" + displayedUser.getUsername() + "'s watchlist");
				showWatchListButton.addActionListener(new VideoListDisplayerController(displayedUser.getWatchList(),
						user, displayedUser.getUsername() + "'s watchlist", allUsers, allVideos));
				userPanel.add(showWatchListButton);

			} else {
				sb.append("This user does not have a watchlist.");
			}
			sb.append("</html>");
			userLabel = new JLabel(sb.toString());

			JButton followButton = new JButton();
			followButton.addActionListener(new FollowUserController(user, displayedUser, this, allUsers));
			if (user.isFollowing(displayedUser)) {
				followButton.setText("Unfollow");
				followButton.setBackground(Color.GREEN);
			} else {
				followButton.setText("Follow");
				followButton.setBackground(Color.RED);
			}
			buttonList.add(followButton);
			userPanel.add(followButton);
			userPanel.add(userLabel);
			parentComponent.add(userPanel, BorderLayout.CENTER);
		}
		add(parentComponent);
		setSize(1024, 768);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	// Whenever the loggedinUser gets updated, view class sets its user to the
	// parameter Observable in order to get the most updated model class.
	// After updating the user, it updates the buttons accordingly to the observed
	// user.
	@Override
	public void update(Observable o, Object arg) {
		this.setUser(((User) o));
		Integer count = 0;
		for (User displayedUser : usersToBeListed) {
			if (user.isFollowing(displayedUser)) {
				buttonList.get(count).setText("Unfollow");
				buttonList.get(count).setBackground(Color.GREEN);
			} else {
				buttonList.get(count).setText("Follow");
				buttonList.get(count).setBackground(Color.RED);
			}
			count++;
		}
		revalidate();
		repaint();
	}

	public List<User> getUsersToBeListed() {
		return usersToBeListed;
	}

	public void settUsersToBeListed(List<User> userList) {
		this.usersToBeListed = userList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFrameTitle() {
		return frameTitle;
	}

	public void setFrameTitle(String frameTitle) {
		this.frameTitle = frameTitle;
	}

	public JFrame getVideoListFrame() {
		return videoListFrame;
	}

	public void setVideoListFrame(JFrame videoListFrame) {
		this.videoListFrame = videoListFrame;
	}

}
