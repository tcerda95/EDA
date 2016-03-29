package arbol;

public class Tree {
	private Node root;

	public void insert(int n) {
		if (isEmpty())
			root = new Node(n);
		else
			root.insert(n);
	}

	public boolean contains(int n) {
		return isEmpty() ? false : root.contains(n);
	}

	public void delete(int n) {
		if (!isEmpty())
			root = root.remove(n);
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void display() {
		displayRec(root);
	}

	private void displayRec(Node n) {
		if (n != null) {
			displayRec(n.getLeft());
			System.out.print(n.getValue() + " ");
			displayRec(n.getRight());
		}
	}
}
