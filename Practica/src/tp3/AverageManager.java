package tp3;

public class AverageManager {
	private double sum;
	private int amount;

	public AverageManager(double initialSum, int initialAmount) {
		sum = initialSum;
		amount = initialAmount;
	}

	public AverageManager(double initialSum) {
		this(initialSum, 0);
	}

	public AverageManager() {
		this(0, 0);
	}

	public double add(double x) {
		amount += 1;
		sum += x;
		return getAverage();
	}

	public double getSum() {
		return sum;
	}

	public double getAverage() {
		return sum / amount;
	}

	public int getAmount() {
		return amount;
	}
}
