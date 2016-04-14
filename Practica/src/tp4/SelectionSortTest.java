package tp4;

import java.util.Comparator;
import java.util.Random;

public class SelectionSortTest {

	public static void main(String[] args) {
		Comparator<Integer> cmp = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		};
		Random rand = new Random();
		int size = 16000;
		Integer[] arr = new Integer[size];
		for (int i = 0; i < size; i++)
			arr[i] = rand.nextInt(100000);
		long sortingTime = SelectionSort.otherSort(arr, cmp);
		print(arr);
		System.out.println("Sorting time: " + sortingTime);
	}

	private static void print(Integer[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
			if (i % 15 == 0)
				System.out.println();
		}
	}

}
