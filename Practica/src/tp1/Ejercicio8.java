package tp1;

public class Ejercicio8 {

	public static void main(String[] args) {
		Matrix m = new Matrix(10, 10);
		m.fillRandomColors();

		System.out.println("Antes del fill en 0,0:");
		m.print();

		m.fill(0, 0, false);
		System.out.println("Despues del fill negro en 0,0:");
		m.print();

		m.fill(0, 0, true);
		System.out.println("Despues del fill blanco en 0,0:");
		m.print();
	}

}
