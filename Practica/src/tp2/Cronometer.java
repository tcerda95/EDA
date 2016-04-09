package tp2;

public class Cronometer {
	private boolean stopped;
	private long startTime;
	private long endTime;

	public Cronometer() {
		stopped = true;
	}

	public void start() {
		stopped = false;
		startTime = System.currentTimeMillis();
	}

	public void reset() {
		startTime = System.currentTimeMillis();
	}

	public long timeElapsed() {
		if (stopped)
			return endTime - startTime;
		return System.currentTimeMillis() - startTime;
	}

	public double secondsElapsed() {
		return timeElapsed() / 1000.0;
	}

	public void stop() {
		stopped = true;
		endTime = System.currentTimeMillis();
	}
}
