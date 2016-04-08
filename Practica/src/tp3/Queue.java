package tp3;

public class Queue<T> {
	private Node<T> first;
	private Node<T> last;
	private int size;

	public void enqueue(T value) {
		size += 1;
		Node<T> n = new Node<T>(value);
		
		if (isEmpty()) {
			first = n;
			last = n;
		}
		else {
			last.next = n;
			last = n;
		}
	}
	
	public T dequeue() {
		size -= 1;
		if (isEmpty())
			throw new IllegalStateException("Empty queue");
		T value = first.value;
		first = first.next;
		return value;
	}

	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return size;
	}
	
	private static class Node<T> {
		private T value;
		private Node<T> next;
		
		public Node(T v) {
			value = v;
		}
	}
	

}
