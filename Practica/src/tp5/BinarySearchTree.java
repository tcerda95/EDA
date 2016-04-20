package tp5;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

import tp2.ListSet;
import tp3.Queue;
import tp3.Stack;

public class BinarySearchTree<T> {
	private BinaryTree<T> root;
	private Comparator<T> cmp;
	private int size;

	public BinarySearchTree(Comparator<T> c) {
		cmp = c;
	}

	public boolean isAVL() {
		return root.isAVL(cmp);
	}

	public void insert(T value) {
		root = insert(value, root);
	}

	private BinaryTree<T> insert (T value, BinaryTree<T> t) {
		if (t == null) {
			size += 1;
			return new BinaryTree<T>(value);
		}
		int comp = cmp.compare(value, t.value);
		if (comp < 0)
			t.left = insert(value, t.left);
		else if (comp > 0)
			t.right = insert(value, t.right);
		return t;
	}

	public void remove(T key) {
		root = remove(key, root);
	}

	private BinaryTree<T> remove(T key, BinaryTree<T> t) {
		if (t == null)
			return t;

		int comp = cmp.compare(key, t.value);

		if (comp < 0) {
			t.left = remove(key, t.left);
			return t;
		}
		else if (comp > 0) {
			t.right = remove(key, t.right);
			return t;
		}

		size -= 1;

		if (!t.hasChildren())
			return null;

		if (!t.hasBothChildren())
			return t.hasRightChild() ? t.right : t.left;

		BinaryTree<T> prev = null;
		BinaryTree<T> current = t.right;

		while (current.hasLeftChild()) {
			prev = current;
			current = current.left;
		}

		if (prev != null) {
			prev.left = current.right;
			current.right = t.right;
		}

		current.left = t.left;

		return current;
	}

	public boolean contains(T value) {
		return contains(value, root);
	}

	public boolean contains(T value, BinaryTree<T> t) {
		if (t == null)
			return false;
		int comp = cmp.compare(value, t.value);
		if (comp > 0)
			return contains(value, t.right);
		else if (comp < 0)
			return contains(value, t.left);
		return true;
	}

	public int level(T value) {
		return level(value, root);
	}

	private int level(T value, BinaryTree<T> t) {
		if (t == null)
			return -1;

		int comp = cmp.compare(value, t.value);
		int subLevel;

		if (comp < 0)
			subLevel = level(value, t.left);
		else if (comp > 0)
			subLevel = level(value, t.right);
		else
			return 0;

		return subLevel != -1 ? subLevel + 1 : -1;
	}

	public int leaves() {
		return leaves(root);
	}

	private int leaves(BinaryTree<T> t) {
		if (t == null)
			return 0;
		if (!t.hasChildren())
			return 1;
		return leaves(t.left) + leaves(t.right);
	}

	public Set<T> getInRange(T minValue, T maxValue) {
		Set<T> set = new ListSet<T>();
		getInRange(minValue, maxValue, root, set);
		return set;
	}

	private void getInRange(T minValue, T maxValue, BinaryTree<T> t, Set<T> set) {
		if (t != null) {
			int cmpMin = cmp.compare(t.value, minValue);
			int cmpMax = cmp.compare(t.value, maxValue);
			if (cmpMin >= 0 && cmpMax <= 0)
				set.add(t.value);
			if (cmpMin > 0)
				getInRange(minValue, maxValue, t.left, set);
			if (cmpMax < 0)
				getInRange(minValue, maxValue, t.right, set);
		}
	}

	public T max() {
		if (root != null)
			return max(root);
		return null;
	}

	private T max(BinaryTree<T> t) {
		if (!t.hasRightChild())
			return t.value;
		return max(t.right);
	}

	public void printParents(T value) {
		printParents(value, root);
	}

	private boolean printParents(T value, BinaryTree<T> t) {
		if (t == null)
			return false;
		int comp = cmp.compare(value, t.value);
		if (comp == 0)
			return true;
		boolean print = comp > 0 ? printParents(value, t.right) : printParents(value, t.left);
		if (print)
			System.out.println(t.value);
		return print;
	}

	public void printDescendants(T value) {
		printDescendants(value, root);
	}

	private void printDescendants(T value, BinaryTree<T> t) {
		if (t != null) {
			int comp = cmp.compare(value, t.value);
			if (comp > 0)
				printDescendants(value, t.right);
			else if (comp < 0)
				printDescendants(value, t.left);
			else
				printDescendants(t);
		}
	}

	private void printDescendants(BinaryTree<T> t) {
		Queue<BinaryTree<T>> q = new Queue<>();
		q.enqueue(t);
		while(!q.isEmpty()) {
			BinaryTree<T> dequeued = q.dequeue();
			if (dequeued != t) // imprime solo descendientes
				System.out.println(dequeued.value);
			if (dequeued.hasLeftChild())
				q.enqueue(dequeued.left);
			if (dequeued.hasRightChild())
				q.enqueue(dequeued.right);
		}
	}

	public Iterator<T> postorderIterator() {
		return new PostorderIterator<T>(root);
	}

	private static class PostorderIterator<T> implements Iterator<T> {
		private static class MarkableTree<T> {
			private boolean marked;
			BinaryTree<T> tree;

			public MarkableTree(BinaryTree<T> t) {
				tree = t;
			}
		}

		// arriba de la pila debe quedar el nodo a devolver
		Stack<MarkableTree<T>> stack = new Stack<>();

		private void traverseTree() {
			MarkableTree<T> t;
			while (!(t = stack.peek()).marked) {
				t.marked = true;
				if (t.tree.hasRightChild())
					stack.push(new MarkableTree<T>(t.tree.right));
				if (t.tree.hasLeftChild())
					stack.push(new MarkableTree<T>(t.tree.left));
			}
		}

		public PostorderIterator(BinaryTree<T> root) {
			stack.push(new MarkableTree<T>(root));
			traverseTree();
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public T next() {
			T value = stack.pop().tree.value;
			if (hasNext())
				traverseTree();
			return value;
		}
	}

	public Iterator<T> inorderIterator() {
		return new InorderIterator<T>(root);
	}

	private static class InorderIterator<T> implements Iterator<T> {
		// arriba del stack está el nodo cuyo valor devolver
		private Stack<BinaryTree<T>> stack = new Stack<>();

		public InorderIterator(BinaryTree<T> root) {
			stack.push(root);
			BinaryTree<T> t;
			while ((t = stack.peek()).hasLeftChild())
				stack.push(t.left);
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public T next() {
			BinaryTree<T> t = stack.pop();
			T value = t.value;  // "me proceso" en este caso devolver value

			// "proceso subarbol derecho" en este caso dejar la pila lista con
			// los valores del subarbol derecho
			if (t.hasRightChild()) {
				stack.push(t.right); // "me guardo"; primero proceso subarbol izquierdo

				// "proceso subarbol izquierdo"; en este caso dejar la pila lista con
				// los valores del subarbol izquierdo
				while ((t = stack.peek()).hasLeftChild())
					stack.push(t.left);
			}

			return value;
		}
	}

	public Iterator<T> preorderIterator() {
		return new PreorderIterator<T>(root);
	}

	private static class PreorderIterator<T> implements Iterator<T> {
		private Stack<BinaryTree<T>> stack;

		public PreorderIterator(BinaryTree<T> root) {
			stack = new Stack<>();
			stack.push(root);
		}

		@Override
		public boolean hasNext() {
			return stack.isEmpty();
		}

		@Override
		public T next() {
			BinaryTree<T> t = stack.pop();
			if (t.hasRightChild())
				stack.push(t.right);
			if (t.hasLeftChild())
				stack.push(t.left);
			return t.value;
		}
	}

	public boolean sameLeavesOrder(BinarySearchTree<?> t) {
		return BinaryTree.sameLeavesOrder(this.root, t.root);
	}

	@Override  // se tomó como criterio de igualdad que ambos árboles tengan los mismos elementos
	public boolean equals(Object other) {
		if (other == this)
			return true;
		if (other == null || !(other instanceof BinarySearchTree))
			return false;
		BinarySearchTree<?> t = (BinarySearchTree<?>) other;
		return t.size() == size() && equals(this, t);
	}

	// recorre ambos inorder y va comparando
	private boolean equals(BinarySearchTree<?> t1, BinarySearchTree<?> t2) {
		Iterator<?> iter1 = t1.inorderIterator();
		Iterator<?> iter2 = t2.inorderIterator();
		while (iter1.hasNext() && iter2.hasNext())
			if (!iter1.next().equals(iter2.next()))
				return false;
		return !iter1.hasNext() && !iter2.hasNext();
	}

	@Override
	public int hashCode() {
		return size();
	}

	public int size() {
		return size;
	}
}
