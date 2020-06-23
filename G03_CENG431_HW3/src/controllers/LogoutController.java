package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.LoginView;
import views.UserView;

public class LogoutController implements ActionListener {

	private UserView userView;

	public LogoutController(UserView userView) {
		this.userView = userView;
	}
	
	//Disposes the user's profile view class' frame and creates a new LoginView.
	@Override
	public void actionPerformed(ActionEvent arg0) {
		userView.dispose();
		new LoginView();
	}

}
