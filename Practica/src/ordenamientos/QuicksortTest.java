package ordenamientos;

public class QuicksortTest {

	public static void main(String[] args) {
		Array arr = new Array(80000);
		arr.fillRandom(100000);

		System.out.println("Desordenado:");
		arr.print();

		long time = arr.quicksort();

		System.out.println("Ordenado:");
		arr.print();

		System.out.println("Tiempo que tardó en ordenar: " + time);

		System.out.println("Ordenado? " + arr.isOrdered());
	}

}
