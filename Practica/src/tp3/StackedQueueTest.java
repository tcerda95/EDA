package tp3;

public class StackedQueueTest {

	public static void main(String[] args) {
		StackedQueue<Integer> q = new StackedQueue<>();
		q.enqueue(34);
		q.enqueue(12);
		q.enqueue(234);
		q.enqueue(9);
		q.enqueue(2);
		q.enqueue(1234);

		while (!q.isEmpty())
			System.out.println(q.dequeue());

	}

}
