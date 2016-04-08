package taller.calculator;

import java.util.ArrayDeque;
import java.util.Deque;

public class Evaluator {

	private static String infixToPostfix(String str) {
		Deque<Token> tokens = new ArrayDeque<>();
		StringBuffer buffer = new StringBuffer();
		Token token;

		for (char c : str.toCharArray()) {
			if (Character.isDigit(c))
				buffer.append(c);
			else {
				token = TokenFactory.getToken(c);
				if (token.getChar() == ')') {
					char ch;
					while ((ch = tokens.pop().getChar()) != '(')
						buffer.append(ch);
				}
				else  {
					if (!tokens.isEmpty() && token.precedence() <= tokens.peek().precedence())
						while (!tokens.isEmpty() && tokens.peek().getChar() != '(')
							buffer.append(tokens.pop());
					tokens.push(token);
				}
			}
		}

		while (!tokens.isEmpty())
			buffer.append(tokens.pop());

		return new String(buffer);
	}

	private static double evaluate(String infix) {
		return eval(infixToPostfix(infix));
	}

	private static double eval(String str) {
		Deque<Double> stack = new ArrayDeque<>();
		Operator op;
		double a, b;
		for (char c : str.toCharArray()) {
			if (Character.isDigit(c))
				stack.push(Double.valueOf(c - '0'));
			else {
				op = OperatorFactory.getOperator(c);
				b = stack.pop();
				a = stack.pop();
				stack.push(op.apply(a, b));
			}
		}
		return stack.pop();
	}

	public static void main(String[] args) {
		System.out.println(evaluate("5/2*4+3"));
	}
}