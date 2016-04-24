package parciales;

public class Bag<T> {
	private static class Node<T> {
		private T value;
		private int amount;
		private Node<T> left, right; // prev y next

		public Node(T v) {
			value = v;
			amount = 1;
		}

		public void remove() {
			if (left != null)
				left.right = right;
			if (right != null)
				right.left = left;
		}

		public void swapRight() {
			Node<T> r = right;
			if (left != null)
				left.right = r;
			if (r.right != null)
				r.right.left = this;
			r.left = left;
			right = r.right;
			r.right = this;
			left = r;
		}

		public void swapLeft() {
			Node<T> l = left;
			if (right != null)
				right.left = l;
			if (l.left != null)
				l.left.right = this;
			l.right = right;
			left = l.left;
			l.left = this;
			right = l;
		}
	}

	private Node<T> minAmount;  // más de la izquierda
	private Node<T> maxAmount;  // más de la derercha

	public void add(T elem) {
		if (minAmount == null)
			minAmount = maxAmount = new Node<T>(elem);
		else {
			Node<T> current = minAmount;
			while (current != null && !current.value.equals(elem))
				current = current.right;
			if (current != null) {
				current.amount += 1;
				swapUntilHigher(current);
			}
			else {
				Node<T> min = new Node<T>(elem);
				min.right = minAmount;
				minAmount.left = min;
				minAmount = min;
			}
		}
	}

	public void remove(T elem) {
		Node<T> current = minAmount;

		while (current != null && !current.value.equals(elem))
			current = current.right;

		if (current != null) {
			current.amount -= 1;
			if (current.amount == 0) {
				if (current == minAmount)
					minAmount = minAmount.right;
				if (current == maxAmount)
					maxAmount = maxAmount.left;
				current.remove();
			}
			else
				swapUntilLower(current);
		}
	}

	public void print() {
		Node<T> current = maxAmount;
		while (current != null) {
			System.out.print(current.value + " (" + current.amount + ") ");
			current = current.left;
		}
		System.out.println();
	}

	private void swapUntilHigher(Node<T> n) {
		if (n == minAmount && n.right != null && n.amount > n.right.amount)
			minAmount = n.right;

		while (n.right != null && n.amount > n.right.amount)
			n.swapRight();

		if (n.right == null)
			maxAmount = n;
	}

	private void swapUntilLower(Node<T> n) {
		if (n == maxAmount && n.left != null && n.amount < n.left.amount)
			maxAmount = n.left;

		while (n.left != null && n.amount < n.left.amount)
			n.swapLeft();

		if (n.left == null)
			minAmount = n;
	}
}
