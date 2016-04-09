package tp3;

import java.util.Random;

public class Server {
	private static final Random rand = new Random();

	private int attended;  // cantidad de trabajos atendidos
	private TimedTask currentTask;  // trabajo actualmente atendido
	private AverageManager waitingTimeAvg; // promedio de tiempo de espera
	private AverageManager amountEnqueuedAvg;   // cantidad promedio de trabajos en cola

	private Queue<TimedTask> queue;

	private double arrivalProbability;
	private int minServiceTime;
	private int maxServiceTime;

	public Server(double arriveProb, int minTime, int maxTime) {
		arrivalProbability = arriveProb;
		minServiceTime = minTime;
		maxServiceTime = maxTime;
		queue = new Queue<>();
		waitingTimeAvg = new AverageManager();
		amountEnqueuedAvg = new AverageManager();
	}

	public Queue<TimedTask> simulate(int simulationTime) {

		for (int t = 0; t < simulationTime; t++) {
			if (taskArrived())  // cada unidad de tiempo puede que llegue un proceso nuevo
				queue.enqueue(generateTask(t));

			if (!isAttending() && !queue.isEmpty()) {
				currentTask = queue.dequeue();
				int waitingTime = t - currentTask.getCreationTime();
				waitingTimeAvg.add(waitingTime);
			}

			if (isAttending()) {
				currentTask.process(1);  // se procesa una unidad de tiempo
				if (!currentTask.needsProcessing())
					attended++;
			}

			amountEnqueuedAvg.add(queue.size());
		}

		return queue;
	}

	public double getAmountEnqueuedAvg() {
		return amountEnqueuedAvg.getAverage();
	}

	public double getWaitingTimeAvg() {
		return waitingTimeAvg.getAverage();
	}

	public int getAttended() {
		return attended;
	}

	private boolean isAttending() {
		return currentTask != null && currentTask.needsProcessing();
	}

	private boolean taskArrived() {
		return rand.nextDouble() < arrivalProbability;
	}

	private int generateServiceTime() {
		return rand.nextInt(maxServiceTime - minServiceTime + 1) + minServiceTime;
	}

	private TimedTask generateTask(int waitingTime) {
		return new TimedTask(generateServiceTime(), waitingTime);
	}

}
