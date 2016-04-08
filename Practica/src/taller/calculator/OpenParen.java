package taller.calculator;

public class OpenParen extends Token {
	private static final OpenParen instance = new OpenParen();

	public static OpenParen getInstance() {
		return instance;
	}

	@Override
	public char getChar() {
		return '(';
	}

	@Override
	public int precedence() {
		return 3;
	}

}
