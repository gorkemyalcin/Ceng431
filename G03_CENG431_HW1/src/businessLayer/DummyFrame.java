package businessLayer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This DummyFrame class is used for getting "live"(non blocking) user input from the user. When the game is running, in order to get user input from user and not stop the game,
 * I used a JFrame object to create a 0,0 sized JFrame to get the user input from the user by overriding the keyPressed method from KeyListener.
 * Only use this object has is for getting the user input.
 */
public class DummyFrame extends JFrame {
	
	private static final long serialVersionUID = -6634758799416963566L;
	private JLabel label;
	private boolean isQPressed;
	private boolean isAnyOtherButtonThanQPressed;

	public DummyFrame() {
		super();
		this.isQPressed = false;
		this.isAnyOtherButtonThanQPressed = false;
		setSize(0, 0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		label = new JLabel();
		label.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == 'q') {
					isQPressed = true;
				} else {
					isAnyOtherButtonThanQPressed = true;
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
		label.setFocusable(true);
		add(label);
		setVisible(true);
	}

	public boolean isQPressed() {
		return isQPressed;
	}

	public boolean isAnyOtherButtonThanQPressed() {
		return isAnyOtherButtonThanQPressed;
	}

}
