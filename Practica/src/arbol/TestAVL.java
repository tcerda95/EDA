package arbol;

public class TestAVL {

	public static void main(String[] args) {
		AVLTree<Integer> tree = new AVLTree<>((o1, o2) -> o1.compareTo(o2));

		tree.insert(50);
		tree.insert(55);
		tree.insert(60);
		tree.insert(75);
		tree.insert(63);
		tree.insert(87);
		tree.insert(77);
		tree.insert(76);
		tree.insert(79);
		tree.insert(93);
		tree.insert(100);
		tree.insert(150);
		tree.insert(140);

		System.out.println("AVL: " + tree.isAVL());
		System.out.println(tree.toString());
		System.out.println("Height: " + tree.getHeight());
	}

}
