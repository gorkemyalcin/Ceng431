package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import data_access_layer.UserInformationWriter;
import data_access_layer.VideoInformationWriter;
import models.User;
import models.Video;
import views.ErrorFrame;
import views.VideoListView;

public class LikeVideoController implements ActionListener {
	private User user;
	private VideoListView videoListView;
	private Video video;
	private List<User> allUsers;
	private List<Video> allVideos;

	public LikeVideoController(Video video, User user, VideoListView videoListView, List<User> allUsers,
			List<Video> allVideos) {
		this.user = user;
		this.videoListView = videoListView;
		this.video = video;
		this.allUsers = allUsers;
		this.allVideos = allVideos;
		user.addObserver(videoListView);
	}
	
	//Likes the given video.
	//Updates the videos.json and users.xml files after the like operation.
	@Override
	public void actionPerformed(ActionEvent e) {
		if (user.isLiked(video)) {
			user.dislike(video);
		} else {
			user.like(video);
		}
		VideoInformationWriter.writeVideosToFile(allVideos);
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
