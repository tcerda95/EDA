package tp3;

import java.util.Random;

public class SupermarketQueues {
	private static final int timeInterval = 1;
	private static final Random rand = new Random();

	private int cashierAmount;
	private SupermarketCashier[] cashiers;
	private double arrivalProbabilty;
	private int minServiceTime;
	private int maxServiceTime;

	private int maxQueueLen;
	private int maxWaitingTime;
	private double avgQueueLen;
	private double avgWaitingTime;
	private double avgFreeTime;

	public SupermarketQueues(int N, double arrivalProb, int minService, int maxService) {
		cashierAmount = N;
		cashiers = new SupermarketCashier[N];
		arrivalProbabilty = arrivalProb;
		minServiceTime = minService;
		maxServiceTime = maxService;
	}

	public SupermarketCashier[] simulate(int simulationTime) {
		for (int i = 0; i < cashierAmount; i++)
			cashiers[i] = new SupermarketCashier();

		for (int t = 0; t < simulationTime; t += timeInterval) {
			if (customerArrived()) {
				TimedTask customer = generateCustomer(t);
				accomodateCustomer(customer);
			}
			putCashiersToWork();
			printQueueLengths(t);
		}

		getStatistics();
		return cashiers;
	}

	private void getStatistics() {
		AverageManager avgWait = new AverageManager();
		AverageManager avgFree = new AverageManager();
		AverageManager avgLen = new AverageManager();
		maxQueueLen = 0;
		maxWaitingTime = 0;

		for (SupermarketCashier cs : cashiers) {
			avgWait.add(cs.getAverageWaitingTime());
			avgFree.add(cs.getFreeTime());
			avgLen.add(cs.getAverageQueueLen());

			int len = cs.getMaxQueueLen();
			if (len >maxQueueLen)
				maxQueueLen = len;

			int wait = cs.getMaxWaitingTime();
			if (wait > maxWaitingTime)
				maxWaitingTime = wait;
		}

		avgWaitingTime = avgWait.getAverage();
		avgFreeTime = avgFree.getAverage();
		avgQueueLen = avgLen.getAverage();
	}

	public double getAverageFreeTime() {
		return avgFreeTime;
	}

	public double getAverageQueueLen() {
		return avgQueueLen;
	}

	public double getAverageWaitingTime() {
		return avgWaitingTime;
	}

	public int getMaxWaitingTime() {
		return maxWaitingTime;
	}

	public int getMaxQueueLen() {
		return maxQueueLen;
	}

	private void printQueueLengths(int t) {
		System.out.println("t = " + t);
		for (SupermarketCashier cs : cashiers)
			System.out.print(cs.getQueueLen() + " ");
		System.out.println();
	}

	private void putCashiersToWork() {
		for (SupermarketCashier sc : cashiers)
			sc.work();
	}

	private void accomodateCustomer(TimedTask customer) {
		int min = 0;
		boolean accomodated = false;
		for (int i = 0; i < cashierAmount && !accomodated; i++) {
			if (cashiers[i].isFree()) {
				cashiers[i].enqueue(customer);
				accomodated = true;
			}
			else if (cashiers[i].getQueueLen() < cashiers[min].getQueueLen())
				min = i;
		}

		if (!accomodated)
			cashiers[min].enqueue(customer);
	}

	private boolean customerArrived() {
		return rand.nextDouble() < arrivalProbabilty;
	}

	private int generateServiceTime() {
		return rand.nextInt(maxServiceTime - minServiceTime + 1) + minServiceTime;
	}

	private TimedTask generateCustomer(int t) {
		return new TimedTask(generateServiceTime(), t);
	}
}
