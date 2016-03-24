package ordenamientos;

public class SelectionSortTest {
	public static void main(String[] args) {
		Array arr = new Array(40000);
		arr.fillRandom(50000);

		System.out.println("Desordenado:");
		arr.print();

		long time = arr.selectionSort();

		System.out.println("Ordenado:");
		arr.print();

		System.out.println("Tiempo que tardó en ordenar: " + time);
	}

}
