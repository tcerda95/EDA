package tp2;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class ListSet<T> implements Set<T>{
	private Node<T> first;
	private int size;

	@Override
	public boolean add(T value) {
		boolean contains = contains(value);

		if (!contains) {
			first = new Node<T>(value, first);
			size += 1;
		}

		return contains;
	}

	@Override
	public boolean contains(Object value) {
		for(T currentValue : this)
			if (value.equals(currentValue))
				return true;
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> arg0) {
		int oldSize = size();
		for (T value : arg0)
			add(value);
		return size != oldSize;
	}

	@Override
	public void clear() {
		first = null;
		size = 0;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		for (Object value : arg0)
			if (!contains(value))
				return false;
		return true;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public Iterator<T> iterator() {

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

	@Override
	public boolean remove(Object arg0) {
		int oldSize = size();
		first = remove(arg0, first);
		return size != oldSize;
	}

	private Node<T> remove(Object value, Node<T> n) {
		if (n != null) {
			if (n.value.equals(value)) {
				size -= 1;
				return n.next;
			}
			n.next = remove(value, n.next);
		}
		return n;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		boolean modified = false;

		for (Object value : arg0)
			modified = remove(value) || modified;

		return modified;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		boolean modified = false;

		Node<T> prev = null;
		Node<T> current = first;

		while (current != null) {
			if (!arg0.contains(current.value)) {
				size -= 1;
				modified = true;
				if (prev == null)
					first = current.next;
				else
					prev.next = current.next;
			}
			else
				prev = current;

			current = current.next;
		}

		return modified;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size()];
		int i = 0;
		for (T value : this)
			array[i++] = value;
		return array;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E> E[] toArray(E[] arg0) {
		E[] array = arg0;
		if (arg0.length < size())
			array = (E[]) Array.newInstance(arg0.getClass(), size());

		int i = 0;
		for (T value : this) {
			array[i++] = (E) value;
		}

		return array;
	}

	public ListSet<T> getReversed() {
		ListSet<T> list = new ListSet<T>();
		for (T value : this)
			list.add(value);
		return list;
	}

	public void reverse() {
		Node<T> prev = null;
		Node<T> current = first;
		Node<T> next;

		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}

		first = prev;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();

		for (T value : this)
			str.append(value.toString() + " ");

		return new String(str);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (o == null || !(o instanceof Set))
			return false;
		Set<?> set = (Set<?>)o;
		if (size() != set.size())
			return false;
		return set.containsAll(this) && this.containsAll(set);
	}

	@Override
	public int hashCode() {
		return 1;
	}

	private static class Node<T> {
		private T value;
		private Node<T> next;

		public Node(T v, Node<T> n) {
			value = v;
			next = n;
		}
	}
}
