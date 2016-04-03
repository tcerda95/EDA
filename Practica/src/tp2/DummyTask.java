package tp2;

public class DummyTask extends Task {
	private static int nextID = 0;
	private final int id;

	public DummyTask(long pTime) {
		super(pTime);
		id = nextID++;
	}

	@Override
	public String toString() {
		return id + "(" + getProcessTimeNeeded() + ")";
	}

}
