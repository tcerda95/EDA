package taller.calculator;

public class Div extends Operator {
	private static final Div instance = new Div();

	public static Div getInstance() {
		return instance;
	}

	@Override
	public double apply(double a, double b) {
		return a / b;
	}

	@Override
	public char getChar() {
		return '/';
	}

	@Override
	public int precedence() {
		return 2;
	}
}
