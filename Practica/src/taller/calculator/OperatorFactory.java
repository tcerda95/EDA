package taller.calculator;

public class OperatorFactory {
	private static final Operator[] operators = {
			Sum.getInstance(),
			Sub.getInstance(),
			Mul.getInstance(),
			Div.getInstance()
	};

	public static Operator getOperator(char c) {
		for (Operator op : operators)
			if (op.getChar() == c)
				return op;
		return null;
	}

	public static Operator getOperator(String str) {
		return getOperator(str.charAt(0));
	}
}
