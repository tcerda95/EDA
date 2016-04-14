package tp3;

public class SupermarketSimulation {

	public static void main(String[] args) {
		SupermarketQueues sq = new SupermarketQueues(5, 0.8, 7, 15);
		sq.simulate(200);
		System.out.println("Max queue length: " + sq.getMaxQueueLen());
		System.out.println("Average queue length: " + sq.getAverageQueueLen());
		System.out.println("Max waiting time: " + sq.getMaxWaitingTime());
		System.out.println("Average waiting time: " + sq.getAverageWaitingTime());
		System.out.println("Average cashier free time: " + sq.getAverageFreeTime());
	}

}
