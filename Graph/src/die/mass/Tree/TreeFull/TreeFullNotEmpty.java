package die.mass.Tree.TreeFull;

import die.mass.Graphs.GraphFull.GraphFull;
import die.mass.Graphs.parts.edges.ArcDirected;
import die.mass.Graphs.parts.edges.Edge;
import die.mass.Graphs.parts.vertices.ValueVertex;
import die.mass.Graphs.parts.vertices.Vertex;
import die.mass.Tree.Tree.TreeAbstractNotEmpty;

import java.util.*;
import java.util.stream.Collectors;


public class TreeFullNotEmpty extends TreeAbstractNotEmpty implements GraphFull {

    private HashSet<ArcDirected> edges;

    protected TreeFullNotEmpty() {
        this.edges = new HashSet<>();
    }

    @Override
    protected <V extends Edge> Collection<V> getPrivateEdges() { return (Collection<V>) edges; }

    @Override
    public <V extends Vertex, E> boolean addEdge(Collection<V> begin, Collection<V> end, E edgeWeight) {
        List<Vertex> vertices = new ArrayList<>();
        vertices.addAll(begin);
        vertices.addAll(end);
        return checkEqualsingOfVertices(vertices) && addingEdge((Collection<ValueVertex>) begin, (Collection<ValueVertex>) end, edgeWeight);
    }

    protected <E> boolean addingEdge(Collection<ValueVertex> begin, Collection<ValueVertex> end, E edge) {
        Set<ValueVertex> vertices = new HashSet<>();
        vertices.addAll(begin);
        vertices.addAll(end);
        Collection<ValueVertex> notContains = vertices.stream().filter(vertex -> !containsVertex(vertex)).collect(Collectors.toSet());
        getPrivateVertices().addAll(notContains);
        getPrivateEdges().add(edgeGenerator.getArcDirected(begin, end, edge));
        if (isCycled()) {
            removeEdge(getEdgesBetweenVertices().stream().max(Comparator.comparing(Edge::getId)).get());
            getPrivateVertices().removeAll(notContains);
            return false;
        }
        return true;
    }

    @Override
    public Class<?> getEdgeWeightType() {
        return edgeWeightType;
    }

    public static TreeFullNotEmpty.Builder builder() { return new TreeFullNotEmpty().new Builder(); }

    public class Builder extends GenericGraphBuilder<TreeFullNotEmpty.Builder> implements GenericWeightBuilder<TreeFullNotEmpty.Builder> {

        @Override
        public TreeFullNotEmpty.Builder setEdgeWeightType(Class<?> edgeWeightType) {
            TreeFullNotEmpty.this.edgeWeightType = edgeWeightType;
            return self();
        }

        @Override
        public TreeFullNotEmpty build() {
            return TreeFullNotEmpty.this;
        }
    }
}
