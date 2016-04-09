package tp3;

import tp2.Task;

public class TimedTask extends Task {
	private int creationTime; // instante de creación

	public TimedTask(long pTime, int t) {
		super(pTime);
		creationTime = t;
	}

	public int getCreationTime() {
		return creationTime;
	}
}
