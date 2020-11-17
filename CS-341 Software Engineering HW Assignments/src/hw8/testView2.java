package hw8;

import java.awt.EventQueue;
import java.awt.Panel;
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

public class testView2 {
	
	public JFrame frame;
	public JTextField title_text;
	public JTextField price_text;
	public JTextField quantity_text;
	public JTextField sku_text;
	public JComboBox btnBuild;
	public JButton btnNewButton;
	public JTextArea textArea;
	public JScrollPane scrollPane;
	public String [] options = {"remove", "display book info", "add book", "display inventory"};
	public String selectedComboBoxSelection;
	public Inventory videoInventory;
	public Panel panel; // attempting
	
	public testView2 () {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel = new Panel();
		
		JLabel SKU_label = new JLabel("SKU:");
		SKU_label.setBounds(6, 33, 35, 16);
		frame.getContentPane().add(SKU_label);
		panel.add(SKU_label);
		
		JLabel title_label = new JLabel("Title:");
		title_label.setBounds(236, 74, 35, 16);
		frame.getContentPane().add(title_label);
		panel.add(title_label);
		
		JLabel price_label = new JLabel("Price:");
		price_label.setBounds(236, 33, 44, 16);
		frame.getContentPane().add(price_label);
		panel.add(price_label);
		
		JLabel quantity_label = new JLabel("Quantity:");
		quantity_label.setBounds(6, 74, 61, 16);
		frame.getContentPane().add(quantity_label);
		panel.add(quantity_label);
		
		JLabel title = new JLabel("Campus Bookstore App");
		title.setBounds(133, 6, 158, 16);
		frame.getContentPane().add(title);
		panel.add(title);
		
		title_text = new JTextField();
		title_text.setBounds(283, 69, 130, 26);
		frame.getContentPane().add(title_text);
		title_text.setColumns(10);
		panel.add(title_text);
		
		price_text = new JTextField();
		price_text.setBounds(283, 28, 130, 26);
		frame.getContentPane().add(price_text);
		price_text.setColumns(10);
		panel.add(price_text);
		
		quantity_text = new JTextField();
		quantity_text.setBounds(79, 69, 130, 26);
		frame.getContentPane().add(quantity_text);
		quantity_text.setColumns(10);
		panel.add(quantity_text);
		
		sku_text = new JTextField();
		sku_text.setBounds(79, 28, 130, 26);
		frame.getContentPane().add(sku_text);
		sku_text.setColumns(10);
		panel.add(sku_text);
		
		btnBuild = new JComboBox(options);
		btnBuild.setBounds(79, 108, 114, 27);
		frame.getContentPane().add(btnBuild);
		btnBuild.setSelectedItem(null); // possibly take out?
		panel.add(btnBuild);
		
		btnNewButton = new JButton("SELECT");
		btnNewButton.setBounds(246, 107, 117, 29);
		frame.getContentPane().add(btnNewButton);
		panel.add(btnNewButton);
		
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(18, 155, 415, 103);
		frame.getContentPane().add(scrollPane);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		panel.add(textArea);
		frame.add(panel);
		scrollPane.setViewportView(textArea);
	}
}
