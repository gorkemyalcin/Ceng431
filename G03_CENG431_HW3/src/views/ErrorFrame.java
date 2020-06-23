package views;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ErrorFrame extends JFrame {

	private static final long serialVersionUID = -7912190104084535037L;
	
	private JLabel errorLabel;

	//An error pop-up. Used when there is an error to be displayed to the user.
	public ErrorFrame(String message) {
		super("Error");
		setLayout(new BorderLayout());
		JPanel parent = new JPanel();
		errorLabel = new JLabel(message);
		parent.add(errorLabel);
		add(parent);
		setSize(600, 100);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
