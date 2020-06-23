package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

//Model class, holds the data about a user.
public class User extends Observable {

	private String username;
	private String password;
	private List<User> followedUsers;
	private List<Video> watchList;
	private List<Video> likedVideos;
	private List<Video> dislikedVideos;
	private List<User> followerUsers;

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.followedUsers = new ArrayList<>();
		this.followerUsers = new ArrayList<>();
		this.watchList = null;
		this.likedVideos = new ArrayList<>();
		this.dislikedVideos = new ArrayList<>();
	}

	public User(String username, String password, List<User> followedUsers, List<User> followerUsers,
			List<Video> watchList, List<Video> likedVideos, List<Video> dislikedVideos) {
		super();
		this.username = username;
		this.password = password;
		this.followedUsers = followedUsers;
		this.followerUsers = followerUsers;
		this.watchList = watchList != null ? watchList : null;
		this.likedVideos = likedVideos;
		this.dislikedVideos = dislikedVideos;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getJSONUsername() {
		JSONObject usernameJSON = new JSONObject();
		usernameJSON.put("username", username);
		return usernameJSON;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getJSON() {
		JSONObject user = new JSONObject();
		user.put("username", username);
		user.put("password", password);
		JSONArray followedUsersJSON = new JSONArray();
		for (User followedUser : followedUsers) {
			followedUsersJSON.add(followedUser.getJSONUsername());
		}
		JSONArray watchListJSON = new JSONArray();
		for (Video watchListVideo : watchList) {
			watchListJSON.add(watchListVideo.getJSONWithoutComments());
		}
		JSONArray likedVideosJSON = new JSONArray();
		for (Video likedVideo : likedVideos) {
			likedVideosJSON.add(likedVideo.getJSONWithoutComments());
		}
		JSONArray dislikedVideosJSON = new JSONArray();
		for (Video dislikedVideo : dislikedVideos) {
			dislikedVideosJSON.add(dislikedVideo.getJSONWithoutComments());
		}
		user.put("followedUsers", followedUsersJSON);
		user.put("watchList", watchListJSON);
		user.put("likedVideos", likedVideosJSON);
		user.put("dislikedVideos", dislikedVideosJSON);
		return user;
	}

	public void setUser(User loggedUser) {
		this.username = loggedUser.getUsername();
		this.password = loggedUser.getPassword();
		this.followedUsers = loggedUser.getFollowedUsers();
		this.watchList = loggedUser.getWatchList();
		this.likedVideos = loggedUser.getLikedVideos();
		this.dislikedVideos = loggedUser.getDislikedVideos();
		setChanged();
		notifyObservers();
	}

	public void createWatchList() {
		this.setWatchList(new ArrayList<>());
		setChanged();
		notifyObservers();
	}

	public void addToWatchList(Video video) {
		if (watchList != null) {
			watchList.add(video);
			setChanged();
			notifyObservers();
		}
	}

	public void removeFromWatchlist(Video video) {
		if (watchList != null && watchList.contains(video)) {
			watchList.remove(video);
			setChanged();
			notifyObservers();
		}
	}

	@Override
	public String toString() {
		return "Username: " + username;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public List<User> getFollowedUsers() {
		return followedUsers;
	}

	public List<Video> getWatchList() {
		return watchList;
	}

	public void setWatchList(List<Video> watchList) {
		this.watchList = watchList;
		setChanged();
		notifyObservers();
	}

	public List<Video> getLikedVideos() {
		return likedVideos;
	}

	public List<Video> getDislikedVideos() {
		return dislikedVideos;
	}

	public void removeFromLikedList(Video video) {
		if (likedVideos.contains(video)) {
			likedVideos.remove(video);
		}
	}

	public void addToLikedList(Video video) {
		if (!likedVideos.contains(video)) {
			likedVideos.add(video);
		}
	}

	public void dislike(Video video) {
		video.setDislikeAmount(video.getDislikeAmount() + 1);
		video.setLikeAmount(video.getLikeAmount() - 1);
		removeFromLikedList(video);
		addToDislikedList(video);
		setChanged();
		notifyObservers();
	}

	public void like(Video video) {
		video.setDislikeAmount(video.getDislikeAmount() - 1);
		video.setLikeAmount(video.getLikeAmount() + 1);
		addToLikedList(video);
		removeFromDislikedList(video);
		setChanged();
		notifyObservers();
	}

	public void removeFromDislikedList(Video video) {
		if (dislikedVideos.contains(video)) {
			dislikedVideos.remove(video);
		}
	}

	public void addToDislikedList(Video video) {
		if (!dislikedVideos.contains(video)) {
			dislikedVideos.add(video);
		}
	}

	public void removeFollowedUser(User unfollowedUser) {
		if (followedUsers.contains(unfollowedUser)) {
			followedUsers.remove(unfollowedUser);
			setChanged();
			notifyObservers();
		}
	}

	public void addToFolloweds(User followedUser) {
		if (!followedUsers.contains(followedUser)) {
			followedUsers.add(followedUser);
			setChanged();
			notifyObservers();
		}
	}

	public String getUserInformationHTML() {
		return new StringBuilder("<html>").append("Username: ").append(getUsername()).append("<br/>").toString();
	}

	public boolean isFollowing(User user) {
		for (User followedUser : followedUsers) {
			if (followedUser.equals(user)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}

	public List<User> getFollowerUsers() {
		return followerUsers;
	}

	public boolean isLiked(Video video) {
		for (Video likedVideo : likedVideos) {
			if (likedVideo.equals(video)) {
				return true;
			}
		}
		return false;
	}

	public boolean isWatchlisted(Video displayedVideo) {
		return watchList.contains(displayedVideo);
	}

}
