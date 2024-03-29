package arbol;

import java.util.Comparator;


public class AVLTree<T> {
	private Node<T> root;
	private Comparator<T> cmp;

	public AVLTree(Comparator<T> c) {
		cmp = c;
	}

	public boolean contains(T value) {
		return contains(value, root);
	}

	public boolean contains(T value, Node<T> t) {
		if (t == null)
			return false;
		int comp = cmp.compare(value, t.value);
		if (comp > 0)
			return contains(value, t.right);
		else if (comp < 0)
			return contains(value, t.left);
		return true;
	}

	public void insert(T value) {
		root = insert(value, root);
	}

	private Node<T> insert(T value, Node<T> n) {
		if (n == null)
			return new Node<T>(value);
		int comp = cmp.compare(value, n.value);
		if (comp > 0)
			n.right = insert(value, n.right);
		else if (comp < 0)
			n.left = insert(value, n.left);
		n.updateHeight();
		return rebalance(n);
	}

	private Node<T> rebalance(Node<T> n) {
		int bf = n.getBF();
		if (bf < -1) {
			if (n.right.getBF() > 0)
				n.right = rotateRight(n.right);
			n = rotateLeft(n);
		}
		else if (bf > 1) {
			if (n.left.getBF() < 0)
				n.left = rotateLeft(n.left);
			n = rotateRight(n);
		}
		return n;
	}

	public void remove(T value) {
		root = remove(value, root);
	}

	private Node<T> remove(T value, Node<T> n) {
		if (n == null)
			return n;
		int comp = cmp.compare(value, n.value);

		if (comp < 0)
			n.left = remove(value, n.left);
		else if (comp > 0)
			n.right = remove(value, n.right);
		else {
			if(!n.hasChildren())
				return null;

			if (!n.hasBothChildren())
				return n.hasRightChild() ? n.right : n.left;

			if (n.right.hasLeftChild())
				n.value = removeMin(n.right.left, n.right); // devuelve el minímo mientras balancea
			else {
				n.value = n.right.value;
				n.right = n.right.right;
			}
		}

		n.updateHeight();
		return rebalance(n);
	}

	private T removeMin(Node<T> n, Node<T> prev) {
		if (!n.hasLeftChild()) {
			prev.left = n.right;
			return n.value;
		}
		T min = removeMin(n.left, n);
		n.updateHeight();
		prev.left = rebalance(n);
		return min;
	}

	private Node<T> rotateLeft(Node<T> n) {
		Node<T> right = n.right;
		n.right = right.left;
		right.left = n;
		n.updateHeight();
		right.updateHeight();
		return right; // devolvemos lo q ahora ocupa el lugar de n
	}

	private Node<T> rotateRight(Node<T> n) {
		Node<T> left = n.left;
		n.left = left.right;
		left.right = n;
		n.updateHeight();
		left.updateHeight();
		return left; // devolvemos lo q ahora ocupa el lugar de n
	}

	public int getHeight() {
		return getHeight(root);
	}

	private int getHeight(Node<T> n) {
		if (n == null)
			return -1;
		return 1 + Integer.max(getHeight(n.left), getHeight(n.right));
	}

	@Override
	public String toString() {
		return toString(root);
	}

	private String toString(Node<T> n) {
		String str = "";
		if (n != null) {
			str = str.concat(toString(n.left));
			str = str.concat(n.value.toString() + " ");
			str = str.concat(toString(n.right));
		}
		return str;
	}

	private static class Container {
		private boolean isValid; // indica si el árbol es AVL
		private int height;		 // contiene la altura del árbol
	}

	public boolean isAVL() {
		return isAVL(root, null, null).isValid;
	}

	private Container isAVL(Node<T> n, T maxValue, T minValue) {
		Container c = new Container();
		if (n == null) {
			c.isValid = true;
			c.height = -1;
			return c;
		}
		if (maxValue != null && cmp.compare(n.value, maxValue) > 0) {
			c.isValid = false;
			return c;
		}
		if (minValue != null && cmp.compare(n.value, minValue) < 0) {
			c.isValid = false;
			return c;
		}
		Container leftc = isAVL(n.left, n.value, minValue);
		Container rightc = isAVL(n.right, maxValue, n.value);

		if (!leftc.isValid || !rightc.isValid) {
			c.isValid = false;
			return c;
		}

		c.isValid = Math.abs(leftc.height - rightc.height) <= 1;
		c.height = 1 + Integer.max(leftc.height, rightc.height);
		return c;
	}

	private static class Node<T> {
		private T value;
		private Node<T> left;
		private Node<T> right;
		private int height;

		public Node(T v) {
			value = v;
		}

		public void updateHeight() {
			int lh = left == null ? -1 : left.height;
			int rh = right == null ? -1 : right.height;
			height = Integer.max(lh, rh) + 1;
		}

		public boolean hasChildren() {
			return hasLeftChild() || hasRightChild();
		}

		public boolean hasLeftChild() {
			return left != null;
		}

		public boolean hasRightChild() {
			return right != null;
		}

		public boolean hasBothChildren() {
			return hasLeftChild() && hasRightChild();
		}

		public int getBF() {
			int lh = left == null ? -1 : left.height;
			int rh = right == null ? -1 : right.height;
			return lh - rh;
		}
	}
}
