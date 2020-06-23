package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JLabel;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import data_access_layer.UserInformationReader;
import data_access_layer.VideoInformationReader;
import models.User;
import models.Video;
import views.ErrorFrame;
import views.LoginView;
import views.UserView;

public class LoginController implements ActionListener {

	private LoginView loginView;
	private User user;
	private JLabel errorLabel;

	public LoginController(LoginView loginView, User user, JLabel errorLabel) {
		this.loginView = loginView;
		this.user = user;
		this.errorLabel = errorLabel;
	}

	//A controller that gets the string username-password data from the LoginView class
	//Then it gets the user and video data from reading the users.xml and videos.json files.
	//Then if there is a user with these credentials, creates a UserView (shows the user profile) and disposes the login view.
	@Override
	public void actionPerformed(ActionEvent e) {
		String username = loginView.getUsernameField().getText();
		String password = new String(loginView.getPasswordField().getPassword());
		List<User> allUsers = null;
		List<Video> allVideos = null;
		try {
			allUsers = UserInformationReader.readAllUsers();
			allVideos = VideoInformationReader.readAllVideos();
			User loggedUser = loginUser(username, password, allUsers);
			if (loggedUser != null) {
				loginView.dispose();
				new UserView(loggedUser, allUsers, allVideos);
			} else {
				errorLabel.setText(
						"<html>Text color: <font color='red'>" + "Username or password is wrong." + " </font></html>");
			}
		} catch (SAXException | IOException e1) {
			loginView.dispose();
			String message = "<html>Error occured while reading users.xml or videos.json files.<br/> Please make sure that the files exist and their format is properly designed.</html>";
			System.out.println(message);
			new ErrorFrame(message);
		} catch (ParserConfigurationException e1) {
			String message = "Error occured when parsing the users.xml file.";
			System.out.println(message);
			new ErrorFrame(message);
		} catch (ParseException e1) {
			String message = "Error occured when parsing the videos.json file.";
			System.out.println(message);
			new ErrorFrame(message);
		}

	}

	private User loginUser(String username, String password, List<User> users) {
		for (User savedUser : users) {
			if (savedUser.getUsername().equals(username) && savedUser.getPassword().equals(password)) {
				return savedUser;
			}
		}
		return null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
