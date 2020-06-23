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
import javax.swing.JTextArea;

import controllers.AddCommentController;
import controllers.FieldFocusController;
import controllers.LikeVideoController;
import controllers.WatchListController;
import models.User;
import models.Video;

public class VideoListView implements Observer {

	private List<Video> videoList;
	private User user;
	private String frameTitle;
	private JPanel parent;
	private List<JLabel> videoInformationList;
	private List<JButton> likeButtonList;
	private List<JButton> watchListButtonList;
	private JFrame frame;

	// A VideoListView class that uses the videoList parameter to show
	// videoList list's videos.
	// 3rd parameter is the logged in user. I needed allUsers and allVideos
	// parameters to update
	// the .xml .json files when needed.
	// This view class displays the videos accordingly to their and logged in users
	// fields. For example if a video is in loggedUser's watch list, the
	// button to add the video to the watchlist will say "Remove from watchlist". If
	// the loggedinUser
	// does not have the video in his/her watchlist, button will say "Add to the
	// watchlist"..

	// In order to see the update from the list when removing the video from
	// loggedInUser's watchlist whilist inside loggedInUser will require to close
	// and reopen the watchlist. I made it this way so if a user accidentally
	// removes a video, user can reverse the mistake easily.
	public VideoListView(List<Video> videoList, String frameTitle, User user, List<User> allUsers,
			List<Video> allVideos) {
		frame = new JFrame(frameTitle);
		this.videoList = videoList;
		this.user = user;
		frame.setLayout(new BorderLayout());
		parent = new JPanel();
		videoInformationList = new ArrayList<>();
		likeButtonList = new ArrayList<>();
		watchListButtonList = new ArrayList<>();
		for (Video video : videoList) {
			JPanel videoPanel = new JPanel();
			videoPanel.setBackground(new Color(252, 231, 174));
			JLabel videoContent = new JLabel(video.getDisplayHTML());
			videoInformationList.add(videoContent);

			// Watchlist
			JButton modifyWatchListButton = new JButton();
			modifyWatchListButton.addActionListener(new WatchListController(video, user, this, allUsers));
			if (user.getWatchList() != null) {
				if (!user.isWatchlisted(video)) {
					modifyWatchListButton.setText("Add to watch list.");
					modifyWatchListButton.setBackground(Color.RED);
				} else {
					modifyWatchListButton.setText("Remove from watch list.");
					modifyWatchListButton.setBackground(Color.GREEN);
				}
				videoPanel.add(modifyWatchListButton);
				watchListButtonList.add(modifyWatchListButton);
			}

			// Like
			JButton likeButton = new JButton();
			likeButton.addActionListener(new LikeVideoController(video, user, this, allUsers, allVideos));
			likeButtonList.add(likeButton);
			if (user.isLiked(video)) {
				likeButton.setText("Dislike");
				likeButton.setBackground(Color.GREEN);
			} else {
				likeButton.setText("Like");
				likeButton.setBackground(Color.RED);
			}
			videoPanel.add(likeButton);

			JTextArea commentField = new JTextArea(1, 6);
			videoPanel.add(commentField);

			commentField.setText("Comment");
			commentField.addFocusListener(new FieldFocusController(commentField));
			JButton addCommentButton = new JButton("Add comment");
			addCommentButton.addActionListener(new AddCommentController(commentField, user, this, video, allVideos));

			videoPanel.add(addCommentButton);
			videoPanel.add(videoContent);

			parent.add(videoPanel, BorderLayout.CENTER);
		}
		frame.add(parent);
		frame.setSize(1024, 768);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public JPanel getParent() {
		return parent;
	}

	public List<Video> getVideoList() {
		return videoList;
	}

	public String getFrameTitle() {
		return frameTitle;
	}

	public void setFrameTitle(String frameTitle) {
		this.frameTitle = frameTitle;
	}

	// Whenever the loggedinUser gets updated, view class sets its user to the
	// parameter Observable in order to get the most updated model class.
	// After updating the user, it updates the buttons and the video information
	// accordingly to the observed
	// user and observed video.
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof Video) {
			Integer count = 0;
			for (Video video : videoList) {
				if (video.equals((Video) arg0)) {
					video.setVideo((Video) arg0);
					videoInformationList.get(count).setText(video.getDisplayHTML());
				}
				count++;
			}
		} else {
			Integer count = 0;
			this.setUser((User) arg0);
			for (Video displayedVideo : videoList) {
				if (user.isLiked(displayedVideo)) {
					likeButtonList.get(count).setBackground(Color.GREEN);
					likeButtonList.get(count).setText("Dislike");
				} else {
					likeButtonList.get(count).setBackground(Color.RED);
					likeButtonList.get(count).setText("Like");
				}
				if (!user.isWatchlisted(displayedVideo)) {
					watchListButtonList.get(count).setText("Add to watch list.");
					watchListButtonList.get(count).setBackground(Color.RED);
				} else {
					watchListButtonList.get(count).setText("Remove from watch list.");
					watchListButtonList.get(count).setBackground(Color.GREEN);
				}
				count++;
			}
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
