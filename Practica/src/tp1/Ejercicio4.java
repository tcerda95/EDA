package tp1;

public class Ejercicio4 {

	public static void main(String[] args) {
		Array arr = new Array(15);
		
		arr.insert(-20);
		arr.insert(-10);
		arr.insert(3);
		arr.insert(4);
		
		System.out.println(arr.numInOrder());
	}
}
