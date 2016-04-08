package taller.calculator;

public class Sum extends Operator {
	private static final Sum instance = new Sum();

	public static Sum getInstance() {
		return instance;
	}

	@Override
	public double apply(double a, double b) {
		return a + b;
	}

	@Override
	public char getChar() {
		return '+';
	}

	@Override
	public int precedence() {
		return 1;
	}
}
