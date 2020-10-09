package die.mass.Graphs.GraphSimple;

import die.mass.Graphs.Graph.GraphAbstractEmpty;
import die.mass.Graphs.Graph.GraphAbstract;
import die.mass.Graphs.parts.edges.Edge;
import die.mass.Graphs.parts.edges.SimpleEdge;
import die.mass.Graphs.parts.vertices.EmptyVertex;
import die.mass.Graphs.parts.vertices.Vertex;

import java.util.Collection;
import java.util.HashSet;

public class GraphSimpleEmpty extends GraphAbstractEmpty implements GraphSimple {

    private HashSet<SimpleEdge> edges;

    protected GraphSimpleEmpty() {
        this.edges = new HashSet<>();
    }

    @Override
    protected <E extends Edge> Collection<E> getPrivateEdges() {
        return (Collection<E>) edges;
    }

    @Override
    public <V extends Vertex> boolean addEdge(Collection<V> vertices) { return checkEqualsingOfVertices(vertices) && addingEdge(vertices); }

    protected <V extends Vertex> boolean addingEdge(Collection<V> vertices) {
        getPrivateVertices().addAll((Collection<? extends EmptyVertex>) vertices);
        return getPrivateEdges().add(edgeGenerator.getSimpleEdge(vertices));
    }

    public static Builder builder() {
        return new GraphSimpleEmpty().new Builder();
    }

    public class Builder extends GraphAbstract.GenericGraphBuilder<Builder> {

        @Override
        public GraphSimpleEmpty build() {
            return GraphSimpleEmpty.this;
        }
    }
}
