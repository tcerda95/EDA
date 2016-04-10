package tp3;

public class AverageManager {
	private double sum;
	private int count;

	public AverageManager(double initialSum, int initialAmount) {
		sum = initialSum;
		count = initialAmount;
	}

	public AverageManager(double initialSum) {
		this(initialSum, 0);
	}

	public AverageManager() {
		this(0, 0);
	}

	public double add(double x) {
		count += 1;
		sum += x;
		return getAverage();
	}

	public int incrementCount() {
		return (count = count + 1);
	}

	public double getSum() {
		return sum;
	}

	public double getAverage() {
		return sum / count;
	}

	public int getCount() {
		return count;
	}
}
