package hw5;

import java.util.ArrayList;

public class LinkedList {
	
	// Data Attributes: 
	private Node first; 
	private Node last;

	// Constructor:
	public LinkedList() {
		first = null;
		last = null;
	}

	// Member Methods:
	public void addNode(int element) { 
		Node s = new Node(element);

		
		if (first == null) { 
			first = s;
			last = s;
		} else {
			last.next = s; 
			last = s; 
		}
	}

	public void display() {
		Node travel = first;

		while (travel != null) {
			System.out.println(travel.element);
			travel = travel.next;
		}
	}
	
	public String displayFormatted() {
		Node travel = first;
		String formatted = "";
		
		while (travel!= null) {
			formatted = formatted + travel.element + " ";
			travel = travel.next;
		}
		
		return formatted;
		
	}
	
	public int LinkedListSum () {
		Node travel = first;
		int sum = 0;
		
		while (travel!= null) {
			sum+= travel.element;
			travel = travel.next;
		}
		
		return sum;
	}
	
	public double standardDeviation () {
		
		ArrayList<Integer> values = new ArrayList <Integer> ();
		
		Node travel = first;
		int sum = 0;
		
		while (travel!= null) {
			values.add(travel.element);
			sum+= travel.element;
			travel = travel.next;
		}
		
		int avg = sum/values.size();
		int denominator = values.size()-1;
		int numerator = 0;
		for (int i = 0; i<values.size(); i++) {
			numerator += Math.pow((values.get(i)-avg), 2);
		}
		
		if (values.size()==1) {
			denominator = 1;
		}
		
		double standardDeviation = Math.sqrt((numerator)/(denominator));
		return standardDeviation;
		
	}
	
	
	public int count () { 
		int counter = 0;
		Node travel = first;
		while (travel!= null) {
			counter++;
			travel = travel.next;
		}
		return counter;
	}
	
	public void sort (LinkedList list ) {
		for (int i = 0; i< (list.count())-1; i++) {
			Node temp = first; 
			Node before = first;
			
			while (temp.next != null) { 
				Node after = temp.next; 
				if (temp == first) { 
					if (temp.element > after.element) { 
						temp.next = after.next;
						first = after;
						first.next = temp;
						before = first;
					} else {
						temp = temp.next; 
					}
				}
				else {
					if (temp.element > after.element) {
						temp.next = after.next; 
						before.next = after;
						after.next = temp;
						before = before.next; 
					}
					else { 
						before = before.next;
						temp = temp.next;
					}
				}
			}
		}
	}
}
