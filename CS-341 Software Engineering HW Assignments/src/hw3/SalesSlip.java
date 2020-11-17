package hw3;

import java.util.ArrayList;

public class SalesSlip{
	
	// Data Members
	
	public ArrayList<String> items;
	public ArrayList<Double> cost;
	public ArrayList<Integer> quantity;
	public String costString;
	public String str = "";
	
	public SalesSlip() {
		items = new ArrayList <String>();
		cost = new ArrayList<Double>();
		quantity = new ArrayList<Integer>();
	}
	
	public void addItem (String item, double cost1, int itemQuantity) { // passing items 
		items.add(item);
		cost.add(cost1);
		costString = String.format("%.2f", cost.get(cost.size()-1)); // to get to two decimal points
		quantity.add(itemQuantity);
	}
	
	public String total () { // how to calculate the sum
		
		double sum = 0;
		for (int i = 0; i<cost.size(); i++) {
			sum = sum + (cost.get(i)*quantity.get(i));
		}
		
		String formatSum = String.format("%.2f", sum); // formatting to two decimal points
		String result = "$"+formatSum;
		return result;
	}
	
	public String display () { // display list string you just input
		str = str +  items.get(items.size()-1) + "                $" +costString + "               " + quantity.get(quantity.size()-1) + " \n";
		return str;
	}
}
