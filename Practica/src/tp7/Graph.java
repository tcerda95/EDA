package tp7;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph<V, E extends ArcGraph> extends GraphAdjList<V, E> {

	@Override
	public void addArc(V v, V w, E e) {
		super.addArc(v, w, e);
		super.addArc(w, v, e);
	}

	@Override
	public void removeArc(V v, V w) {
		super.removeArc(v, w);
		super.removeArc(w, v);
	}

	public int degree(V v) {
		Node node = nodes.get(v);
		if (node != null) {
			return node.adj.size();
		}
		return 0;
	}

	public boolean isConnected() {
		if (isEmpty()) {
			return true;
		}
		clearMarks();
		List<Node> l = getNodes();
		List<V> laux = new ArrayList<V>();
		DFS(l.get(0), laux);
		for (Node node : l) {
			if (!node.visited) {
				return false;
			}
		}
		return true;
	}

	public int connectedComponents() {
		clearMarks();
		return pathCount();
	}

	private int pathCount() {
		int count = 0;
		Node node;
		while ((node = unvisited()) != null) {
			count++;
			DFS(node, new ArrayList<V>());
		}
		return count;
	}

	private Node unvisited() {
		for(Node node : getNodes()) {
			if (! node.visited )
				return node;
		}
		return null;
	}

	public boolean cutVertex(V vertex) {
		Node node = nodes.get(vertex);
		if (node == null || node.adj.size() == 0)
			return false;
		clearMarks();
		int components = pathCount();
		clearMarks();
		node.visited = true;
		return components != pathCount();
	}

	public boolean isBridge(V v, V w) {
		E e = isArc(v,w);
		if ( e == null)
			return false;
		int components = connectedComponents();
		removeArc(v, w);
		int newComponents = connectedComponents();
		addArc(v, w, e);
		return components != newComponents;

	}

	// EJERCICIOS DE LA GUIA

	public void applyDFS (Function<V> f) {
		if (f == null)
			throw new IllegalArgumentException("Illegal function: null");
		if (!nodeList.isEmpty()) {
			clearMarks();
			applyDFS(nodeList.get(0), f);
		}
	}

	private void applyDFS(GraphAdjList<V, E>.Node node, Function<V> f) {
		if (!node.visited) {
			node.visited = true;
			f.apply(node.info);
			for (Arc arc : node.adj)
				applyDFS(arc.neighbor, f);
		}
	}

	public void applyDFSIter (Function<V> f) {
		if (f == null)
			throw new IllegalArgumentException("Illegal function: null");
		if (!nodeList.isEmpty()) {
			clearMarks();
			Deque<Node> stack = new LinkedList<>();
			Node current = nodeList.get(0);
			stack.push(current);
			while(!stack.isEmpty()) {
				current = stack.pop();
				if (!current.visited) {
					current.visited = true;
					f.apply(current.info);
					for (Arc arc : current.adj)
						if (!arc.neighbor.visited)
							stack.push(arc.neighbor);
				}
			}
		}
	}

	public void applyBFS (Function<V> f) {
		if (f == null)
			throw new IllegalArgumentException("Illegal function: null");
		if (!nodeList.isEmpty()) {
			clearMarks();
			Queue<Node> queue = new LinkedList<>();
			Node current = nodeList.get(0);
			current.visited = true;
			queue.add(current);
			while(!queue.isEmpty()) {
				current = queue.remove();
				f.apply(current.info);
				for (Arc arc : current.adj) {
					if (!arc.neighbor.visited) {
						arc.neighbor.visited = true;
						queue.add(arc.neighbor);
					}
				}
			}
		}
	}

	public boolean isTree() {
		clearMarks();
		return nodeList.isEmpty() || isTree(nodeList.get(0), null);
	}

	private boolean isTree(Node node, Node prev) {
		if (node.visited)
			return false;
		node.visited = true;
		for(Arc arc : node.adj)
			if (!arc.neighbor.equals(prev))
				if (!isTree(arc.neighbor, node))
					return false;
		return true;
	}

	public boolean isBipartite() {
		clearMarks();
		for (Node n : nodeList)
			if (n.tag == 0 && !isBipartite(n, 1))
				return false;
		return true;
	}

	private boolean isBipartite(Node n, int color) {
		n.tag = color;
		for (Arc arc : n.adj) {
			if (arc.neighbor.tag == color)
				return false;
			if (arc.neighbor.tag == 0 && !isBipartite(arc.neighbor, -color))
				return false;
		}
		return true;
	}

	public Graph<V, E> coverTree(V initial) {
		Graph<V, E> graph = new Graph<>();

		if (!nodeList.isEmpty()) {
			clearMarks();
			Node current = nodes.get(initial);
			Queue<Node> queue = new LinkedList<>();
			queue.add(current);

			graph.AddVertex(current.info);
			current.visited = true;

			while (!queue.isEmpty()) {
				current = queue.remove();
				for (Arc arc : current.adj) {
					arc.neighbor.visited = true;
					graph.AddVertex(arc.neighbor.info);
					graph.addArc(current.info, arc.neighbor.info, arc.info);
					queue.add(arc.neighbor);
				}
			}
		}

		return graph;
	}

	public int pathCount(V from, V to) {
		Node origin = nodes.get(from);
		Node target = nodes.get(to);
		if (origin == null || target == null)
			return 0;
		clearMarks();
		return pathCount(origin, target);
	}

	private int pathCount(Node origin, Node target) {
		if (origin == target)
			return 1;

		int count = 0;
		origin.visited = true;

		for (Arc arc : origin.adj)
			if (!arc.neighbor.visited)
				count += pathCount(arc.neighbor, target);

		origin.visited = false;

		return count;
	}
}
