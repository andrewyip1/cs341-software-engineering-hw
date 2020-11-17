package hw1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class program1 {

	private JFrame frame;
	private JTextField yText;
	private JTextField xText;
	private JButton btnBuild;
	private JScrollPane scrollPane;
	public JTextArea sumOutput;

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
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel title = new JLabel("Enter ONLY positive integers");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(151, 6, 191, 16);
		frame.getContentPane().add(title);
		
		JLabel lblX = new JLabel("X");
		lblX.setBounds(29, 43, 13, 16);
		frame.getContentPane().add(lblX);
		
		JLabel lblY = new JLabel("Y");
		lblY.setBounds(29, 105, 13, 16);
		frame.getContentPane().add(lblY);
		
		yText = new JTextField();
		yText.setBounds(54, 100, 320, 26);
		frame.getContentPane().add(yText);
		yText.setColumns(10);
		
		xText = new JTextField();
		xText.setBounds(54, 38, 320, 26);
		frame.getContentPane().add(xText);
		xText.setColumns(10);
		
		btnBuild = new JButton("ADD");
		btnBuild.setBounds(151, 155, 117, 29);
		frame.getContentPane().add(btnBuild);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 202, 346, 53);
		frame.getContentPane().add(scrollPane);
		
		sumOutput = new JTextArea();
		scrollPane.setViewportView(sumOutput);
	}
	
	private void createEvents() {
		
		btnBuild.addActionListener(new ActionListener () { // when btnBuild button is pressed
			public void actionPerformed (ActionEvent e) { // the output comes out
				buildOutput();
			}
		});
		
	}
	
	private void buildOutput () {
		
		BigNumber x = new BigNumber(); // creates object of big number class (basically an array list)
		BigNumber y = new BigNumber();
		
		x.arrayInput(xText.getText());
		y.arrayInput(yText.getText());
		
		if (x.getArr().isEmpty() || y.getArr().isEmpty()) {
			sumOutput.setText("input error oops");
		}
		
		else {
			BigNumber z = x.add(y); // sums it up
			sumOutput.setText(z.toString()); // converting to string
		}

		
	}

}
