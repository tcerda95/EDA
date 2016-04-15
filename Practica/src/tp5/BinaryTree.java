package tp5;

import tp2.Function;

public class BinaryTree<T> {

	public static boolean areReflected(BinaryTree<?> t1, BinaryTree<?> t2) {
		if (t1 == null && t2 == null)
			return true;
		if ((t1 != null && t2 == null) || (t1 == null && t2 != null))
			return false;
		return areReflected(t1.right, t2.left) && areReflected(t1.left, t2.right);
	}

	private T value;
	private BinaryTree<T> left;
	private BinaryTree<T> right;

	public BinaryTree(T v) {
		value = v;
	}

	public <S> BinaryTree<S> apply(Function<T,S> f) {
		return apply(f, this);
	}

	private <S> BinaryTree<S> apply(Function<T, S> f, BinaryTree<T> tree) {
		if (tree == null)
			return null;
		BinaryTree<S> newTree = new BinaryTree<S>(f.eval(tree.value));
		newTree.left = apply(f, tree.left);
		newTree.right = apply(f, tree.right);
		return newTree;
	}
}
