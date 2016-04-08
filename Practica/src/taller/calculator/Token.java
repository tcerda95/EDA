package taller.calculator;

public abstract class Token {

	public abstract char getChar();
	public abstract int precedence();

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (o == null || !o.getClass().equals(this.getClass()))
			return false;
		return this.getChar() == ((Token)o).getChar();
	}

	@Override
	public String toString() {
		return Character.toString(getChar());
	}
}
