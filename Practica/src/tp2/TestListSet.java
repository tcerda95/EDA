package tp2;

import java.util.Set;

public class TestListSet {

	public static void main(String[] args) {

		ListSet<Integer> set = new ListSet<>();
		Set<Integer> set2 = new ListSet<>();

		set.add(6);
		set.add(7);
		set.add(6);
		set.add(234);

		set2.add(1233);
		set2.add(12312312);
		set.add(6);


		System.out.println(set);
		set.reverse();
		System.out.println(set);

		System.out.println(set.size());
		System.out.println(set.removeAll(set2));
		System.out.println(set.contains(6));
		System.out.println(set.size());


	}

}
