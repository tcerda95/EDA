package taller;

import java.util.Comparator;

public class UndoList<T> {
	private static class Node<T> {
		private T value;
		private Node<T> next;
		private Node<T> prev;
		private Node<T> nextUndo; // nodo a borrar despues de borrar este

		public Node(T v) {
			value = v;
		}
	}

	private Node<T> first;
	private Node<T> lastInserted;
	private Comparator<T> cmp;

	public UndoList(Comparator<T> c) {
		cmp = c;
	}

	public void insert(T value) {
		Node<T> newNode = new Node<>(value);
		newNode.nextUndo = lastInserted;
		lastInserted = newNode;
		first = insert(newNode, first, null);
	}

	private Node<T> insert(Node<T> n, Node<T> current, Node<T> prev) {
		if (current == null || cmp.compare(n.value, current.value) < 0) {
			n.next = current;
			n.prev = prev;
			if (current != null)
				current.prev = n;
			return n;
		}
		current.next = insert(n, current.next, current);
		return current;
	}

	public void undo() {
		if (lastInserted.prev != null)
			lastInserted.prev.next = lastInserted.next;
		else
			first = lastInserted.next;
		if (lastInserted.next != null)
			lastInserted.next.prev = lastInserted.prev;
		lastInserted = lastInserted.nextUndo;
	}

	public void print() {
		Node<T> current = first;
		while(current != null) {
			System.out.print(current.value + " ");
			current = current.next;
		}
		System.out.println();
	}
}
