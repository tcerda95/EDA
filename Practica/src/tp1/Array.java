package tp1;

import java.util.Random;

public class Array {
	private int[] arr;
	private int size;
	
	public Array(int N) {
		arr = new int[N];
		size = 0;
	}
	
	public void insert(int n) {
		arr[size++] = n;
	}
	
	public void fillRandom() {
		Random r = new Random();
		
		for(int i = 0; i < arr.length; i++)
			arr[i] = r.nextInt(10000);
		
		size = arr.length;
	}
	
	
	// Se supone q los elementos están ordenados y no hay repetidos
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
	// Mejor caso: el primer elemento es mayor al segundo ---> 1 sola comparación
	// Peor caso: el arreglo esta ordenado ----> N-1 comparaciones
	public boolean isOrdered() {
		for (int i = 0; i < size - 1; i++)
			if (arr[i+1] < arr[i])
				return false;
		return true;
	}
	
	public void printArr() {
		for(int i = 0; i < size; i++)
			System.out.println(arr[i]);
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
