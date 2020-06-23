package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JTextArea;

import data_access_layer.VideoInformationWriter;
import models.Comment;
import models.User;
import models.Video;
import views.VideoListView;

public class AddCommentController implements ActionListener {
	private User user;
	private VideoListView videoListView;
	private JTextArea commentField;
	private Video video;
	private List<Video> allVideos;

	public AddCommentController(JTextArea commentField, User user, VideoListView videoListView, Video video, List<Video> allVideos) {
		this.user = user;
		this.videoListView = videoListView;
		this.commentField = commentField;
		this.video = video;
		this.allVideos = allVideos;
		video.addObserver(videoListView);
	}

	//Creates and adds a comment to the given video.
	//Updates the videos.json file after the adding operation.
	@Override
	public void actionPerformed(ActionEvent e) {
		Comment commentToBeAdded = new Comment(user, commentField.getText(), new Date());
		video.addComment(commentToBeAdded);
		VideoInformationWriter.writeVideosToFile(allVideos);
	}

	public VideoListView getVideoListView() {
		return videoListView;
	}

}
