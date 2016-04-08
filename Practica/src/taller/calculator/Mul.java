package taller.calculator;

public class Mul extends Operator {
	private static final Mul instance = new Mul();

	public static Mul getInstance() {
		return instance;
	}

	@Override
	public double apply(double a, double b) {
		return a*b;
	}

	@Override
	public char getChar() {
		return '*';
	}

	@Override
	public int precedence() {
		return 2;
	}

}
