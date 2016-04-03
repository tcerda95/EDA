package tp2;

import java.util.Random;

public class RoundRobin {
	private final static Random rand = new Random();
	private CircularList<Task> tasks;
	private Cronometer cronometer;
	private CPU cpu;

	public RoundRobin(CPU cpu) {
		this.cpu = cpu;
		cronometer = new Cronometer();
		tasks = new CircularList<Task>();
	}

	/**
	 * Comienza la simulación
	 * @param simulationTime duración de simulación
	 * @param taskGenerationTime cada cuanto tiempo se debe generar un nuevo trabajo
	 * @return lista de los trabajos restantes
	 * @throws InterruptedException
	 */
	public CircularList<Task> startSimulation(long simulationTime, long taskGenerationTime) throws InterruptedException {
		long nextTaskGenTime = taskGenerationTime;
		cronometer.start();

		while (cronometer.timeElapsed() < simulationTime) {
			while (nextTaskGenTime < cronometer.timeElapsed()) {
				nextTaskGenTime += taskGenerationTime;
				Task t = new DummyTask( rand.nextInt((int) simulationTime / 10) );
				System.out.println("Adding task: " + t);
				tasks.add(t);
			}

			if (!tasks.isEmpty()) {
				Task t = tasks.next();
				System.out.println("Time: " + cronometer.timeElapsed() +
						" Processing Task: " + t + "...");
				cpu.process(t);
				if (!t.needsProcessing()) {
					tasks.remove();
					System.out.println("Removing task: " + t);
				}
			}

		}

		return tasks;
	}
}
