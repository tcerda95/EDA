package tp3;

public class ListStack<T> {
	private static class Node<T> {
		private T value;
		private Node<T> next;

		public Node (T v, Node<T> n) {
			value = v;
			next = n;
		}
	}

	private Node<T> first;

	public void push(T value) {
		first = new Node<T>(value, first);
	}

	public T pop() {
		if (isEmpty())
			throw new IllegalStateException("Empty stack");
		T value = first.value;
		first = first.next;
		return value;
	}

	public T peek() {
		if (isEmpty())
			throw new IllegalStateException("Empty stack");
		return first.value;
	}

	public boolean isEmpty() {
		return first == null;
	}
}
