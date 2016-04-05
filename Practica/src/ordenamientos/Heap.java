package ordenamientos;

import java.util.Comparator;

@SuppressWarnings("unchecked")
public class Heap<T> {
	public static <E> void heapify(E[] array, int size, Comparator<E> cmp) {
		int parent;
		int current;
		E elem;
		for(int i = 1; i < size; i++) {
			parent = getParent(i);
			current = i;
			elem = array[i];
			while (current != 0 && cmp.compare(elem, array[parent]) > 0) {
				array[current] = array[parent];
				current = parent;
				parent = getParent(current);
			}
			array[current] = elem;
		}
	}

	public static <E> void sort(E[] array, int size, Comparator<E> cmp) {
		heapify(array, size, cmp);

		E last;
		int current;
		int max;
		for (int i = size-1; i > 0; i--) {
			last = array[i]; // guardo el último valor del árbol
			array[i] = array[0]; // en i va el máximo
			current = 0; // posición del último valor del árbol
			while (getLeft(current) < i &&
					cmp.compare(last, array[(max = getMaxChild(current, array, i, cmp))]) < 0) {
				array[current] = array[max];
				current = max;
			}
			array[current] = last;
		}
	}

	private static <E> int getMaxChild(int index, E[] array, int size, Comparator<E> cmp) {
		int leftChild = getLeft(index);
		int rightChild = getRight(index);
		if (leftChild >= size)
			throw new IllegalStateException("Childs out of bounds");
		if (rightChild == size)
			return leftChild;
		return cmp.compare(array[leftChild], array[rightChild]) > 0 ? leftChild : rightChild;
	}

	private static int getParent(int index) {
		return (index-1)/2;
	}

	private static int getLeft(int index) {
		return index*2+1;
	}

	private static int getRight(int index) {
		return index*2+2;
	}

/****************************** FIN FUNCIONES STATIC ***********************************/


	private Object[] array;
	private int size;
	private Comparator<T> cmp;

	public Heap(int n) {
		array = new Object[n];
	}

	//TODO: que no tenga repetidos
	public boolean insert(T elem) {
		if (size == array.length)
			return false;

		int parentIndex = getParent(size);
		int elemIndex = size++;

		while (elemIndex != 0 && cmp.compare(elem, (T) array[parentIndex]) > 0) {
			array[elemIndex] = array[parentIndex];
			elemIndex = parentIndex;
			parentIndex = getParent(parentIndex);
		}

		array[elemIndex] = elem;
		return true;
	}

	public T remove(int index) {
		if (index >= size || index < 0)
			return null;

		T elem = (T) array[index];

		T last = (T) array[--size];
		int max;

		while (getLeft(index) < size &&   // is in bounds
				cmp.compare(last, (T) array[ (max = getMaxChild(index)) ]) < 0) {
			array[index] = array[max]; // pisar el padre por el máximo hijo mayor que él
			index = max;	// bajo hacia el nodo que tenía el mayor hijo mayor
		}

		array[index] = last;
		return elem;
	}

	public boolean remove(T elem) {
		return remove(search(elem)) != null;
	}

	public int search(T elem) {
		return search(elem, 0);
	}

	private int search(T elem, int index) {
		int comp;

		if (index >= size || (comp = cmp.compare(elem, (T) array[index])) > 0)
			return -1;
		if (comp == 0)
			return index;

		int searchLeft = search(elem, getLeft(index));
		return searchLeft != -1 ? searchLeft : search(elem, getRight(index));
	}

	private int getMaxChild(int index) {
		int leftChild = getLeft(index);
		int rightChild = getRight(index);
		if (leftChild >= size)
			throw new IllegalStateException("Childs out of bounds");
		if (rightChild == size)
			return leftChild;
		return cmp.compare((T) array[leftChild], (T) array[rightChild]) > 0 ? leftChild : rightChild;
	}
}