package tp3;

public interface PriorityQueue<T> {
	public void enqueue(T elem, int priority);
	public T dequeue();
	public boolean isEmpty();
}
