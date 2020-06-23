package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import models.enums.Audience;


//Model class, holds the data about a video.
public class Video extends Observable {

	private String title;
	private String contentInformation;
	private Audience intendedAudience;
	private Date date;
	private Long likeAmount;
	private Long dislikeAmount;
	private List<Comment> comments;
	
	private static final String NEW_LINE = "<br/>";
	private static final String DATE_FORMAT = "yyyy-mm-dd hh:mm:ss";

	public Video() {
		super();
		this.comments = new ArrayList<>();
	}

	public Video(String title, String contentInformation, Audience intendedAudience, Date date, Long likeAmount,
			Long dislikeAmount, List<Comment> comments) {
		super();
		this.title = title;
		this.contentInformation = contentInformation;
		this.intendedAudience = intendedAudience;
		this.date = date;
		this.likeAmount = likeAmount;
		this.dislikeAmount = dislikeAmount;
		this.comments = comments;
	}

	public Video(String title) {
		this.title = title;
		this.contentInformation = "";
		this.intendedAudience = null;
		this.date = new Date();
		this.likeAmount = 0l;
		this.dislikeAmount = 0l;
		this.comments = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Video [title=" + title + ", contentInformation=" + contentInformation + ", intendedAudience="
				+ intendedAudience + ", date=" + date + ", likeAmount=" + likeAmount + ", dislikeAmount="
				+ dislikeAmount + ", comments=" + comments + "]";
	}

	@SuppressWarnings("unchecked")
	public JSONObject getJSON() {
		JSONObject video = new JSONObject();
		video.put("title", title);
		video.put("contentInformation", contentInformation);
		video.put("intendedAudience", intendedAudience.toString());
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		video.put("date", dateFormat.format(date));
		video.put("likeAmount", likeAmount);
		video.put("dislikeAmount", dislikeAmount);
		JSONArray commentArray = new JSONArray();
		for (Comment comment : comments) {
			commentArray.add(comment.getJSON());
		}
		video.put("comments", commentArray);
		return video;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getJSONWithoutComments() {
		JSONObject video = new JSONObject();
		video.put("title", title);
		video.put("contentInformation", contentInformation);
		video.put("intendedAudience", intendedAudience.toString());
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		video.put("date", dateFormat.format(date));
		video.put("likeAmount", likeAmount);
		video.put("dislikeAmount", dislikeAmount);
		return video;
	}

	public String getTitle() {
		return title;
	}

	public String getContentInformation() {
		return contentInformation;
	}

	public Audience getIntendedAudience() {
		return intendedAudience;
	}

	public Date getDate() {
		return date;
	}

	public Long getLikeAmount() {
		return likeAmount;
	}

	public void setLikeAmount(Long likeAmount) {
		this.likeAmount = likeAmount;
		setChanged();
		notifyObservers();
	}

	public Long getDislikeAmount() {
		return dislikeAmount;
	}

	public void setDislikeAmount(Long dislikeAmount) {
		this.dislikeAmount = dislikeAmount;
		setChanged();
		notifyObservers();
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setVideo(Video obs) {
		this.title = obs.getTitle();
		this.contentInformation = obs.getContentInformation();
		this.intendedAudience = obs.getIntendedAudience();
		this.date = obs.getDate();
		this.likeAmount = obs.getLikeAmount();
		this.dislikeAmount = obs.getDislikeAmount();
		this.comments = obs.getComments();
	}

	public void addComment(Comment commentToBeAdded) {
		comments.add(commentToBeAdded);
		setChanged();
		notifyObservers();
	}

	public String getDisplayHTML() {
		StringBuilder sb = new StringBuilder("<html>").append("Title: ").append(getTitle()).append(NEW_LINE)
				.append("Content information: ").append(getContentInformation()).append(NEW_LINE)
				.append("Intended audience: ").append(getIntendedAudience()).append(NEW_LINE).append("Date: ")
				.append(getDate()).append(NEW_LINE).append("Likes: ").append(getLikeAmount()).append(NEW_LINE)
				.append("Dislikes: ").append(getDislikeAmount()).append(NEW_LINE).append(NEW_LINE)
				.append("Comments: "+ NEW_LINE);
		for (Comment comment : getComments()) {
			sb.append(comment.getCommentHTML());
			sb.append(NEW_LINE);
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Video other = (Video) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}

}
