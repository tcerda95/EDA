package tp3;

public class TestServer {

	public static void main(String[] args) {
		Server server = new Server(0.75, 2, 6);
		server.simulate(100);
		System.out.println("Average enqueued tasks: " + server.getAmountEnqueuedAvg());
		System.out.println("Average waiting time: " + server.getWaitingTimeAvg());
		System.out.println("Total tasks attended: " + server.getAttended());
	}

}
