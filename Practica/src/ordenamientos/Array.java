package ordenamientos;

import java.util.Random;

public class Array {
	private int[] arr;
	private int size;
	static private Random rand = new Random();

	public Array(int N) {
		arr = new int[N];
		size = 0;
	}

	public int getNum(int i) {
		return arr[i];
	}

	public int getSize() {
		return size;
	}

	public void insert(int n) {
		arr[size++] = n;
	}

	public int contains(int n) {
		for (int i = 0; i < size; i++)
			if(arr[i] == n)
				return n;
		return -1;
	}

	public void fillRandom(int n) {

		for(int i = 0; i < arr.length; i++)
			arr[i] = rand.nextInt(n);

		size = arr.length;
	}

	// O(N)
	// Mejor caso: el primer elemento es mayor al segundo ---> 1 sola comparaci�n
	// Peor caso: el arreglo esta ordenado ----> N-1 comparaciones
	public boolean isOrdered() {
		for (int i = 0; i < size - 1; i++)
			if (arr[i+1] < arr[i])
				return false;
		return true;
	}

	// O(N^2)
	public long bubbleSort() {
		long startTime = System.currentTimeMillis();

		boolean swapped = true;

		for (int i = 0; i < size - 1 && swapped; i++) {
			swapped = false;
			for (int j = 0; j < size - i - 1; j++) {
				if (arr[j+1] < arr[j]) {
					swapped = true;
					swap(j, j+1);
				}
			}
		}

		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}

	// O(N^2)
	public long selectionSort() {
		long startTime = System.currentTimeMillis();

		int smallest;

		for(int i = 0; i < size-1; i++) {
			smallest = i;
			for (int j = i+1; j < size; j++)
				if (arr[j] < arr[smallest])
					smallest = j;
			swap(i, smallest);
		}

		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}

	// O(N^2)
	public long insertionSort() {
		long startTime = System.currentTimeMillis();

		int aux;
		int j;

		// i apunta al ultimo elemento ordenado
		for (int i = 0; i < size-1; i++) {
			aux = arr[i+1]; // elemento a ordenar
			for(j = i; j >= 0 && arr[j] > aux; j--)
				arr[j+1] = arr[j];
			arr[j+1] = aux;
		}

		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}

	//O(N^2)
	public long combSort() {
		long startTime = System.currentTimeMillis();

		double divisor = 1.3;
		int gap = (int) (size / divisor);
		boolean swapped = true;

		while (gap > 1 || swapped) {
			swapped = false;
			for (int j = 0; j+gap < size; j++)
				if (arr[j] > arr[j+gap]) {
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
	public long mergeSort() {
		long startTime = System.currentTimeMillis();
		int[] vec = new int[size];
		mergeSortRec(0, size-1, vec);
		long endTime = System.currentTimeMillis();
		return (endTime - startTime);
	}

	private void mergeSortRec(int low, int high, int[] vec) {
		if (low == high)
			return;

		int mid = (high+low)/2;
		mergeSortRec(low, mid, vec);
		mergeSortRec(mid+1,high,vec);

		for (int i = 0; i < (high-low)+1; i++)
			vec[i+low] = arr[i+low];

		merge(low, high, vec);
	}

	private void merge(int low, int high, int[] vec) {
		int mid = (high+low)/2;

		int i = low;
		int j = mid+1;
		int k = low;

		while(i <= mid && j <= high) {
			if (vec[i] < vec[j])
				arr[k++] = vec[i++];
			else
				arr[k++] = vec[j++];
		}

		while (i <= mid)
			arr[k++] = vec[i++];
		while (j <= high)
			arr[k++] = vec[j++];
	}

	public long quicksort() {
		long startTime = System.currentTimeMillis();

		quicksortRec(0, size-1);

		long endTime = System.currentTimeMillis();
		return (endTime - startTime);
	}

	private void quicksortRec(int low, int high) {
		if (high <= low)
			return;

		int p = rand.nextInt(high-low+1) + low; // pivot index
		int pivot = arr[p];

		swap(low, p);
		int i = low+1;
		int j = high;

		while (i <= j) { // asi i termina apuntando al lugar siguiente donde debe ir el pivote
			if (arr[i] > pivot)
				swap(i,j--);
			else
				i++;
		}

		swap(low, j);
		quicksortRec(low, j-1);
		quicksortRec(j+1, high);
	}

	public void print() {
		for(int i = 0; i < size; i++)
			System.out.println(arr[i] + " ");
		System.out.println();
	}

	private void swap(int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
