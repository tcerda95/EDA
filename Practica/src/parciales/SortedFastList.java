package parciales;

import java.util.Comparator;

public class SortedFastList<T> {
	private static class Node<T> {
		private T value;
		private Node<T> next;
		private Node<T> skipNext;

		public Node(T v) {
			value = v;
		}
	}

	private Node<T> first;
	private Comparator<T> cmp;

	public SortedFastList(Comparator<T> c) {
		cmp = c;
	}

	public void add(T value) {
		if (first == null || cmp.compare(first.value, value) > 0) {
			Node<T> n = new Node<>(value);
			n.next = first;
			n.skipNext = first != null ? first.next : null;
			first = n;
		} else {
			first.next = add(value, first.next, first.skipNext);
			first.skipNext = first.next.next;
		}
	}

	private Node<T> add(T value, Node<T> prev, Node<T> current) {
		if (current == null && prev == null)
			return new Node<T>(value);
		if (current == null || cmp.compare(value, current.value) < 0) {
			Node<T> n = new Node<T>(value);
			if (cmp.compare(value, prev.value) < 0) {
				n.next = prev;
				n.skipNext = current;
				return n;
			}
			n.next = current;
			n.skipNext = prev.skipNext;
			prev.next = n;
			prev.skipNext = current;
			return prev;
		}
		current.next = add(value, current.next, current.skipNext);
		current.skipNext = current.next.next; // feo
		return prev;
	}

	public void print() {
		Node<T> current = first;
		while (current != null) {
			System.out.print(current.value + " ");
			current = current.next;
		}
	}
}
