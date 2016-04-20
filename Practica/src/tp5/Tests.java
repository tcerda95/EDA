package tp5;

import java.util.Comparator;
import java.util.Iterator;

public class Tests {

	public static void main(String[] args) {
		Comparator<Integer> cmp = ((a,b)->Integer.compare(a, b));
		BinarySearchTree<Integer> tree1 = new BinarySearchTree<>(cmp);
		BinarySearchTree<Integer> tree2 = new BinarySearchTree<>(cmp);

		tree1.insert(10);
		tree1.insert(14);
		tree1.insert(20);
		tree1.insert(9);
		tree1.insert(12);
		tree1.insert(8);
		tree1.insert(324);

		tree2.insert(10);
		tree2.insert(14);
		tree2.insert(20);
		tree2.insert(9);
		tree2.insert(12);
		tree2.insert(8);
		tree2.insert(-1);

		System.out.println("Same leaves order: " + tree1.sameLeavesOrder(tree2));
		System.out.println(tree1.isAVL());

		Iterator<Integer> iter = tree1.postorderIterator();

		while (iter.hasNext()) {
			System.out.println(iter.next());
		}

		System.out.println("[10,14]");
		System.out.println((tree1.getInRange(10, 14)));
	}
}
