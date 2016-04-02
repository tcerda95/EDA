package tp2;

public class DoubleLinkedListTest {

	public static void main(String[] args) {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

		list.add(21);
		list.add(87);
		list.add(212);
		list.add(11);

		list.reverse();

		list.addFirst(11111);

		list.removeLast();

		System.out.println(list);
		System.out.println(list.size());
	}

}
