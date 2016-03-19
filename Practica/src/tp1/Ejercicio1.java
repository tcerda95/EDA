package tp1;

public class Ejercicio1 {

	public static void main(String[] args) {
		Array arr = new Array(Integer.parseInt(args[0]));
		arr.fillRandom(10000);

		System.out.println("Desordenado:");
		arr.print();

		long time = arr.bubbleSort();

		System.out.println("Ordenado:");
		arr.print();

		System.out.println("Tiempo que tard� en ordenar: " + time);
	}

	// Complejidad O(N^2) --> al duplicar el tama�o, cuadruplica lo q tarda aprox

}
