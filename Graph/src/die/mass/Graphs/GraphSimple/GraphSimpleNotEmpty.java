package die.mass.Graphs.GraphSimple;

import die.mass.Graphs.Graph.GraphAbstractNotEmpty;
import die.mass.Graphs.parts.edges.Edge;
import die.mass.Graphs.parts.edges.SimpleEdge;
import die.mass.Graphs.parts.vertices.ValueVertex;
import die.mass.Graphs.parts.vertices.Vertex;

import java.util.*;

public class GraphSimpleNotEmpty extends GraphAbstractNotEmpty implements GraphSimple {

    private HashSet<SimpleEdge> edges;

    protected GraphSimpleNotEmpty() {
        this.edges = new HashSet<>();
    }

    @Override
    protected <T extends Edge> Collection<T> getPrivateEdges() {
        return (Collection<T>) edges;
    }

    @Override
    public <V extends Vertex> boolean addEdge(Collection<V> vertices) {
        return checkEqualsingOfVertices(vertices) && addingEdge(vertices);
    }

    protected <V extends Vertex> boolean addingEdge(Collection<V> vertices) {
        getPrivateVertices().addAll((Collection<? extends ValueVertex>) vertices);
        return getPrivateEdges().add(edgeGenerator.getSimpleEdge(vertices));
    }

    public static Builder builder() {
        return new GraphSimpleNotEmpty().new Builder();
    }

    public class Builder extends GraphAbstractNotEmpty.GenericGraphBuilder<Builder> {

        @Override
        public GraphSimpleNotEmpty build() {
            return GraphSimpleNotEmpty.this;
        }
    }
}
