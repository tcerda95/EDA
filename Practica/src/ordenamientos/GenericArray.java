package ordenamientos;

import java.util.Comparator;
import java.util.Random;

@SuppressWarnings("unchecked")
public class GenericArray<T> {
	private Object[] arr;
	private int size;
	static private Random rand = new Random();

	public GenericArray(int n) {
		arr = new Object[n];
		size = 0;
	}

	public T get(int i) {
		return (T) arr[i];
	}

	public int getSize() {
		return size;
	}

	public void insert(T n) {
		arr[size++] = n;
	}

	public int contains(T n) {
		for (int i = 0; i < size; i++)
			if(arr[i].equals(n))
				return i;
		return -1;
	}

//	public void fillRandom(int n) {
//
//		for(int i = 0; i < arr.length; i++)
//			arr[i] = rand.nextInt(n);
//
//		size = arr.length;
//	}

	// O(N)
	// Mejor caso: el primer elemento es mayor al segundo ---> 1 sola comparaci�n
	// Peor caso: el arreglo esta ordenado ----> N-1 comparaciones
	public boolean isOrdered(Comparator<T> cmp) {
		for (int i = 0; i < size - 1; i++)
			if (cmp.compare((T) arr[i+1], (T) arr[i]) < 0)
				return false;
		return true;
	}

	// O(N^2)
	public long bubbleSort(Comparator<T> cmp) {
		long startTime = System.currentTimeMillis();

		boolean swapped = true;

		for (int i = 0; i < size - 1 && swapped; i++) {
			swapped = false;
			for (int j = 0; j < size - i - 1; j++) {
				if (cmp.compare((T) arr[j+1], (T) arr[j]) < 0) {
					swapped = true;
					swap(j, j+1);
				}
			}
		}

		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}

	// O(N^2)
	public long selectionSort(Comparator<T> cmp) {
		long startTime = System.currentTimeMillis();

		int smallest;

		for(int i = 0; i < size-1; i++) {
			smallest = i;
			for (int j = i+1; j < size; j++)
				if (cmp.compare((T) arr[j], (T) arr[smallest]) < 0)
					smallest = j;
			swap(i, smallest);
		}

		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}

	// O(N^2)
	public long insertionSort(Comparator<T> cmp) {
		long startTime = System.currentTimeMillis();

		T aux;
		int j;

		// i apunta al ultimo elemento ordenado
		for (int i = 0; i < size-1; i++) {
			aux = (T) arr[i+1]; // elemento a ordenar
			for(j = i; j >= 0 && cmp.compare((T) arr[j], (T) aux) > 0; j--)
				arr[j+1] = arr[j];
			arr[j+1] = aux;
		}

		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}

	//O(N^2)
	public long combSort(Comparator<T> cmp) {
		long startTime = System.currentTimeMillis();

		double divisor = 1.3;
		int gap = (int) (size / divisor);
		boolean swapped = true;

		while (gap > 1 || swapped) {
			swapped = false;
			for (int j = 0; j+gap < size; j++)
				if (cmp.compare((T) arr[j], (T) arr[j+gap]) > 0) {
					swap(j, j+gap);
					swapped = true;
				}
			if (gap > 1)
				gap = (int) (gap / divisor);
		}

		long endTime = System.currentTimeMillis();
		return (endTime - startTime);
	}


	//O(N*log N)
	public long mergeSort(Comparator<T> cmp) {
		long startTime = System.currentTimeMillis();
		Object[] vec = new Object[size];
		mergeSortRec(0, size-1, vec, cmp);
		long endTime = System.currentTimeMillis();
		return (endTime - startTime);
	}

	private void mergeSortRec(int low, int high, Object[] vec, Comparator<T> cmp) {
		if (low == high)
			return;

		int mid = (high+low)/2;
		mergeSortRec(low, mid, vec, cmp);
		mergeSortRec(mid+1, high, vec, cmp);

		for (int i = 0; i < (high-low)+1; i++)
			vec[i+low] = arr[i+low];

		merge(low, high, vec, cmp);
	}

	private void merge(int low, int high, Object[] vec, Comparator<T> cmp) {
		int mid = (high+low)/2;

		int i = low;
		int j = mid+1;
		int k = low;

		while(i <= mid && j <= high) {
			if (cmp.compare((T) vec[i], (T) vec[j]) < 0)
				arr[k++] = vec[i++];
			else
				arr[k++] = vec[j++];
		}

		while (i <= mid)
			arr[k++] = vec[i++];
		while (j <= high)
			arr[k++] = vec[j++];
	}

	public long quicksort(Comparator<T> cmp) {
		long startTime = System.currentTimeMillis();

		quicksortRec(0, size-1, cmp);

		long endTime = System.currentTimeMillis();
		return (endTime - startTime);
	}

	private void quicksortRec(int low, int high, Comparator<T> cmp) {
		if (high <= low)
			return;

		int p = rand.nextInt(high-low+1) + low; // pivot index
		T pivot = (T) arr[p];

		swap(low, p);
		int i = low+1;
		int j = high;

		while (i <= j) { // asi j termina apuntando al lugar donde debe ir el pivote
			if (cmp.compare((T) arr[i], pivot) > 0)
				swap(i,j--);
			else
				i++;
		}

		swap(low, j);
		quicksortRec(low, j-1, cmp);
		quicksortRec(j+1, high, cmp);
	}

	public long heapSort(Comparator<T> cmp) {
		long startTime = System.currentTimeMillis();

		Heap.sort((T[]) arr, size, cmp);

		long endTime = System.currentTimeMillis();
		return (endTime - startTime);
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		for(int i = 0; i < size; i++)
			str.append(arr[i] + " ");
		return new String(str);
	}

	private void swap(int i, int j) {
		Object tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
