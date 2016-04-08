package tp3;

public class TestPriorityQueue {

	public static void main(String[] args) {
		PriorityQueue<Integer> queue = new PriorityList<>();
		
		queue.enqueue(8, 1);
		queue.enqueue(5, 6);
		queue.enqueue(4, 2);
		queue.enqueue(123, 1);
		
		while (!queue.isEmpty())
			System.out.println(queue.dequeue());
	}

}