package tp2;

public class TestBaseList {

	public static void main(String[] args) {
		BaseList<Integer> list = new BaseList<Integer>();
		list.add(2);
		list.add(5);
		list.add(7);
		list.add(123);

		System.out.println(list);
		list = list.reverseRec();
		System.out.println(list);

		BaseList<Integer> list2 = list.map(a -> 2*a);

		System.out.println(list2.contains(4));
		System.out.println(list2.contains(25));
		System.out.println(list2.contains(49));

		System.out.println(list2.toString());
		System.out.println(list.inject((a, b) -> a+b, 15));
		System.out.println(list.count(a -> a > 5));
		System.out.println(list.filter(a -> a >= 5));
	}

}
