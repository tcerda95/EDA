package tp2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import taller.OrderedList;

public class MergeTest {

	public static void main(String[] args) {
		Comparator<Integer> cmp = ((a, b) -> Integer.compare(a, b));
		OrderedList<Integer> list1 = new OrderedList<>(cmp);
		OrderedList<Integer> list2 = new OrderedList<>(cmp);
		OrderedList<Integer> list3 = new OrderedList<>(cmp);

		list1.add(123);
		list1.add(1);
		list2.add(21344);
		list2.add(-123);
		list3.add(21123);
		list3.add(222);
		list3.add(0);

		List<OrderedList<Integer>> array = new ArrayList<>();

		array.add(list1);
		array.add(list2);
		array.add(list3);

		System.out.println(OrderedList.otherMerge(array));
	}

}
