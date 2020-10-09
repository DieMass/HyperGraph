package die.mass.Tree.TreeDirected;

import die.mass.Graphs.Graph.GraphAbstractNotEmpty;
import die.mass.Graphs.parts.edges.Arrow;
import die.mass.Graphs.GraphDirected.GraphDirected;
import die.mass.Graphs.parts.edges.Edge;
import die.mass.Graphs.parts.vertices.ValueVertex;
import die.mass.Graphs.parts.vertices.Vertex;
import die.mass.Tree.Tree.Tree;
import die.mass.Tree.Tree.TreeAbstractNotEmpty;

import java.util.*;
import java.util.stream.Collectors;

public class TreeDirectedNotEmpty extends TreeAbstractNotEmpty implements GraphDirected {

    private ValueVertex root;
    private HashSet<Arrow> edges;

    public TreeDirectedNotEmpty() {
        this.edges = new HashSet<>();
    }

    protected Collection<Arrow> getPrivateEdges() {
        return edges;
    }

    @Override
    public ValueVertex getRoot() {
        return root;
    }

    @Override
    public <V extends Vertex> boolean addEdge(Collection<V> begin, Collection<V> end) {
        List<Vertex> vertices = new ArrayList<>();
        vertices.addAll(begin);
        vertices.addAll(end);
        return checkEqualsingOfVertices(vertices) && addingEdge((Collection<ValueVertex>)begin,(Collection<ValueVertex>) end);
    }

    protected boolean addingEdge(Collection<ValueVertex> begin, Collection<ValueVertex> end) {
        Set<ValueVertex> vertices = new HashSet<>();
        vertices.addAll(begin);
        vertices.addAll(end);
        Collection<ValueVertex> notContains = vertices.stream().filter(vertex -> !containsVertex(vertex)).collect(Collectors.toSet());
        getPrivateVertices().addAll(notContains);
        Arrow edge = edgeGenerator.getArrow(begin, end);
        getPrivateEdges().add(edge);
        if (isCycled()) {
            removeEdge(edge);
            getPrivateVertices().removeAll(notContains);
            return false;
        }
        return true;
    }

    public static TreeDirectedNotEmpty.Builder builder() {
        return new TreeDirectedNotEmpty().new Builder();
    }

    public class Builder extends GraphAbstractNotEmpty.GenericGraphBuilder<TreeDirectedNotEmpty.Builder> {

        @Override
        public TreeDirectedNotEmpty build() {
            return TreeDirectedNotEmpty.this;
        }
    }

}
