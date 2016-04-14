package tp3;

public class StackedQueue<T> {
	private Stack<T> enqueueStack;
	private Stack<T> dequeueStack;
	private int size;

	public StackedQueue() {
		size = 0;
		enqueueStack = new Stack<>();
		dequeueStack = new Stack<>();
	}

	public void enqueue(T value) {
		size += 1;
		if (enqueueStack.isEmpty())
			transfer(enqueueStack, dequeueStack);
		enqueueStack.push(value);
	}

	public T dequeue() {
		if (isEmpty())
			throw new IllegalStateException("Empty queue");

		size -= 1;
		if (dequeueStack.isEmpty())
			transfer(dequeueStack, enqueueStack);
		return dequeueStack.pop();
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	private void transfer(Stack<T> dest, Stack<T> src) {
		while (!src.isEmpty())
			dest.push(src.pop());
	}
}
