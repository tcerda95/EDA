package tp2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<T> implements Iterable<T> {
	private Node<T> first;
	private Node<T> last;
	private boolean reversed;
	private int size;

	public void add(T value) {
		if (reversed) {
			reversed = false;
			addFirst(value);
			reversed = true;
		}
		else {
			size += 1;

			Node<T> newNode = new Node<T>(value);
			if (isEmpty())
				first = last = newNode;
			else {
				last.next = newNode;
				newNode.prev = last;
				last = newNode;
			}
		}
	}

	public void addFirst(T value) {
		if (reversed) {
			reversed = false;
			add(value);
			reversed = true;
		}
		else {
			size += 1;

			Node<T> newNode = new Node<T>(value);
			if (isEmpty())
				first = last = newNode;
			else {
				newNode.next = first;
				first.prev = newNode;
				first = newNode;
			}
		}
	}

	public void remove(T value) {
		boolean wasReversed = reversed;
		reversed = false;	// para que sean coherentes removeFirst y removeLast

		if (first.value.equals(value))
			removeFirst();
		else if (last.value.equals(value))
			removeLast();
		else if (remove(value, first.next))
			size -= 1;

		reversed = wasReversed;
	}

	private boolean remove(T value, Node<T> n) {
		if (n == null)
			return false;
		if (n.value.equals(value)) {
			n.prev.next = n.next;
			n.next.prev = n.prev;
			return true;
		}
		else
			return remove(value, n.next);
	}

	public T removeFirst() {
		if (isEmpty())
			throw new NoSuchElementException("Empty list");

		T value;

		if (reversed) {
			reversed = false;
			value = removeLast();
			reversed = true;
		}
		else {
			size -= 1;
			value = first.value;
			first = first.next;
			if (isEmpty())
				last = null; // last era igual a first, el cual removimos
			else
				first.prev = null;
		}

		return value;
	}

	public T removeLast() {
		if (isEmpty())
			throw new NoSuchElementException("Empty list");

		T value;

		if (reversed) {
			reversed = false;
			value = removeFirst();
			reversed = true;
		}
		else {
			size -= 1;
			value = last.value;
			last = last.prev;
			if (isEmpty())
				first = null;
			else
				last.next = null;
		}

		return value;
	}

	public boolean contains(T value) {
		for (T elem : this)
			if (elem.equals(value))
				return true;
		return false;
	}

	public void reverse() {
		reversed = !reversed;
	}

	public boolean isEmpty() {
		return first == null || last == null;
	}

	@Override
	public Iterator<T> iterator() {
		if (reversed) {
			reversed = false;
			Iterator<T> iter = reversedIterator();
			reversed = true;
			return iter;
		}

		return new Iterator<T>() {
			private Node<T> current = first;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public T next() {
				T value = current.value;
				current = current.next;
				return value;
			}
		};
	}

	public Iterator<T> reversedIterator() {
		if (reversed) {
			reversed = false;
			Iterator<T> iter = iterator();
			reversed = true;
			return iter;
		}

		return new Iterator<T>() {
			Node <T> current = last;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public T next() {
				T value = current.value;
				current = current.prev;
				return value;
			}
		};
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();

		for (T value : this)
			str.append(value + " ");

		return new String(str);
	}

	private static class Node<T> {
		private T value;
		private Node<T> next;
		private Node<T> prev;

		public Node(T v) {
			value = v;
		}
	}
}
