package die.mass.Graphs.GraphDirected;

import die.mass.Graphs.Graph.GraphAbstractNotEmpty;
import die.mass.Graphs.parts.edges.Arrow;
import die.mass.Graphs.parts.vertices.ValueVertex;
import die.mass.Graphs.parts.vertices.Vertex;

import java.util.*;

public class GraphDirectedNotEmpty extends GraphAbstractNotEmpty implements GraphDirected {

	private HashSet<Arrow> edges;

	public GraphDirectedNotEmpty() {
		this.edges = new HashSet<>();
	}

	@Override
	protected Collection<Arrow> getPrivateEdges() {
		return edges;
	}

	@Override
	public <V extends Vertex> boolean addEdge(Collection<V> begin, Collection<V> end) {
		List<Vertex> vertices = new ArrayList<>();
		vertices.addAll(begin);
		vertices.addAll(end);
		return checkEqualsingOfVertices(vertices) && addingEdge(begin, end);
	}


	protected <V extends Vertex> boolean addingEdge(Collection<V> begin, Collection<V> end) {
		getPrivateVertices().addAll((Collection<? extends ValueVertex>) begin);
		getPrivateVertices().addAll((Collection<? extends ValueVertex>) end);
		return getPrivateEdges().add(edgeGenerator.getArrow(begin, end));
	}

	public static GraphDirectedNotEmpty.Builder builder() {
		return new GraphDirectedNotEmpty().new Builder();
	}

	public class Builder extends GenericGraphBuilder<Builder> {

		@Override
		public GraphDirectedNotEmpty build() {
			return GraphDirectedNotEmpty.this;
		}
	}
}
