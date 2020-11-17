package hw4;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// TASK 1: INSTANTIATE A BINARY TREE

		BST tree = new BST();

		 tree.displayInOrder(); // assertion message should pop up

		// TASK 2: ADD NODES TO THE BINARY TREE

		tree.addNode(8);
		// tree.deleteNode(8);
		tree.addNode(4);
		tree.addNode(2);
		tree.addNode(3);
		tree.addNode(1);
		tree.addNode(6);
		tree.addNode(7);
		tree.addNode(5);
		tree.addNode(12);
		tree.addNode(14);
		// tree.addNode(8);// duplicate so assertion method should pop up
		tree.addNode(9);
		tree.addNode(11);
		tree.addNode(13);
		tree.addNode(15);

		// TASK 3: DISPLAY THE NODES IN ORDER

		System.out.println("Display the nodes in order: ");
		tree.displayInOrder();

		// TASK 4: TEST NODE DELETIONS

		tree.deleteNode(3);
		// tree.deleteNode(99); // doesn't exist so assertion should pop up
		System.out.println("Display the nodes in order: ");
		tree.displayInOrder();
		tree.deleteNode(12);

		System.out.println("Display the nodes in order: ");
		tree.displayInOrder();

		// TASK 5: PRINT OUT IF ALL ASSERTION TESTS PASS

		System.out.println("All assertion tests passed. Success!");

	}
}
