package taller.calculator;

public class TokenFactory {
	private static final Token[] tokens = {
			OpenParen.getInstance(),
			CloseParen.getInstance()
	};

	public static Token getToken(char c) {
		for (Token token : tokens)
			if (token.getChar() == c)
				return token;
		return OperatorFactory.getOperator(c);
	}
}
