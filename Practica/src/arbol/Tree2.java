package arbol;

import java.util.Comparator;

public class Tree2<T> {

	private Node<T> root;
	private Comparator<T> cmp;

	public Tree2(Comparator<T> c) {
		cmp = c;
	}

	public void add(T value) {
		root = add(value, root);
	}

	private Node<T> add(T value, Node<T> n) {
		if (n == null)
			return new Node<T>(value);
		int comp = cmp.compare(value, n.value);
		if (comp > 0)
			n.right = add(value, n.right);
		else if (comp < 0)
			n.left = add(value, n.left);
		return n;
	}

	public void remove(T value) {
		root = remove(value, root);
	}

	private Node<T> remove(T value, Node<T> n) {
		if (n == null)
			return null;

		int comp = cmp.compare(value, n.value);

		if (comp > 0) {
			n.right = remove(value, n.right);
			return n;
		}
		else if (comp < 0) {
			n.left = remove(value, n.left);
			return n;
		}

		// value == n.value

		if (!n.hasChildren())
			return null;

		if (!n.hasBothChildren())
			return n.hasLeftChild() ? n.left : n.right;

		// buscamos el mínimo del subárbol derecho

		Node<T> prev = null;
		Node<T> min = n.right;

		while (min.hasLeftChild()) {
			prev = min;
			min = min.left;
		}

		if (prev != null) {
			prev.left = min.right;
			min.right = n.right;
		}

		min.left = n.left;

		return min;
	}

	public boolean contains(T value) {
		return contains(value, root);
	}

	private boolean contains (T value, Node<T> n) {
		if (n == null)
			return false;

		int comp = cmp.compare(value, n.value);
		if (comp > 0)
			return contains(value, n.right);
		else if (comp < 0)
			return contains(value, n.left);
		return true;
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

	private static class Node<T> {
		private T value;
		private Node<T> left;
		private Node<T> right;

		public Node(T v) {
			value = v;
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
	}
}
