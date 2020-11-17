package hw2pt2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/** Represents program2
 * @author andrewyip
 * @version 1.0
 */

public class program2 {

	private JFrame frame;
	private JTextField inputPassword;
	private JButton btnBuild;
	private JLabel passwordLabel;
	private JScrollPane scrollPane;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					program2 window = new program2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public program2() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel title = new JLabel("Password Strength Detector");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(148, 6, 198, 16);
		frame.getContentPane().add(title);
		
		passwordLabel = new JLabel("Input Password Here:");
		passwordLabel.setBounds(15, 81, 141, 16);
		frame.getContentPane().add(passwordLabel);
		
		inputPassword = new JTextField();
		inputPassword.setBounds(168, 76, 232, 26);
		frame.getContentPane().add(inputPassword);
		inputPassword.setColumns(10);
		
		btnBuild = new JButton("DETECT!");
		btnBuild.setBounds(168, 128, 117, 29);
		frame.getContentPane().add(btnBuild);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 191, 389, 53);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
	
	/**
	 * This is specifically to create the button when clicked on. Button and event listener gets activated and runs buildOutput()
	 */

	public void createEvents() {
		


		btnBuild.addActionListener(new ActionListener() { // when btnBuild button is pressed
			public void actionPerformed(ActionEvent e) { // the output comes out
				buildOutput();
			}
		});

	}
	

	/**
	 * This is do provide us with the output that we want. This generates and lets us know if the password that we entered is sufficient or not. 
	 */

	public void buildOutput() {

		
		String password = inputPassword.getText();
		
		String str2 = "Password: " + password;
		
		int count = 1; // to count number of consecutive letters/numbers
		int max = 1; // to find the highest number
		
		for (int i = 0; i<password.length()-1; i++) { // to count the number of consecutive 
			
			if (password.charAt(i) == password.charAt(i+1)) {
				max = count;
				count++;
			}	
		}
		
		if (password.length() < 7 || password.length() > 12) {
			textArea.setText("ERROR INVALID PASSWORD LENGTH!");
		}
		else if (password.contains(" ")){
			textArea.setText("NO SPACES ALLOWED IN THE PASSWORD");
		}
		else {
			String output = "";
			
			if (max <= 2) {
				output = "Output:  The largest block in the password is "+ max + ". This is a decent password.";
			}
			
			else {
				int want = 2; // we want a length of 2
				int diff = max - want;
				
				output = "Output:  The largest block in the password is "+ max + ". This password can be made stronger by reducing this block by " + diff+ ".";
			}
			
			textArea.setText(str2 + "\n" + output);
		}
	}
}
