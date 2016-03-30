package taller;

import java.util.Comparator;

public class OrderedList<T> {
	private static class Node<T> {
		private T value;
		private Node<T> next;

		public Node(T v, Node<T> n) {
			value = v;
			next = n;
		}
	}

	private Node<T> first;
	private Comparator<T> cmp;

	public OrderedList(Comparator<T> c) {
		first = null;
		cmp = c;
	}

	public void add(T value) {
		first = add(value, first);
	}

	private Node<T> add(T value, Node<T> n) {
		if (n == null || cmp.compare(n.value, value) > 0)
			return new Node<T>(value, n);

		n.next = add(value, n.next);
		return n;
	}

	public void delete(T value) {
		first = delete(value, first);
	}

	private Node<T> delete(T value, Node<T> n) {
		if (n == null || cmp.compare(n.value, value) > 0)
			return n;

		if (n.value.equals(value))
			return n.next;

		n.next = delete (value, n.next);
		return n;
	}
}
