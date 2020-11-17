package hw8;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList; // import the ArrayList class
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Main {

	public JFrame frame;
	public JTextField title_text;
	public JTextField price_text;
	public JTextField quantity_text;
	public JTextField sku_text;
	public JComboBox btnBuild;
	public JButton btnNewButton;
	public JTextArea textArea;
	public JScrollPane scrollPane;
	public String [] options = {"add book", "remove", "display book info", "display inventory"};
	public String selectedComboBoxSelection;
	public Inventory videoInventory;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
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
		
		JLabel SKU_label = new JLabel("SKU:");
		SKU_label.setBounds(6, 33, 35, 16);
		frame.getContentPane().add(SKU_label);
		
		JLabel title_label = new JLabel("Title:");
		title_label.setBounds(236, 74, 35, 16);
		frame.getContentPane().add(title_label);
		
		JLabel price_label = new JLabel("Price:");
		price_label.setBounds(236, 33, 44, 16);
		frame.getContentPane().add(price_label);
		
		JLabel quantity_label = new JLabel("Quantity:");
		quantity_label.setBounds(6, 74, 61, 16);
		frame.getContentPane().add(quantity_label);
		
		JLabel title = new JLabel("Campus Bookstore App");
		title.setBounds(133, 6, 158, 16);
		frame.getContentPane().add(title);
		
		title_text = new JTextField();
		title_text.setBounds(283, 69, 130, 26);
		frame.getContentPane().add(title_text);
		title_text.setColumns(10);
		
		price_text = new JTextField();
		price_text.setBounds(283, 28, 130, 26);
		frame.getContentPane().add(price_text);
		price_text.setColumns(10);
		
		quantity_text = new JTextField();
		quantity_text.setBounds(79, 69, 130, 26);
		frame.getContentPane().add(quantity_text);
		quantity_text.setColumns(10);
		
		sku_text = new JTextField();
		sku_text.setBounds(79, 28, 130, 26);
		frame.getContentPane().add(sku_text);
		sku_text.setColumns(10);
		
		btnBuild = new JComboBox(options);
		btnBuild.setBounds(79, 108, 114, 27);
		frame.getContentPane().add(btnBuild);
		btnBuild.setSelectedItem(null); // possibly take out?
		
		btnNewButton = new JButton("SELECT");
		btnNewButton.setBounds(246, 107, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(18, 155, 415, 103);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		// Inventory Class:
		videoInventory = new Inventory ();
		
	}
	
	/**
	 * This is to create our output once the button is clicked
	 */
	
	public void createEvents() {
		btnNewButton.addActionListener(new ActionListener() { // when btnBuild button is pressed
			public void actionPerformed(ActionEvent e) { // the output comes out
				buildOutput();
			}
		});
	}
	
	public void buildOutput () {
		
		// Combo Box Selection choice:
		if (btnBuild.getSelectedItem() == null) {
			textArea.setText("NO OPTION SELECTED");
			return;
		}
		else {
			selectedComboBoxSelection = (String) btnBuild.getSelectedItem();
		}
		
		// Based on selections:

		if (selectedComboBoxSelection.equals("add book")){ 
			
			String sku = sku_text.getText();
			String title = title_text.getText();
			String price = price_text.getText();
			String quantity = quantity_text.getText();
			
			if (price.equals("") || quantity.equals("")) {
				textArea.setText("YOU HAVE AN EMPTY FIELD.");
				return;
			}
			
			int p = Integer.parseInt(price);
			int q = Integer.parseInt(quantity);
			
			if (sku.equals("") || title.equals("") || price.equals("") || quantity.equals("")) {
				textArea.setText("YOU HAVE AN EMPTY FIELD.");
			}
			else {
				Video vid = new Video(sku, title, p, q);
				videoInventory.v.add(vid); // adding to array list
				videoInventory.addVideo(vid); // adding to hashmap
				textArea.setText("video successfully added!");
			}
		}
		
		if (selectedComboBoxSelection.equals("remove")){
			
			// Passing to Video Constructor
			
			String sku = sku_text.getText();	
			String remove = "YOU CAN'T REMOVE A VIDEO WITH THIS SKU BECAUSE IT IS NOT CONTAINED IN THE INVENTORY";
			videoInventory.removeVideo(sku);
			videoInventory.hashVideoMap.remove(sku);
			remove = "video successfully removed!";
			textArea.setText(remove);
		}
		if (selectedComboBoxSelection.equals("display book info")){
			
			String sku = sku_text.getText();
			String txt = "video with that sku doesn't exist";
			
			for (int i = 0; i<videoInventory.getV().size(); i++) {
				if (videoInventory.getV().get(i).getSKU().equals(sku)) {
					txt = "Video Information: \n" + videoInventory.getV().get(i).toString();
				}
			}
			textArea.setText(txt);
		}
		
		if (selectedComboBoxSelection.equals("display inventory")) {
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			textArea.setText("Video Inventory: " + "\n" + videoInventory.display());
		}
		
		System.out.println(videoInventory.displayHashMap());
			
	}
}
