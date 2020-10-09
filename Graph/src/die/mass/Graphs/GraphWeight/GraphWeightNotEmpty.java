package die.mass.Graphs.GraphWeight;

import die.mass.Graphs.Graph.GraphAbstractNotEmpty;
import die.mass.Graphs.parts.edges.Arc;
import die.mass.Graphs.parts.edges.Edge;
import die.mass.Graphs.parts.vertices.ValueVertex;
import die.mass.Graphs.parts.vertices.Vertex;

import java.util.Collection;
import java.util.HashSet;

public class GraphWeightNotEmpty extends GraphAbstractNotEmpty implements GraphWeight {

    private HashSet<Arc> edges;

    protected GraphWeightNotEmpty() {
        this.edges = new HashSet<>();
    }

    @Override
    protected <V extends Edge> Collection<V> getPrivateEdges() {
        return (Collection<V>) edges;
    }

    @Override
    public <Q extends Vertex, E> boolean addEdge(Collection<Q> vertices, E edge) {
        return checkEqualsingOfVertices(vertices) && addingEdge(vertices, edge);
    }

    protected <V extends Vertex, E> boolean addingEdge(Collection<V> vertices, E edge) {
        getPrivateVertices().addAll((Collection<? extends ValueVertex>) vertices);
        return getPrivateEdges().add(edgeGenerator.getArc(vertices, edge));
    }

    @Override
    public Class<?> getEdgeWeightType() {
        return edgeWeightType;
    }

    public static Builder builder() {
        return new GraphWeightNotEmpty().new Builder();
    }

    public class Builder extends GenericGraphBuilder<Builder> implements GenericWeightBuilder<Builder> {

        @Override
        public Builder setEdgeWeightType(Class<?> edgeWeightType) {
                GraphWeightNotEmpty.this.edgeWeightType = edgeWeightType;
                return self();
        }

        @Override
        public GraphWeightNotEmpty build() {
            return GraphWeightNotEmpty.this;
        }
    }
}
