package parciales;

public class TestSortedFastList {

	public static void main(String[] args) {
		SortedFastList<Integer> list = new SortedFastList<>((a,b) -> a.compareTo(b));
		list.add(5);
		list.add(8);
		list.add(1);
		list.add(0);
		list.add(4);
		list.add(10);

		list.print();
	}

}
