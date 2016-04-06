package ordenamientos;

import java.util.Comparator;

@SuppressWarnings("unchecked")
public class Heap<T> {
	public static <E> void heapify(E[] array, int size, Comparator<E> cmp) {
		for(int i = 1; i < size; i++)
			moveUp(array, i, cmp);
	}

	public static <E> void sort(E[] array, int size, Comparator<E> cmp) {
		heapify(array, size, cmp);

		E last;
		for (int i = size-1; i > 0; i--) {
			last = array[i]; // guardo el último valor del árbol
			array[i] = array[0]; // en i va el máximo
			array[0] = last;  // subo el último al primer lugar
			moveDown(array, 0, i, cmp); // lo bajo :( como mucho hasta i-1
		}
	}

	private static <E> int moveUp(E[] array, int i, Comparator<E> cmp) {
		int parent = getParent(i);
		E elem = array[i];
		while (i != 0 && cmp.compare(elem, array[parent]) > 0) {
			array[i] = array[parent];
			i = parent;
			parent = getParent(parent);
		}
		array[i] = elem;
		return i;
	}

	private static <E> int moveDown(E[] array, int i, int size, Comparator<E> cmp) {
		int max = getMaxChild(array, i, size, cmp);
		E elem = array[i];
		while (max != -1 && cmp.compare(elem, array[max]) < 0) {
			array[i] = array[max];
			i = max;
			max = getMaxChild(array, max, size, cmp);
		}
		array[i] = elem;
		return i;
	}

	private static <E> int getMaxChild(E[] array, int index, int size, Comparator<E> cmp) {
		int leftChild = getLeft(index);
		int rightChild = getRight(index);
		if (leftChild >= size)
			return -1; // out of bounds
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

		int elemIndex = size++;
		array[elemIndex] = elem;

		moveUpwards(elemIndex);
		return true;
	}

	public T remove(int index) {
		if (index >= size || index < 0)
			return null;

		T elem = (T) array[index];

		array[index] = array[--size];	// reemplazo el valor del nodo a borrar por el último nodo
		moveDown(index); 	// lo bajo :(

		return elem;
	}

	public boolean remove(T elem) {
		return remove(search(elem)) != null; // si encuentra el elemento, borra por índice
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

	private int moveDown(int index) {
		return moveDown((T[]) array, index, size, cmp);
	}

	private int moveUpwards(int elemIndex) {
		return moveUp((T[]) array, elemIndex, cmp);
	}

}