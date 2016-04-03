package tp2;

public abstract class Task {
	private long processTimeNeeded;

	public Task(long pTime) {
		processTimeNeeded = pTime;
	}

	public long getProcessTimeNeeded() {
		return processTimeNeeded;
	}

	public boolean needsProcessing() {
		return processTimeNeeded > 0;
	}

	public long process(long processTime) {
		processTimeNeeded -= processTime;
		if (processTimeNeeded < 0)
			processTimeNeeded = 0;
		return processTimeNeeded;
	}

}
