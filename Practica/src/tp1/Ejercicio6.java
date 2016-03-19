package tp1;

public class Ejercicio6 {

	public static void main(String[] args) {
		Array a1 = new Array(12);
		Array a2 = new Array(12);

		a1.fillRandom(10);
		a2.fillRandom(10);

		System.out.println("a1: ");
		a1.print();

		System.out.println("a2: ");
		a2.print();

		System.out.println("Intersection: ");
		a1.intersection(a2).print();

		a1.bubbleSort();
		a2.bubbleSort();
		a1.intersection(a2).print();
	}

}
