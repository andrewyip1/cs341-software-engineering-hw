package hw3; 

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This program imitates a sales slip when purchasing food at a store
 * @author andrewyip
 * @version 1.0
 */

public class program1 { 

	public JFrame frame;
	public JTextField item_text;
	public JTextField cost_text;
	public JTextField quantity_text;
	public JButton btnBuild;
	public JLabel total_sales;
	public JScrollPane scrollPane_1;
	public JTextField sales_output;
	public JScrollPane scrollPane_2;
	public JTextArea list_output;
	public SalesSlip s1;

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
		
		JLabel lblNewLabel = new JLabel("Your Shopping Cart: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(164, 6, 145, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel item_label = new JLabel("Item:");
		item_label.setBounds(55, 39, 61, 16);
		frame.getContentPane().add(item_label);
		
		JLabel cost_label = new JLabel("Cost ($): ");
		cost_label.setBounds(55, 74, 61, 16);
		frame.getContentPane().add(cost_label);
		
		JLabel quantity_label = new JLabel("Quantity: ");
		quantity_label.setBounds(55, 102, 73, 16);
		frame.getContentPane().add(quantity_label);
		
		item_text = new JTextField();
		item_text.setBounds(128, 34, 245, 26);
		frame.getContentPane().add(item_text);
		item_text.setColumns(10);
		
		cost_text = new JTextField();
		cost_text.setColumns(10);
		cost_text.setBounds(128, 69, 245, 26);
		frame.getContentPane().add(cost_text);
		
		quantity_text = new JTextField();
		quantity_text.setColumns(10);
		quantity_text.setBounds(128, 97, 245, 26);
		frame.getContentPane().add(quantity_text);
		
		btnBuild = new JButton("Add item to sales list");
		btnBuild.setBounds(117, 130, 173, 29);
		frame.getContentPane().add(btnBuild);
		
		total_sales = new JLabel("Total Sales:");
		total_sales.setBounds(35, 245, 81, 16);
		frame.getContentPane().add(total_sales);
		
		// scroll for list items
		scrollPane_2 = new JScrollPane(list_output, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setBounds(33, 155, 340, 73);
		frame.getContentPane().add(scrollPane_2);
		
		list_output = new JTextArea();
		scrollPane_2.setViewportView(list_output);
		
		// for sales (dollar total amount)
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(117, 240, 205, 32);
		frame.getContentPane().add(scrollPane_1);
		
		sales_output = new JTextField();
		scrollPane_1.setViewportView(sales_output);
		
		// Instantiating object
		s1 = new SalesSlip(); 

	}
	
	/**
	 * This method is implemented when you press the button and add an item to your sales slip.
	 */
	public void createEvents() {
		btnBuild.addActionListener(new ActionListener() { // when btnBuild button is pressed
			public void actionPerformed(ActionEvent e) { // the output comes out
				buildOutput();
			}
		});
	}
	
	/**
	 * This provides us with the output for the list as well as the total cost
	 */
	public void buildOutput() {
		String it = item_text.getText();
		double c = Double.parseDouble(cost_text.getText());
		int q = Integer.parseInt(quantity_text.getText());
		s1.addItem(it, c, q);
		list_output.setText(s1.display());
		list_output.setLineWrap(true); // wraps onto same line
		list_output.setWrapStyleWord(true); // wraps onto same line
		sales_output.setText(s1.total());
	}
}
