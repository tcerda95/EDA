package tp4;

import java.util.Random;

public class RadixSortTest {

	public static void main(String[] args) {
		Random rand = new Random();
		int size = 160000;
		int[] arr = new int[size];
		for (int i = 0; i < size; i++)
			arr[i] = rand.nextInt(10000);
		long sortingTime = RadixSort.sort(arr);
		print(arr);
		System.out.println("Sorting time: " + sortingTime);
	}

	private static void print(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
			if (i % 15 == 0)
				System.out.println();
		}
	}

}
