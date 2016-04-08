package taller.calculator;

public class CloseParen extends Token {
	private static final CloseParen instance = new CloseParen();

	public static CloseParen getInstance() {
		return instance;
	}

	@Override
	public char getChar() {
		return ')';
	}

	@Override
	public int precedence() {
		return 3;
	}
}
