package die.mass.Graphs.GraphWeight;

import die.mass.Graphs.Graph.GraphAbstractEmpty;
import die.mass.Graphs.parts.edges.Arc;
import die.mass.Graphs.parts.edges.Edge;
import die.mass.Graphs.parts.vertices.EmptyVertex;
import die.mass.Graphs.parts.vertices.Vertex;

import java.util.Collection;
import java.util.HashSet;

public class GraphWeightEmpty extends GraphAbstractEmpty implements GraphWeight {

    private HashSet<Arc> edges;

    protected GraphWeightEmpty() {
        this.edges = new HashSet<>();
    }

    @Override
    protected <V extends Edge> Collection<V> getPrivateEdges() { return (Collection<V>) edges; }

    @Override
    public <V extends Vertex, E> boolean addEdge(Collection<V> vertices, E edge) {
        return checkEqualsingOfVertices(vertices) && addingEdge(vertices, edge);
    }

    protected <V extends Vertex, E> boolean addingEdge(Collection<V> vertices, E edge) {
        getPrivateVertices().addAll((Collection<? extends EmptyVertex>) vertices);
        return getPrivateEdges().add(edgeGenerator.getArc(vertices, edge));
    }

    @Override
    public Class<?> getEdgeWeightType() {
        return edgeWeightType;
    }

    public static Builder builder() {
        return new GraphWeightEmpty().new Builder();
    }

    public class Builder extends GenericGraphBuilder<Builder> implements GenericWeightBuilder<Builder> {

        @Override
        public Builder setEdgeWeightType(Class<?> edgeWeightType) {
            GraphWeightEmpty.this.edgeWeightType = edgeWeightType;
            return self();
        }

        @Override
        public GraphWeightEmpty build() {
            return GraphWeightEmpty.this;
        }
    }
}
