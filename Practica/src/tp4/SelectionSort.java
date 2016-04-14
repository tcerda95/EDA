package tp4;

import java.util.Comparator;

public class SelectionSort {
	//O(N^2)
	public static <T> long otherSort(T[] arr, Comparator<T> cmp) {
		long currentTime = System.currentTimeMillis();

		for(int i = 0, j = arr.length-1; i < j; i++, j--) {
			int max = i, min = i;
			for(int k = i+1; k <= j; k++) {
				if (cmp.compare(arr[k],arr[min]) < 0)
					min = k;
				else if (cmp.compare(arr[k], arr[max]) > 0)
					max = k;
			}
			swap(arr, i,min);
			swap(arr, j,max);
		}

		long endTime = System.currentTimeMillis();
		return endTime - currentTime;
	}

	public static <T> long sort(T[] arr, Comparator<T> cmp) {
		long currentTime = System.currentTimeMillis();

		for(int i = 0; i < arr.length; i++) {
			int min = i;
			for(int k = i+1; k < arr.length; k++)
				if (cmp.compare(arr[k],arr[min]) < 0)
					min = k;
			swap(arr, i,min);
		}

		long endTime = System.currentTimeMillis();
		return endTime - currentTime;
	}


	private static <T> void swap(T[]arr, int i, int j) {
		T aux = arr[i];
		arr[i] = arr[j];
		arr[j] = aux;
	}
}
