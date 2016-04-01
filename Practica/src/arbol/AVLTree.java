package arbol;

import java.util.Comparator;

public class AVLTree<T> {
	private Node<T> root;
	private Comparator<T> cmp;

	public AVLTree(Comparator<T> c) {
		cmp = c;
	}

	public void insert(T value) {
		root = insert(value, root);
	}

	private Node<T> insert(T value, Node<T> n) {
		if (n == null)
			return new Node<T>(value);

		int childBF = 5; // para q no tire error de inicializacion más abajo
		int comp = cmp.compare(value, n.value);

		if (comp > 0) {
			if (!n.hasRightChild())
				n.BF -= 1;
			else
				childBF = n.right.BF;
			n.right = insert(value, n.right);

			if (n.right.BF != 0 && n.right.BF != childBF)
				n.BF -= 1;

			if (n.BF == -2) { // desbalance provocado por subarbol derecho
				if (n.right.BF == 1) // subarbol izquierdo del hijo: RL
					n.right = rotRight(n.right);
				n = rotLeft(n);		// subarbol derecho del hijo: RR
			}

		}
		else if (comp < 0) {
			if (!n.hasLeftChild())
				n.BF += 1;
			else
				childBF = n.left.BF;
			n.left = insert(value, n.left);

			if (n.left.BF != 0 && childBF != n.left.BF)
				n.BF += 1;

			if (n.BF == 2) { // desbalance provocado por subarbol izquierdo
				if (n.left.BF == -1) // subarbol derecho del hijo: LR
					n.left = rotLeft(n.left);
				n = rotRight(n);    // subarbol izquierdo del hijo: LL
			}
		}

		return n;
	}

	private Node<T> rotLeft(Node<T> n) {
		Node<T> r = n.right;
		n.BF = 0;
		r.BF = 0;
		n.right = r.left;
		r.left = n;
		return r;
	}

	private Node<T> rotRight(Node<T> n) {
		Node<T> l = n.left;
		n.BF = 0;
		l.BF = 0;
		n.left = l.right;
		l.right = n;
		return l;
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

	private static class Node<T> {
		private T value;
		private Node<T> left;
		private Node<T> right;
		private int BF; // balance factor: leftHeight - rightHeight

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
