package controllers;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;

public class FieldFocusController implements FocusListener {

	private JTextArea commentField;

	//This controller is used only for creating a placeholder for the JTextArea object I used in the adding comments to the video section.
	public FieldFocusController(JTextArea commentField) {
		this.commentField = commentField;
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (commentField.getText().equals("Comment")) {
			commentField.setText("");
			commentField.setForeground(Color.BLACK);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (commentField.getText().isEmpty()) {
			commentField.setForeground(Color.GRAY);
			commentField.setText("Comment");
		}
	}

}
