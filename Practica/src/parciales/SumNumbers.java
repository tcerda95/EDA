package parciales;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SumNumbers {
	public static Set<List<Integer>> possibleSums(List<Integer> numbers, Integer target) {
		Set<List<Integer>> ans = new HashSet<>();
		if (numbers.isEmpty() || target <= 0)
			return ans;
		Collections.sort(numbers);
		LinkedList<Integer> current = new LinkedList<>();
		possibleSums(ans, numbers, current, target, 0, 0);
		return ans;
	}

	private static void possibleSums(Set<List<Integer>> ans, List<Integer> numbers, LinkedList<Integer> current,
			Integer target, int sum, int index) {
		for (int i = index; i < numbers.size(); i++) {
			int num = numbers.get(i);
			int newSum = sum + num;

			if (newSum > target)
				return;

			current.addLast(num);
			if (newSum == target) {
				ans.add(new LinkedList<Integer>(current));
				current.removeLast();
				return;
			}

			possibleSums(ans,numbers,current,target, newSum, i);
			current.removeLast();
		}
	}

	public static void main(String[] args) {
		List<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(7);
		list.add(14);

		Set<List<Integer>> ans = possibleSums(list, 5);

		System.out.println(ans);
	}
}
