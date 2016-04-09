package tp2;

public class CPU {
	private long processTime;

	public CPU(long pTime) {
		processTime = pTime;
	}

	// retorna true si el trabajo sigue requiriendo procesador
	public boolean process(Task t) throws InterruptedException {
		long sleepTime = t.getProcessTimeNeeded() > processTime ? processTime : t.getProcessTimeNeeded();
		Thread.sleep(sleepTime);
		t.process(processTime);
		return t.needsProcessing();
	}

}
