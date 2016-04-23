package taller;

public class LuchoList<K, V> {
	private static class Node<K, V> {
		private K key;
		private V value;
		private int accessed;
		private Node<K, V> next;

		public Node(K k, V v) {
			key = k;
			value = v;
		}
	}

	private Node<K, V> first, last; // first == leastAccessed, last == mostAccessed

	public K getMostAccessed() {
		return last.key;
	}

	public void removeLeastAccessed() {
		first = first.next;
	}

	public V get(K key) {
		Node<K, V> prev = null;
		Node<K, V> current = first;

		while (current != null && !current.key.equals(key)) {
			prev = current;
			current = current.next;
		}

		if (current == null)
			return null;

		current.accessed += 1;
		swapLessAccessed(prev, current);

		return current.value;
	}

	public void put(K key, V value) {
		if (first == null)
			first = last = new Node<K, V>(key, value);
		else {
			Node<K, V> prev = null;
			Node<K, V> current = first;

			while (current != null && !current.key.equals(key)) {
				prev = current;
				current = current.next;
			}

			if (current == null) {
				Node<K, V> n = new Node<K, V>(key, value);
				n.next = first;
				first = n;
			}
			else {
				current.accessed += 1;
				current.value = value;
				swapLessAccessed(prev, current);
			}
		}
	}

	private void swapNext(Node<K, V> prev, Node<K ,V> n) {
		if (n.next != null) {
			Node<K, V> next = n.next;
			if (prev != null)
				prev.next = next;
			n.next = next.next;
			next.next = n;
		}
	}

	private void swapLessAccessed(Node<K, V> prev, Node<K, V> n) {
		if (prev == null && n.next != null && n.accessed > n.next.accessed) {
			first = n.next;
			swapNext(prev, n);
			prev = first;
		}

		while (n.next != null && n.accessed > n.next.accessed) {
			swapNext(prev, n);
			prev = prev.next; // actualiza prev, n sigue siendo n
		}

		if (n.next == null) // estoy al final
			last = n;
	}
}
