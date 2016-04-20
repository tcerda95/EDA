package tp5;

import java.util.Comparator;

import tp2.Function;
import tp3.Stack;

public class BinaryTree<T> {

	public static boolean sameLeavesOrder(BinaryTree<?> t1, BinaryTree<?> t2) {
		if (t1 == null && t2 == null)
			return true;
		if (t1 == null || t2 == null)
			return false;

		Stack<BinaryTree<?>> stack1 = new Stack<>();
		Stack<BinaryTree<?>> stack2 = new Stack<>();

		stack1.push(t1);
		stack2.push(t2);

		while (!stack1.isEmpty() && !stack2.isEmpty()) {
			BinaryTree<?> n1;
			BinaryTree<?> n2;

			while ((n1 = stack1.pop()).hasChildren()) {
				if (n1.hasRightChild())
					stack1.push(n1.right);
				if (n1.hasLeftChild())
					stack1.push(n1.left);
			}

			while ((n2 = stack2.pop()).hasChildren()) {
				if (n2.hasRightChild())
					stack2.push(n2.right);
				if (n2.hasLeftChild())
					stack2.push(n2.left);
			}

			if (!n1.value.equals(n2.value))
				return false;
		}

		return stack1.isEmpty() && stack2.isEmpty();
	}

	public static boolean areReflected(BinaryTree<?> t1, BinaryTree<?> t2) {
		if (t1 == null && t2 == null)
			return true;
		if ((t1 != null && t2 == null) || (t1 == null && t2 != null))
			return false;
		return areReflected(t1.right, t2.left) && areReflected(t1.left, t2.right);
	}

	public static <T> BinaryTree<T> buildBalanced (T[] array) {
		return buildBalanced(array, 0, array.length-1);
	}

	private static <T> BinaryTree<T> buildBalanced(T[] array, int low, int high) {
		if (low > high)
			return null;
		int mid = (low + high) / 2;
		BinaryTree<T> n = new BinaryTree<>(array[mid]);
		n.left = buildBalanced(array, low, mid-1);
		n.right = buildBalanced(array, mid+1, high);
		return n;
	}

	T value;
	BinaryTree<T> left;
	BinaryTree<T> right;

	public BinaryTree(T v) {
		value = v;
	}

	public <S> BinaryTree<S> apply(Function<T,S> f) {
		return apply(f, this);
	}

	public boolean isReflected(BinaryTree<?> t) {
		return areReflected(this, t);
	}

	public boolean isBST(Comparator<T> cmp) {
		return isBST(this, null, null, cmp);
	}

	private boolean isBST(BinaryTree<T> n, T maxValue, T minValue, Comparator<T> cmp) {
		if (n == null)
			return true;
		if (maxValue != null && cmp.compare(n.value, maxValue) > 0)
			return false;
		if (minValue != null && cmp.compare(n.value, minValue) < 0)
			return false;
		return isBST(n.left, n.value, minValue, cmp) && isBST(n.right, maxValue, n.value, cmp);
	}

	private static class Container {
		private boolean isValid; // indica si el árbol es AVL
		private int height;		 // contiene la altura del árbol
	}

	public boolean isAVL(Comparator<T> cmp) {
		return isAVL(this, null, null, cmp).isValid;
	}

	private Container isAVL(BinaryTree<T> n, T maxValue, T minValue, Comparator<T> cmp) {
		Container c = new Container();
		if (n == null) {
			c.isValid = true;
			c.height = -1;
			return c;
		}
		if (maxValue != null && cmp.compare(n.value, maxValue) > 0) {
			c.isValid = false;
			return c;
		}
		if (minValue != null && cmp.compare(n.value, minValue) < 0) {
			c.isValid = false;
			return c;
		}
		Container leftc = isAVL(n.left, n.value, minValue, cmp);
		Container rightc = isAVL(n.right, maxValue, n.value, cmp);

		if (!leftc.isValid || !rightc.isValid) {
			c.isValid = false;
			return c;
		}

		c.isValid = Math.abs(leftc.height - rightc.height) <= 1;
		c.height = 1 + Integer.max(leftc.height, rightc.height);
		return c;
	}

	private <S> BinaryTree<S> apply(Function<T, S> f, BinaryTree<T> tree) {
		if (tree == null)
			return null;
		BinaryTree<S> newTree = new BinaryTree<S>(f.eval(tree.value));
		newTree.left = apply(f, tree.left);
		newTree.right = apply(f, tree.right);
		return newTree;
	}

	public boolean hasChildren() {
		return hasLeftChild() || hasRightChild();
	}

	public boolean hasLeftChild() {
		return left != null;
	}

	public boolean hasRightChild() {
		return right != null;
	}

	public boolean hasBothChildren() {
		return hasLeftChild() && hasRightChild();
	}

}
