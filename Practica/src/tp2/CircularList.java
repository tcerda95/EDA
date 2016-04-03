package tp2;

import java.util.Iterator;

public class CircularList<T> implements Iterable<T>{
	private Node<T> current;
	private int size;

	public void add(T value) {
		size += 1;

		Node<T> newNode = new Node<T>(value);
		if (current == null) {
			current = newNode;
			current.next = current;
			current.prev = current;
		}
		else {
			newNode.next = current;
			newNode.prev = current.prev;
			current.prev.next = newNode;
			current.prev = newNode;
		}
	}

	public void remove (T value) {
		Node<T> n = current.next;
		boolean equals = false;

		while (!(equals = n.value.equals(value)) && n != current)
			n = n.next;

		if (equals) {
			size -= 1;
			n.remove();
			if (size() == 0)
				current = null;
			else if (n == current)
				current = current.next;
		}

	}

	public boolean contains(T value) {
		for (T elem : this)
			if (elem.equals(value))
				return true;
		return false;
	}

	// sirve para iterar la lista sin fin
	public T next() {
		if (isEmpty())
			throw new IllegalStateException("Empty list");
		T value = current.value;
		current = current.next;
		return value;
	}

	// borra el último devuelto por next
	public void remove() {
		size -= 1;
		if (size() == 0)
			current = null;
		else
			current.prev.remove();
	}

	public boolean isEmpty() {
		return current == null;
	}

	public int size() {
		return size;
	}

	private static class Node<T> {
		private T value;
		private Node<T> next;
		private Node<T> prev;

		public Node(T v) {
			value = v;
		}

		public void remove() {
			prev.next = next;
			next.prev = prev;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node<T> n = current;
			private int i = size();

			@Override
			public boolean hasNext() {
				return i > 0;
			}

			@Override
			public T next() {
				T value = n.value;
				n = n.next;
				i -= 1;
				return value;
			}

		};
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		for (T value : this)
			str.append(value + " ");
		return new String(str);
	}
}