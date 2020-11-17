package hw10;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DataStructsFrame extends JFrame {
	public DataStructsFrame(String title, int[] numbers, int [] b) {
		super(title);

		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		final ArrayList<ListItem> list = arrayToList(numbers, b);

		final ListPanel unorderedList = new ListPanel("Unordered List");
		unorderedList.setDiameter(75);
		unorderedList.addItems(list); // adds all elements from list

		final ListPanel orderedList = new ListPanel("Ordered List");
		orderedList.setDiameter(100);

		JButton sortButton = new JButton("Sort List");
		sortButton.setSize(30, 10);
		sortButton.setAlignmentX(CENTER_ALIGNMENT);

		
		// once you click the sort button
		sortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("pre sort (only a values) " + list);
				Collections.sort(list); // this doesn't work because we can't sort ListItems
				System.out.println("post sort (compare to method) (only a values)" + list);
				orderedList.addItems(list);
				panel.add(orderedList); // panel add
				//panel.add(unorderedList);
				
				System.out.print("unsorted b values: ");
				unorderedList.displayB();
				System.out.println();
				unorderedList.sortbyBValues();
				System.out.print("sorted b values: (bubble sort)");
				unorderedList.displayB();
				
				pack();
			}
		});

		panel.add(unorderedList);
		panel.add(sortButton);
		add(panel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private ArrayList<ListItem> arrayToList(int[] numbers, int [] b) {
		ArrayList<ListItem> list = new ArrayList<ListItem>();

		for (int i = 0; i < numbers.length; i++) {
			ListItem item = new ListItem(numbers[i], b[i]); // temp value of 0
			list.add(item);
		}

		return list;
	}
}
