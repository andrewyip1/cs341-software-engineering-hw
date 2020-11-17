package hw5;

import java.awt.EventQueue;

/**Reads in a file by using file chooser and calculates the standard deviation/mean with a linked list
 * @author andrewyip
 * @version 1.0
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class program1 {

	public JFrame frame;
	public JButton btnBuild;
	public LinkedList list;
	public File inputFile;
	public Scanner fileScanner; // scans the file
	public JTextField readFileTextInput;
	public String temp = "";
	public ArrayList<Integer> values;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	public String readfromInput = "";
	private JScrollPane scrollPane_1;
	private JTextArea output_text;
	public JButton open;
	public JFileChooser fc;
	private JButton btnFileOpener;
	public String filePath = "";

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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel titleLabel = new JLabel("mean & standard deviation");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(142, 6, 173, 16);
		frame.getContentPane().add(titleLabel);

		JLabel inputFileLabel = new JLabel("input from file:");
		inputFileLabel.setHorizontalAlignment(SwingConstants.LEFT);
		inputFileLabel.setBounds(6, 43, 106, 16);
		frame.getContentPane().add(inputFileLabel);

		btnBuild = new JButton("calculate");
		btnBuild.setBounds(142, 112, 117, 29);
		frame.getContentPane().add(btnBuild);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(108, 43, 230, 61);
		frame.getContentPane().add(scrollPane);

		textArea = new JTextArea(readfromInput, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(49, 153, 341, 104);
		frame.getContentPane().add(scrollPane_1);

		output_text = new JTextArea();
		scrollPane_1.setViewportView(output_text);

		btnFileOpener = new JButton("select a file");
		btnFileOpener.setBounds(-5, 61, 117, 29);
		frame.getContentPane().add(btnFileOpener);

		// Linked List and Array List
		list = new LinkedList();
		values = new ArrayList<Integer>();

	}

	/**
	 * As soon as the file is opened, this method runs
	 */

	public void createEvents() {

		// TASK 1: SELECT THE FILE AND STORE THE PATH OF THE SELECTED FILE

		btnFileOpener.addActionListener(new ActionListener() { // when btnBuild button is pressed
			public void actionPerformed(ActionEvent e) { // the output comes out
				
				// SUBTASK A: SELECT THE FILE BY CLICKING THE BUTTON "SELECT FILE"
				
				filePath = chooseFile(); // path to the file that gets returned
				
				// SUBTASK B: DETERMINE IF THE FILE WAS SELECTED OR PRESSED THE CANCELLED BUTTON
				
				if (filePath.equals("cancelled")) {
					textArea.setText("cancel button selected");
					output_text.setText("cancel button selected");
				}

				else {

					System.out.println("File path: " + filePath);

					// TASK 2: STORE THE TEXT IN THE FILE
					//String readfromInput = "";

					try {
						
						String readfromInput = "";

						inputFile = new File(filePath); // input file // reading into eclipse
						fileScanner = new Scanner(inputFile); // scans the file

						// INSTANTIATE AN OUTPUT FILE STREAM AND LINK IT TO THE OUTPUT FILE

						readfromInput = readFile();
						
						// FOR EMPTY FILES
						
						if (readfromInput.equals("") || readfromInput.equals(" ")) {
							textArea.setText(readfromInput);
							output_text.setText("Empty file. Clicking 'calculate' will throw an error. ");
							return;
							}

						else {
							textArea.setText(readfromInput);
						}

					} catch (FileNotFoundException a) {
						System.out.println("Error - This file could not be found.");
					} finally {
						fileScanner.close();
					}

					
				}
			}
		});

		// TASK 3: CLICK "CALCULATE" TO FIND THE SUM AND STANDARD DEVIATION IF THE FILE CONTAINS ONLY NUMBERS

		btnBuild.addActionListener(new ActionListener() { // when btnBuild button is pressed
			public void actionPerformed(ActionEvent e) { // the output comes out
				buildOutput();
			}
		});
	}

	/**
	 * displays output on GUI
	 */

	public void buildOutput() {
		
		assert list.count()>0: "INVALID INPUT. CAN'T CALCULATE AVERAGE AND STANDARD DEVIATION!";
		

		if (!temp.equals("invalid")) {

				// sum
				int sum = list.LinkedListSum();
				int avg = sum / list.count();

				// standard deviation
				double standardDeviation = list.standardDeviation();

				output_text.setLineWrap(true); // wraps onto same line
				output_text.setWrapStyleWord(true); // wraps onto same line
				output_text.setText("values: " + list.displayFormatted() + "\nmean: " + avg + "\n" + "standard deviation: "
						+ standardDeviation);
				return;

		}

		else {
			output_text.setText("invalid input");
			assert false: "CAN'T CALCULATE AN INVALID INPUT";
			return;
		}
	}

	/**
	 * This method reads the file
	 * 
	 * @return the string that we read
	 */

	public String readFile() { // method for reading the file
		String s = ""; // what we are returning
		String z = ""; // string reader

		while (fileScanner.hasNext()) { // this reads the file

			// store variables and add to linked list
			z = fileScanner.next();
			if (isNumeric(z)) {
				int element = Integer.parseInt(z);
				values.add(element); // add to array list to find sum/standard deviation
				list.addNode(element); // add to linked list
				s = s + z + "\n";
			} else {
				temp = "invalid"; // stored as invalid as soon as you have an invalid input
				s = s + z + "\n";
			}
		}
		return s;
	}

	/**
	 * checks to see if the string passed is is a number
	 * 
	 * @param strNum the text we pass in
	 * @return true or false if is a number
	 */

	public boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/**
	 * This selects the file we want to read
	 * 
	 * @return the path of the file
	 */

	public String chooseFile() {

		// file chooser
		open = new JButton();
		fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		fc.setFileFilter(filter);
		fc.setCurrentDirectory(new java.io.File("/Users/andrewyip/Desktop"));
		fc.setDialogTitle("Select a File: ");
		if (fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile().getAbsolutePath(); // once you click open, this gets returned
		} 
		/*
		 * else if (fc.showOpenDialog(frame) == JFileChooser.CANCEL_OPTION) {
			fc.hide();
			return "cancelled";
		}
		 */
		else {
			fc.hide();
			return "cancelled";
		}
	}
}
