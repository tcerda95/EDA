package arbol;

public class Test2 {

	public static void main(String[] args) {
		Tree2<Integer> tree = new Tree2<>((o1, o2) -> o1.compareTo(o2));

		tree.add(50);
		tree.add(30);
		tree.add(36);
		tree.add(75);
		tree.add(63);
		tree.add(87);
		tree.add(77);
		tree.add(76);
		tree.add(79);
		tree.add(93);

		System.out.println(tree.toString());

		System.out.println();
		System.out.println(tree.contains(50));
		tree.remove(50);
		System.out.println(tree.toString());

	}

}
