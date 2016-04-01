package arbol;

public class Test {

	public static void main(String[] args) {
		Tree tree = new Tree();

		tree.insert(50);
		tree.insert(30);
		tree.insert(36);
		tree.insert(75);
		tree.insert(63);
		tree.insert(87);
		tree.insert(77);
		tree.insert(76);
		tree.insert(79);
		tree.insert(93);

		tree.display();

		System.out.println("Height: " + tree.height());

		System.out.println();
		System.out.println(tree.contains(64));
		tree.delete(50);
		tree.display();

		System.out.println("Height: " + tree.height());
	}

}
