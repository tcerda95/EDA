package taller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class OrderedList<T> implements Iterable<T> {

	public static <E> OrderedList<E> otherMerge(List<OrderedList<E>> lists) {
		long startTime = System.currentTimeMillis();

		List<Node<E>> nodes = new ArrayList<>();
		OrderedList<E> newList = new OrderedList<E>(lists.get(0).cmp);

		for (OrderedList<E> list : lists)
			nodes.add(list.first);

		int merged = 0;
		int [] min = new int[nodes.size()]; // guardamos los índices de los nodos minimales
		E minValue;

		newList.first = new Node<E>(); // valor dummy para first
		Node<E> prev = newList.first;
		Node<E> current;

		while (merged < nodes.size()) {
			int j = 0;
			min[j++] = merged;
			minValue = nodes.get(min[0]).value;

			for (int i = merged+1; i < nodes.size(); i++) {
				E value = nodes.get(i).value;
				int comp = newList.cmp.compare(value, minValue);
				if (comp < 0) {
					j = 0;
					min[j++] = i;
					minValue = nodes.get(min[0]).value;
				}
				else if (comp == 0)
					min[j++] = i;
			}

			for (int i = 0; i < j; i++) {
				Node<E> next = nodes.get(min[i]).next;
				if (next == null) // mando las listas recorridas al comienzo del arreglo
					swap(nodes, merged++, min[i]);
				else	// avanzo el nodo
					nodes.set(min[i], next);
			}

			current = new Node<E>(minValue);
			prev.next = current;
			prev = current;
		}

		newList.first = newList.first.next; // asignamos el verdadero first

		long endTime = System.currentTimeMillis();
		System.out.println("Time elapsed, otherMerge: " + (endTime - startTime));
		return newList;
	}

	private static <E> void swap(List<E> list, int i, int j) {
		E tmp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, tmp);
	}

	public static <E> OrderedList<E> merge(List<OrderedList<E>> lists) {
		long startTime = System.currentTimeMillis();
		OrderedList<E> list = merge(lists, 0, lists.size()-1);
		long endTime = System.currentTimeMillis();

		System.out.println("Time elapsed, merge: " + (endTime - startTime));

		return list;
	}

	private static <E> OrderedList<E> merge (List<OrderedList<E>> lists, int left, int right) {
		if (left == right)
			return lists.get(left);

		int mid = (left+right) / 2;

		OrderedList<E> list1 = merge(lists, left, mid);
		OrderedList<E> list2 = merge(lists, mid+1, right);

		return merge(list1, list2);
	}

	public static <E> OrderedList<E> merge (OrderedList<E> a, OrderedList<E> b) {
		OrderedList<E> list = new OrderedList<E>(a.cmp);

		Node<E> nodeA = a.first;
		Node<E> nodeB = b.first;

		list.first = new Node<E>(); // un first vacio para que prev no comience null
		Node<E> prev = list.first;
		Node<E> current;

		while (nodeA != null && nodeB != null) {
			current = new Node<E>();

			int comp = list.cmp.compare(nodeA.value, nodeB.value);
			if (comp < 0) {
				current.value = nodeA.value;
				nodeA = nodeA.next;
			}
			else if (comp > 0) {
				current.value = nodeB.value;
				nodeB = nodeB.next;
			}
			else {
				current.value = nodeA.value;
				nodeA = nodeA.next;
				nodeB = nodeB.next;
			}

			prev.next = current;
			prev = current;
		}

		while (nodeA != null) {
			current = new Node<E>();
			current.value = nodeA.value;
			nodeA = nodeA.next;

			prev.next = current;
			prev = current;
		}

		while (nodeB != null) {
			current = new Node<E>();
			current.value = nodeB.value;
			nodeB = nodeB.next;

			prev.next = current;
			prev = current;
		}

		list.first = list.first.next; // borramos el first vacio
		return list;
	}

	private static class Node<T> {
		private T value;
		private Node<T> next;

		public Node() {
			this(null,null);
		}

		public Node(T v) {
			this(v, null);
		}

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

	public boolean isOrdered() {
		Node<T> current = first;
		while (current.next != null) {
			if (cmp.compare(current.value, current.next.value) >= 0)
				return false;
			current = current.next;
		}
		return true;
	}

	public void addIter(T value) {
		Node<T> prev = null;
		Node<T> current = first;
		int comp = 1;

		while(current != null && (comp = cmp.compare(current.value, value)) < 0) {
			prev = current;
			current = current.next;
		}

		Node<T> newNode = new Node<T>(value, current);

		if (prev == null && comp != 0)  // sin repetidos
			first = newNode;
		else if (comp != 0)
			prev.next = newNode;

	}

	public void add(T value) {
		first = add(value, first);
	}

	private Node<T> add(T value, Node<T> n) {
		int comp;
		if (n == null || (comp = cmp.compare(n.value, value)) > 0)
			return new Node<T>(value, n);
		else if (comp < 0) // sin repetidos
			n.next = add(value, n.next);
		return n;
	}

	public void remove(T value) {
		first = remove(value, first);
	}

	private Node<T> remove(T value, Node<T> n) {
		if (n == null || cmp.compare(n.value, value) > 0)
			return n;

		if (n.value.equals(value))
			return n.next;

		n.next = remove (value, n.next);
		return n;
	}

	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node<T> current = first;

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

	public boolean isEmpty() {
		return first != null;
	}

	public void fillRandom(int count, Randomizer<T> rand) {
		for (int i = 0; i < count; i++)
			addIter(rand.next());
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();

		for (T value : this)
			str.append(value.toString() + " ");

		return new String(str);
	}
}
