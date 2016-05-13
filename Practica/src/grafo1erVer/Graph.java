package grafo1erVer;


public class Graph<V, E> extends GraphAdjList<V, E> {

	@Override
	protected boolean isDirected() {
		return false;
	}


}
