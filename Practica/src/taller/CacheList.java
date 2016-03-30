package taller;

public class CacheList<S,T> {
	private static class Node<S,T> {
		private T value;
		private S key;
		private Node<S,T> next;

		public Node(S k, T v, Node<S,T> n) {
			value = v;
			key = k;
			next = n;
		}
	}

	private Node<S,T> first;

	public void add(S key, T value) {
		first = add(key, value, first);
	}

	private Node<S,T> add(S key, T value, Node<S,T> n) {
		if (n == null)
			return new Node<S,T>(key,value,null);
		if (key == n.key)
			return new Node<S,T>(key,value,n.next);
		n.next = add(key, value, n.next);
		return n;
	}

	public T get(S key) {
		Node <S,T> prev = null, current = first;

		while(current != null && !current.key.equals(key)) {
			prev = current;
			current = current.next;
		}

		if (current == null)
			return null;

		if (prev != null) {
			prev.next = current.next;
			current.next = first;
			first = current;
		}

		return current.value;
	}
}
