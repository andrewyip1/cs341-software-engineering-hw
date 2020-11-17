package hw4;

import java.util.ArrayList;
import java.util.Collections;

public class BST {

	// Attributes/Data Members:

	public Node root; // starting point
	public int count; // to count nodes
	public int singles; // single parents
	public ArrayList<Integer> list;  // to hold all of the values within each node
	public int startIndex; // for display purposes

	public BST() {
		// THE ROOT IS INITIALIZED TO NULL. TREES BEGIN EMPTY
		root = null;
		list = new ArrayList<Integer>();
	}

	// ADD A NODE

	public void addNode(int n) {

		// we can't use the isEmpty() because it wouldn't work for the first node

		assert !found(n) : "This node with the value of " + n + " already exists"; // pre-condition

		// CREATE A TEMPORARY NODE
		Node temp = new Node(n);
		
		System.out.println("The node with value of " + n + " has been ADDED to the Binary Search Tree. ");
		
		// array list
		list.add(n);
		Collections.sort(list);
		System.out.println("Binary Search Tree Node Values: " + getList());
		
		count++;

		// SCENARIO 1: IF THE TREE IS EMPTY, PLACE THE FIRST ITEM AT THE ROOT
		if (root == null) {
			root = temp;
		}

		// SCENARIO 2: ASSUME THERE WILL NOT BE DUPLICATE NODES

		// checked for in the assert statement

		// SCENARIO 3: SEARCH THE CORRECT LOCATION

		else { // if the tree is not empty

			Node travel = root; // start traveling at root

			while (true) { // like recursion (keep searching until breaks)

				// test 1: travel to the left

				if (temp.value < travel.value) { // if its less than the current root value

					if (travel.left != null) { // if there is something there run while loop again
						travel = travel.left;
					}

					else {

						travel.left = temp; // that spot is temp which is the new node
						break;
					}
				}

				// test 2: travel to the right

				else { // if the temp value is greater than the root

					if (travel.right != null) { // if there is something there run while loop again
						travel = travel.right;
					} else {
						travel.right = temp; // that spot is temp which is the new node
						break;
					}
				}
			}
		}

		// post condition
		assert found(n) && !isEmpty() && isBST(): "This node with value of " + n + " wasn't added to the tree correctly. Error";

	}

	public void deleteNode(int num) {

		// displays assertion message if any not satisfied (if it has a node and if it
		// is found)

		// keeps running if it is found and is not empty

		assert found(num) && !isEmpty() : "The node with the value of " + num + " doesn't exist"; // pre-condition

		// TASK 1: DECLARE REFERENCES TO A CHILD AND PARENT NODE
		
		System.out.println("The node with value of " + num + " has been REMOVED to the Binary Search Tree. ");
		
		// SUBTASK: ADD TO ARRAYLIST AND SORT
		list.remove(Integer.valueOf(num)); // removes value of num (not index)
		//list.remove(list.indexOf(num));
		Collections.sort(list);
		System.out.println("Binary Search Tree Node Values: " + getList());

		Node toBeRemoved = root;
		Node parent = null;
		boolean found = false;
		count--;

		// TASK 2: LOCATE THE NODE (basically the contains in the youtube video)

		while (!found && toBeRemoved != null) {
			if (num == toBeRemoved.value) {
				found = true;
			} else {
				parent = toBeRemoved;
				if (num > toBeRemoved.value) {
					toBeRemoved = toBeRemoved.right;
				} else {
					toBeRemoved = toBeRemoved.left;
				}
			}
		}

		// TASK 3: IF THE NODE ISN'T FOUND END (won't get to this because assertion takes care of it)

		if (!found) {
			count++;
			System.out.println("Node isn't found !");
			return;
		}

		// SCENARIO 1: ONE OF THE SUBTREES IS EMPTY
		if (toBeRemoved.left == null || toBeRemoved.right == null) {
			// TASK 1: IF ONE OF THE CHILD NODES IS EMPTY. USE THE OTHER
			Node theChild;
			if (toBeRemoved.left == null) {
				theChild = toBeRemoved.right;
			} else {
				theChild = toBeRemoved.left;
			}

			// TASK 2: DEAL WITH THE SITUATION IF THE PARENT IS NULL
			if (parent == null) {
				root = theChild;
			} else if (parent.left == toBeRemoved) {
				parent.left = theChild;
			} else {
				parent.right = theChild;
			}
			return;
		}

		// SCENARIO 2: NEITHER SUBTREE IS EMPTY (2 sub child)

		// TASK 1: FIND THE SMALLEST ELEMENT OF THE RIGHT SUBTREE

		Node smallestParent = toBeRemoved;
		Node smallest = toBeRemoved.right;
		while (smallest.left != null) {
			smallestParent = smallest;
			smallest = smallest.left;
		}

		// TASK 2: NOW SMALLEST CONTAINS SMALLEST CHILD IN RIGHT SUBTREE
		// MOVE CONTENTS

		toBeRemoved.value = smallest.value;
		if (smallestParent == toBeRemoved) {
			smallestParent.right = smallest.right;
		} else {
			smallestParent.left = smallest.right;
		}

		// post condition
		assert !found(num) && isBST(): "This node value of " + num
				+ " is found and still exists. Node wasn't deleted properly. Error";

	}

	// IN ORDER TRAVERSAL

	public void displayInOrder() { // method to display in order
		assert !isEmpty() : "There are no nodes that exist";
		startIndex = 1;
		inOrderRecursive(root); // passing root from the start to inOrderRecursive method
	}

	public void inOrderRecursive(Node travel) { // in-order display method

		if (travel != null) {
			inOrderRecursive(travel.left);
			// System.out.println(travel.value);
			System.out.println(startIndex + ". " + travel.toString());
			startIndex++;
			inOrderRecursive(travel.right);
		}
	}

	// HELPER METHODS

	public boolean isEmpty() {
		return root == null;
	}

	public boolean found(int num) { // helper method

		// TASK 1: DECLARE REFERENCES TO A CHILD AND PARENT NODE

		Node n = root;
		Node parent = null;
		boolean found = false;

		// TASK 2: LOCATE THE NODE (basically the contains in the youtube video)

		while (!found && n != null) {
			if (num == n.value) {
				found = true; // found is now equal to true
			} else {
				parent = n;
				if (num > n.value) {
					n = n.right;
				} else {
					n = n.left;
				}
			}
		}

		// TASK 3: IF THE NODE ISN'T FOUND END

		if (!found) {
			// System.out.println("Node value of: " + num + " isn't found !");
			return found; // found is false
		}

		else {
			return found; // it would be set to true in the while loop above
		}
	}

	public void CountNodes() {
		System.out.println("Number of nodes: " + count);
	}

	public void countsingleParent(Node travel) {

		if (travel != null) {

			countsingleParent(travel.left); // goes all the way to the left until is equal to null

			if ((travel.left == null && travel.right != null) || (travel.right == null && travel.left != null)) {
				singles++;
				System.out.println("singles parent node is: " + travel.toString());
			}
			countsingleParent(travel.right); // goes all the way to the right until is equal to null
		}
	}
	
	// POST CONDITIONS TO VERIFY WE DO HAVE A BINARY SEARCH TREE

	public boolean isBST() {
		//return isBSTTraverse(root, list.get(0), list.get(list.size()-1)); // either statment works
		return isBSTTraverse(root, Collections.min(list), Collections.max(list));// smallest and largest
	}

	/*
	 * Returns true if the given tree is a BST and its values are >= min and <= max.
	 */
	public boolean isBSTTraverse(Node travel, int min, int max) {	// this goes all the way to the left and the right
		
		/* no nodes are still considered as a binary search tree */
		if (travel == null) {
			return true;
		}

		/* if order is out of place, max/min less or greater than existing, then return false */
		if (travel.value < min || travel.value > max) {
			return false;
		}

		/*
		 * otherwise check the subtrees recursively tightening the min/max constraints
		 */
		// Allow only distinct values (min values first shortening size to get the smallest) 
		return (isBSTTraverse(travel.left, min, travel.value - 1) && isBSTTraverse(travel.right, travel.value + 1, max));
	}
	
	// SETTERS AND GETTERS 

	/**
	 * @return the list
	 */
	public ArrayList<Integer> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList<Integer> list) { 
		this.list = list;
	}
}