package hw10;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class ListPanel extends JPanel {
	private int diameter = 30;
	private ArrayList<ListItem> mList = null;

	public ListPanel(String title) {
		super();
		mList = new ArrayList<ListItem>();
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), title));
	}

	public ListPanel(String title, ArrayList<ListItem> list) {
		this(title);
		addItems(list);
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
		repaint();
	}

	public void addItems(ArrayList<ListItem> list) {
		mList = (ArrayList<ListItem>) list.clone();
		for (ListItem item : mList)
			add(new JLabel(item.createIcon(diameter)));
	}

	public ArrayList<ListItem> getArrayList() {
		return mList;
	}

	public void sortbyBValues() {

		int size = mList.size();

		for (int i = 0; i < size-1; i++) {
			for (int j = 0; j < size -i- 1; j++) {
				if (mList.get(j).getValB() > mList.get(j + 1).getValB()) {
					ListItem t = mList.get(j);
					mList.set(j, mList.get(j + 1));
					mList.set(j + 1, t);
				}
			}
		}
	}
	
	public void displayB () {
		for (int i = 0; i<mList.size(); i++) {
			System.out.print(mList.get(i).getValB() + " ");
		}
	}
}
