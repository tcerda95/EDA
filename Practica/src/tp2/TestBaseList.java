package tp2;

public class TestBaseList {

	public static void main(String[] args) {
		BaseList<Integer> list = new BaseList<Integer>();
		list = list.add(2);
		list = list.add(5);
		list = list.add(7);

		System.out.println(list.contains(1234));

		BaseList<Integer> list2 = list.map(new Function<Integer, Integer>() {
			public Integer eval(Integer a) {
				return a*a;
			}
		});

		System.out.println(list.toString());

		System.out.println(list2.contains(1));
		System.out.println(list2.contains(25));
		System.out.println(list2.contains(49));
		
		System.out.println(list2.toString());
	}

}
