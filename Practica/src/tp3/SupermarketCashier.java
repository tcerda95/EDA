package tp3;

public class SupermarketCashier {
	private static final int timeInterval = 1; // una unidad de tiempo
	private String name;

	private Queue<TimedTask> queue = new Queue<>();
	private int freeTime;
	private AverageManager avgWaitingTime = new AverageManager();
	private AverageManager avgQueueLength = new AverageManager();
	private TimedTask currentCustomer;

	private int currentTime; // dudoso

	public SupermarketCashier() {
		this("Anonymous", 0);
	}

	public SupermarketCashier(String n) {
		this(n, 0);
	}

	public SupermarketCashier(String n, int time) {
		name = n;
		currentTime = time;
	}

	public String getName() {
		return name;
	}

	public int getQueueLen() {
		return queue.size() + (isFree() ? 0 : 1); // longitud de la cola + 1 si está atendiendo
	}

	public boolean isFree() {
		return currentCustomer == null || !currentCustomer.needsProcessing();
	}

	public void enqueue(TimedTask customer) {
		queue.enqueue(customer);
	}

	public void work() {
		if (isFree() && !queue.isEmpty()) {
			currentCustomer = queue.dequeue();
			int waitingTime = currentTime - currentCustomer.getCreationTime();
			avgWaitingTime.add(waitingTime);
			currentCustomer.process(timeInterval);
		}
		else if (isFree() && queue.isEmpty())
			freeTime += timeInterval;
		else
			currentCustomer.process(timeInterval);

		currentTime += timeInterval;
		avgQueueLength.add(getQueueLen());
	}

	public int getFreeTime() {
		return freeTime;
	}

	public double getAverageWaitingTime() {
		return avgWaitingTime.getAverage();
	}

	public int getMaxWaitingTime() {
		return (int) avgWaitingTime.getMax();
	}

	public double getAverageQueueLen() {
		return avgQueueLength.getAverage();
	}

	public int getMaxQueueLen() {
		return (int) avgQueueLength.getMax();
	}
}