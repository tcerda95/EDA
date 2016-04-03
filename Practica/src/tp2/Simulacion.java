package tp2;

public class Simulacion {
	private static long quantum = 100;  // milisegundos

	public static void main(String[] args) throws InterruptedException {
		long simTime = Integer.parseInt(args[0]); // milisegundos
		long taskGenTime = Integer.parseInt(args[1]); // milisegundos

		RoundRobin simulator = new RoundRobin(new CPU(quantum));
		CircularList<?> list = simulator.startSimulation(simTime, taskGenTime);
		System.out.println(list);
	}

}
