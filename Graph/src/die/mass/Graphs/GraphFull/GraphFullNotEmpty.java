package die.mass.Graphs.GraphFull;

import die.mass.Graphs.Graph.GraphAbstractNotEmpty;
import die.mass.Graphs.parts.edges.ArcDirected;
import die.mass.Graphs.parts.edges.Edge;
import die.mass.Graphs.parts.vertices.ValueVertex;
import die.mass.Graphs.parts.vertices.Vertex;

import java.util.*;

public class GraphFullNotEmpty extends GraphAbstractNotEmpty implements GraphFull {

    private HashSet<ArcDirected> edges;

    protected GraphFullNotEmpty() {
        this.edges = new HashSet<>();
    }

    @Override
    protected <V extends Edge> Collection<V> getPrivateEdges() {
        return (Collection<V>) edges;
    }

    @Override
    public <V extends Vertex, E> boolean addEdge(Collection<V> begin, Collection<V> end, E edgeWeight) {
        List<Vertex> vertices = new ArrayList<>();
        vertices.addAll(begin);
        vertices.addAll(end);
        return checkEqualsingOfVertices(vertices) && addingEdge((Collection<ValueVertex>) begin, (Collection<ValueVertex>) end, edgeWeight);
    }

    protected <E> boolean addingEdge(Collection<ValueVertex> begin, Collection<ValueVertex> end, E edge) {
        getPrivateVertices().addAll(begin);
        getPrivateVertices().addAll(end);
        return getPrivateEdges().add(edgeGenerator.getArcDirected(begin, end, edge));
    }

    @Override
    public Class<?> getEdgeWeightType() {
        return edgeWeightType;
    }

    public static Builder builder() { return new GraphFullNotEmpty().new Builder(); }

    public class Builder extends GenericGraphBuilder<Builder> implements GenericWeightBuilder<GraphFullNotEmpty.Builder> {

        @Override
        public Builder setEdgeWeightType(Class<?> edgeWeightType) {
            GraphFullNotEmpty.this.edgeWeightType = edgeWeightType;
            return self();
        }

        @Override
        public GraphFullNotEmpty build() {
            return GraphFullNotEmpty.this;
        }
    }
}
