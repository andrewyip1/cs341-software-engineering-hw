package hw8;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class testView {

	public JFrame window;
	public JPanel panel;
	public JTextField textField;
	public JButton tapButton;

	public testView() {

		// TASK 1: Create view. These are the swing UI components.
		textField = new JTextField(26);
		textField.setEditable(false);
		tapButton = new JButton("TAP");

		// TASK 2: Set the view layout
		panel = new JPanel();
		panel.add(textField);
		panel.add(tapButton);

		// TASK 3: Display it all in a window and make the window appear
		window = new JFrame("TAP MVC DEMO");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(panel);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

	}

}
