package die.mass.Graphs.GraphDirected;

import die.mass.Graphs.Graph.GraphAbstractEmpty;
import die.mass.Graphs.Graph.GraphAbstract;
import die.mass.Graphs.parts.edges.Arrow;
import die.mass.Graphs.parts.vertices.EmptyVertex;
import die.mass.Graphs.parts.vertices.Vertex;

import java.util.*;

public class GraphDirectedEmpty extends GraphAbstractEmpty implements GraphDirected {

    private HashSet<Arrow> edges;

    protected GraphDirectedEmpty() {
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
        getPrivateVertices().addAll((Collection<? extends EmptyVertex>) begin);
        getPrivateVertices().addAll((Collection<? extends EmptyVertex>) end);
        return getPrivateEdges().add(edgeGenerator.getArrow(begin, end));
    }

    public static GraphDirectedEmpty.Builder builder() {
        return new GraphDirectedEmpty().new Builder();
    }

    public class Builder extends GraphAbstract.GenericGraphBuilder<GraphDirectedEmpty.Builder> {

        @Override
        public GraphDirectedEmpty build() {
            return GraphDirectedEmpty.this;
        }

    }
}
