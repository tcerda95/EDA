package tp2;

public class CircularListTest {

	public static void main(String[] args) throws InterruptedException {
		CircularList<Integer> list = new CircularList<>();

		list.add(11);
		list.add(22);
		list.add(55);
		list.add(89);

		while (!list.isEmpty()) {
			System.out.println(list.next());
			list.remove();
		}

		list.add(33);

		System.out.println(list);
	}

}
