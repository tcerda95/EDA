package arbol;

public class Node {
	private int value;
	private Node left;
	private Node right;

	public Node(int n) {
		value = n;
		left = null;
		right = null;
	}

	public Node getRight() {
		return right;
	}

	public Node getLeft() {
		return left;
	}

	public int getValue() {
		return value;
	}

	// sin repetidos
	public void insert(int n) {
		if (n > value) {
			if (hasRightChild())
				getRight().insert(n);
			else
				right = new Node(n);
		}
		else if (n < value) {
			if (hasLeftChild())
				getLeft().insert(n);
			else
				left = new Node(n);
		}
	}

	public Node remove (int n) {
		if (n > value) {
			if (hasRightChild())
				right = getRight().remove(n);
			return this;
		}
		if (n < value) {
			if (hasLeftChild())
				left = getLeft().remove(n);
			return this;
		}

		if (!hasChilds())
			return null;

		if (!hasBothChilds()) {
			return hasLeftChild() ? getLeft() : getRight();
		}

		// tiene ambos, buscamos el mínimo del subarbol der

		Node parent = getRight();
		Node rl = getRight().getLeft();

		if (rl == null) {
			parent.setLeft(getLeft());
			return parent;
		}

		// guardo en rl el nodo mínimo
		while (rl.hasLeftChild()) {
			parent = rl;
			rl = rl.getLeft();
		}

		parent.setLeft(rl.getRight()); // borro rl

		// subo rl
		rl.setRight(getRight());
		rl.setLeft(getLeft());

		return rl;
	}

	public boolean hasChilds() {
		return hasLeftChild() || hasRightChild();
	}

	public boolean hasLeftChild() {
		return left != null;
	}

	public boolean hasRightChild() {
		return right != null;
	}

	public boolean hasBothChilds() {
		return hasLeftChild() && hasRightChild();
	}

	public boolean contains(int n) {
		if (n > value) {
			return hasRightChild() ? getRight().contains(n) : false;
		}
		if (n < value) {
			return hasLeftChild() ? getLeft().contains(n) : false;
		}
		return true;
	}

	public void setRight(Node n) {
		right = n;
	}

	public void setLeft(Node n) {
		left = n;
	}
}
