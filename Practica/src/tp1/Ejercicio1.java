package tp1;

public class Ejercicio1 {

	public static void main(String[] args) {
		Array arr = new Array(Integer.parseInt(args[0]));
		arr.fillRandom();
		
		System.out.println("Desordenado:");
		arr.printArr();
		
		long time = arr.bubbleSort();
		
		System.out.println("Ordenado:");
		arr.printArr();
		
		System.out.println("Tiempo que tardó en ordenar: " + time);
	}

	// Complejidad O(N^2) --> al duplicar el tamaño, cuadruplica lo q tarda aprox
	
}
