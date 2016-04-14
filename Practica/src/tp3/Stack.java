package tp3;

public class Stack<T> {
	private static class Node<T> {
		private T value;
		private Node<T> next;

		public Node(T v, Node<T> n) {
			value = v;
			next = n;
		}
	}

	private Node<T> first;

	public void push(T value) {
		first = new Node<T>(value, first);
	}

	public T pop() {
		T value = first.value;
		first = first.next;
		return value;
	}

	public T peek() {
		return first.value;
	}

	public boolean isEmpty() {
		return first == null;
	}
}
