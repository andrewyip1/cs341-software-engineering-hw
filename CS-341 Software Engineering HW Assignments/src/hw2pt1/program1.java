package hw2pt1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This our application 1 of HW2
 * @author andrewyip
 * @version 1.0
 */

public class program1 {

	private JFrame frame;
	private JTextField textField;
	private JTextArea textArea;
	private JButton btnBuild;
	private JLabel lblNewLabel;
	private String result;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					program1 window = new program1();
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
	public program1() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		result = "";
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Scrabble App");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(179, 6, 89, 16);
		frame.getContentPane().add(lblNewLabel);
		
		btnBuild = new JButton("Show output");
		btnBuild.setBounds(179, 93, 117, 29);
		frame.getContentPane().add(btnBuild);
		
		JLabel lblNewLabel_1 = new JLabel("Input 4 letters here:");
		lblNewLabel_1.setBounds(6, 60, 135, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(143, 55, 284, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 157, 395, 100);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
	
	/**
	 * This is to create our output once the button is clicked
	 */
	
	public void createEvents() {
		

		btnBuild.addActionListener(new ActionListener() { // when btnBuild button is pressed
			public void actionPerformed(ActionEvent e) { // the output comes out
				buildOutput();
			}
		});

	}
	
	/**
	 * This is to provide us with the output that we want. 
	 */
	
	public void buildOutput() {
		
		
		String text = textField.getText(); // retrieves the text
		int len = text.length(); // length
		
		
		// checks if it is invalid from the left
		if (len >4 || text.contains("1")|| text.contains("2")|| text.contains("3")|| text.contains("4")||
				text.contains("5")|| text.contains("6")|| text.contains("7")|| text.contains("8")
				|| text.contains("9")) {
			
			if (len > 4) {
				textArea.setText("you entered more then four letters");
			}
			else {
				textArea.setText("you entered a number into your text. that is wrong.");
			}
			
			
		}
		
		// if valid goes through permutation
		else {
			
			permute(text, 0,len-1);
			textArea.setText(result);
		}

	}
	
	
	
	
	/**
	 * This allows us to find the possible outcomes
	 * @param str The string we pass in
	 * @param left Where we start in the string
	 * @param right the right of the string
	 */
	
	public void permute (String str, int left, int right) { // HAVE TO REVIEW RECURSION HOMEWORK/LABS FROM CS111		
		// base case
		
		if (left==right) { // base case assuming we went all the way through
			
			result+=str; // adding permutation to output string
			result+=" "; // adding to the end a space so that all the permutations have a space in between
			
		}
		// recursive statement
		else {

			for (int i = left; i<=right; i++) { 
				str = swap(str,left,i); // swap method (swaps with itself)
				permute(str,left+1, right); // recursive statement (to make it go around)
				str = swap(str,left,i); // specifically for back tracking (used for next iteration)
			}
			
			
		}
	}
	
	
	
	/**
	 * This is to swap characters and returns it as a string
	 * @param str the string that we intend on swapping
	 * @param i index that we swap at (calling it index 'i')
	 * @param j index that we swap at (calling it index 'j')
	 * 
	 * @return returns the string value
	 */
	
	public String swap (String str, int i, int j) {
		char temp; // temporary value value
		char[] charArray = str.toCharArray(); // converts string to character array
		
		
		//SWAPPING PROCESS of two elements from the permutation
		temp = charArray[i];
		charArray[i]=charArray[j];
		charArray[j] = temp;
		
		//RETURNING
		return String.valueOf(charArray); // returning a string
	}
}
