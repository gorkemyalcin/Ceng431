package views;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.LoginController;

public class LoginView extends JFrame {

	private static final long serialVersionUID = -339275828071152215L;

	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel errorLabel;

	//A small view class that holds the fields for logging in to the system.
	public LoginView() {
		super("Login");
		setLayout(new BorderLayout());
		JPanel parent = new JPanel();
		JLabel username = new JLabel("Username");
		usernameField = new JTextField(10);
		JLabel password = new JLabel("Password");
		passwordField = new JPasswordField(10);
		JButton loginButton = new JButton("Login");
		errorLabel = new JLabel();
		loginButton.addActionListener(new LoginController(this, null, errorLabel));
		parent.add(username);
		parent.add(usernameField);
		parent.add(password);
		parent.add(passwordField);
		parent.add(loginButton);
		parent.add(errorLabel);
		add(parent);
		setSize(400, 200);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JTextField getUsernameField() {
		return usernameField;
	}

	public void setUsernameField(JTextField usernameField) {
		this.usernameField = usernameField;
	}
}
