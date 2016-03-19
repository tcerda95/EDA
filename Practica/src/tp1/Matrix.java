package tp1;

import java.util.Random;

// true es blanco, false negro
public class Matrix {
	private boolean[][] matrix;
	private int rows;
	private int cols;
	static private Random rand = new Random();

	public Matrix(int r, int c) {
		matrix = new boolean[r][c];
		rows = r;
		cols = c;
	}

	public void setColor(int r, int c, boolean color) {
		matrix[r][c] = color;
	}

	public void fillRandomColors() {
		for(int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				matrix[i][j] = rand.nextBoolean();
	}

	public void fill(int r, int c, boolean color) {
		if (isInBounds(r, c) && matrix[r][c] != color) {
			matrix[r][c] = color;
			fill(r, c-1, color);
			fill(r+1, c, color);
			fill(r, c+1, color);
			fill(r-1, c, color);
		}
	}

	// las filas van de 0 a rows-1, las columnas de 0 a cols-1
	public boolean isInBounds(int r, int c) {
		return r < rows && r >= 0 && c < cols && c >= 0;
	}

	public void print() {
		for (int i = 0; i < rows; i++) {
			System.out.println();
			for (int j = 0; j < cols; j++) {
				System.out.print((matrix[i][j] == true ? 'W' : 'B') + " ");
			}
		}
		System.out.println();
	}

}
