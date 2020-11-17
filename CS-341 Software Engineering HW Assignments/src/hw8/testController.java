// ERRORS


package hw8;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class testController {
	
	public static testView2 view; // view
	public static Inventory model;
	public static testView hola;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		view = new testView2();
		//hola = new hi();
		model = new Inventory();
		//addListenerEvent();

	}
	
	public static void addListenerEvent() {
		
		// Control the update and response of the model and view
		view.btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateModel();
			}
		});
	}
	
	public static void updateModel() {
		
		String selectedComboBoxSelection;
		
		// Passing to Video Constructor
		
		String sku = view.sku_text.getText();
		
		String title = view.title_text.getText();
		
		String price = view.price_text.getText();
		int p = Integer.parseInt(price);
		
		String quantity = view.quantity_text.getText();
		int q = Integer.parseInt(quantity);
		
		Video vid = new Video(sku, title, p, q);
		
		// Combo Box Selection choice:
		if (view.btnBuild.getSelectedItem() == null) {
			view.textArea.setText("NO OPTION SELECTED");
			return;
		}
		else {
			selectedComboBoxSelection = (String) view.btnBuild.getSelectedItem();
		}

		updateView(selectedComboBoxSelection, vid);
		
	}
	
	public static void updateView(String selectedComboBoxSelection, Video vid) {
		
		if (selectedComboBoxSelection.equals("add book")){ 
			view.videoInventory.addVideo(vid);
			view.textArea.setText("video successfully added!");
		}
		
		if (selectedComboBoxSelection.equals("remove")){
			view.videoInventory.removeVideo(vid.getSKU());
			view.textArea.setText("video successfully removed!");
		}
		if (selectedComboBoxSelection.equals("display book info")){
			view.textArea.setText("Video Information: \n" + vid.toString());
		}
		if (selectedComboBoxSelection.equals("display inventory")) {
			view.textArea.setLineWrap(true);
			view.textArea.setWrapStyleWord(true);
			view.textArea.setText("Video Inventory: " + "\n" + view.videoInventory.display());
		}
		
		System.out.println(view.videoInventory.displayHashMap());
	}

}
