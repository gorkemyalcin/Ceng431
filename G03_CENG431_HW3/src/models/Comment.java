package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;

//Model class, holds the data about a comment.
public class Comment {

	private User user;
	private String content;
	private Date date;

	private static final String DATE_FORMAT = "yyyy-mm-dd hh:mm:ss";

	public Comment() {
		super();
	}

	public Comment(User user, String content, Date date) {
		super();
		this.user = user;
		this.content = content;
		this.date = date;
	}

	public String getCommentHTML() {
		return "<html> User: " + user + ", Comment: " + content + ", Date: "
				+ new SimpleDateFormat(DATE_FORMAT).format(date) + "\n<html>";
	}

	@Override
	public String toString() {
		return user + ", Comment: " + content + ", Date: " + new SimpleDateFormat(DATE_FORMAT).format(date)
				+ "\n<html>";
	}

	@SuppressWarnings("unchecked")
	public JSONObject getJSON() {
		JSONObject comment = new JSONObject();
		String username = user != null ? user.getUsername() : "Deleted user";
		comment.put("username", username);
		comment.put("comment", content);
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		comment.put("date", dateFormat.format(date));
		return comment;
	}

	public User getUser() {
		return user;
	}

	public String getContent() {
		return content;
	}

	public Date getDate() {
		return date;
	}
}
