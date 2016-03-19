package tp1;

public class Ejercicio3 {

	public static void main(String[] args) {
		Array arr = new Array(50);
		arr.insert(10);
		arr.insert(8);
		arr.insert(15);
		arr.insert(50);
		arr.insert(10);
		
		System.out.println("Hay duplicados desordenado " + arr.areDuplicates());
		
		arr.bubbleSort();
		
		System.out.println("Hay duplicados ordenado " + arr.areDuplicatesOredered());
	}

}
