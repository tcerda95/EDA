package tp1;

public class Ejercicio2 {
	public static void main(String[] args) {
		Array arr = new Array(10000);
		arr.fillRandom();
		
		System.out.println(arr.isOrdered());
		
		arr.bubbleSort();
		System.out.println(arr.isOrdered());
	}
}
