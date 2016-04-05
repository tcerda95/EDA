package ordenamientos;

import java.util.Comparator;
import java.util.Random;

public class HeapSortTest {

	public static void main(String[] args) {
		GenericArray<Integer> array = new GenericArray<>(100000);
		Comparator<Integer> cmp = (a, b) -> Integer.compare(a, b);
		Random rand = new Random();

		for (int i = 0; i < 50000; i++) {
			array.insert(rand.nextInt());
		}

		array.insert(123);
		array.insert(12);
		array.insert(1);
		array.insert(13);
		array.insert(231);
		array.insert(1223);
		array.insert(11111);
		array.insert(-32);
		array.insert(0);

		System.out.println(array.heapSort(cmp));
	}

}
