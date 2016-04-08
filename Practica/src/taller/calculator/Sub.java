package taller.calculator;

public class Sub extends Operator {
	private static final Sub instance = new Sub();

	public static Sub getInstance() {
		return instance;
	}

	@Override
	public double apply(double a, double b) {
		return a - b;
	}

	@Override
	public char getChar() {
		return '-';
	}

	@Override
	public int precedence() {
		return 1;
	}
}
