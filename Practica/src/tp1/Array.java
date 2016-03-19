package tp1;

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

	public Array intersection(Array other) {
		Array a = new Array(size);

		for(int i = 0; i < size; i++)
			if (a.contains(arr[i]) == -1 && other.contains(arr[i]) != -1)
				a.insert(arr[i]);
		return a;
	}

	public Array intersectionOrder(Array other) {
		Array a = new Array(size);

		int i = 0, j = 0;

		while (i < size && j < other.size) {
			// salteamos repetidos
			while (arr[i] == arr[i+1])
				i++;
			while (other.getNum(j) == other.getNum(j+1))
				j++;

			if (arr[i] < other.getNum(j))
				i++;
			else if (arr[i] > other.getNum(j))
				j++;
			else {
				a.insert(arr[i]);
				i++; j++;
			}
		}

		return a;
	}

	public int binSearch(int n) {
		return binSearchRec(0, size-1, n);
	}

	private int binSearchRec(int low, int high, int n) {
		if (high < low)
			return -1;
		int mid = (low + high) / 2;

		if (n > arr[mid])
			return binSearchRec(low+1, high, n);

		if (n < arr[mid])
			return binSearchRec(low, high-1, n);

		return mid;
	}

	public void fillRandom(int n) {

		for(int i = 0; i < arr.length; i++)
			arr[i] = rand.nextInt(n);

		size = arr.length;
	}


	// Se supone q los elementos est�n ordenados y no hay repetidos
	// El primer elemento es A sub 0.
	public boolean numInOrder() {
		if (arr[size-1] < 0)
			return false;
		return numInOrderRec(0, size-1);
	}

	private boolean numInOrderRec(int low, int high) {
		if (high < low)
			return false;
		int mid = (high + low) / 2;

		if (mid > arr[mid])
			return numInOrderRec(mid + 1, high);
		if (mid < arr[mid])
			return numInOrderRec(low, mid - 1);

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

	// O(N)
	// Mejor caso: el primer elemento es mayor al segundo ---> 1 sola comparaci�n
	// Peor caso: el arreglo esta ordenado ----> N-1 comparaciones
	public boolean isOrdered() {
		for (int i = 0; i < size - 1; i++)
			if (arr[i+1] < arr[i])
				return false;
		return true;
	}

	public void print() {
		for(int i = 0; i < size; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	// O(N^2)
	public boolean areDuplicates() {
		for (int i = 0; i < size-1; i++)
			for (int j = i+1; j < size; j++)
				if (arr[i] == arr[j])
					return true;
		return false;
	}

	// O(N)
	public boolean areDuplicatesOredered() {
		for (int i = 0; i < size-1; i++)
			if (arr[i] == arr[i+1])
				return true;
		return false;
	}

	private void swap(int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
