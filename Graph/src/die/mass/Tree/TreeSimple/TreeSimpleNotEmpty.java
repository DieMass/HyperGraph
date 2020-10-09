package die.mass.Tree.TreeSimple;

import die.mass.Graphs.Graph.GraphAbstractNotEmpty;
import die.mass.Graphs.GraphSimple.GraphSimple;
import die.mass.Graphs.parts.edges.SimpleEdge;
import die.mass.Graphs.parts.vertices.ValueVertex;
import die.mass.Graphs.parts.vertices.Vertex;
import die.mass.Tree.Tree.TreeAbstractNotEmpty;
import die.mass.Tree.Tree.Tree;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

public class TreeSimpleNotEmpty extends TreeAbstractNotEmpty implements GraphSimple {

    private Collection<SimpleEdge> edges;

    public TreeSimpleNotEmpty() {
        this.edges = new HashSet<>();
    }

    @Override
    protected Collection<SimpleEdge> getPrivateEdges() {
        return edges;
    }

    @Override
    public <V extends Vertex> boolean addEdge(Collection<V> vertices) {
        return checkEqualsingOfVertices(vertices) && addingEdge((Collection<ValueVertex>) vertices);
    }

    //TODO: duplicate in sibling trees
    protected boolean addingEdge(Collection<ValueVertex> vertices) {
        Collection<ValueVertex> notContains = vertices.stream().filter(vertex -> !containsVertex(vertex)).collect(Collectors.toSet());
        getPrivateVertices().addAll(notContains);
        SimpleEdge edge = edgeGenerator.getSimpleEdge(vertices);
        getPrivateEdges().add(edge);
        if (isCycled()) {
            System.out.println(edge);
            removeEdge(edge);
            getPrivateVertices().removeAll(notContains);
            return false;
        }
        return true;
    }

    public static TreeSimpleNotEmpty.Builder builder() {
        return new TreeSimpleNotEmpty().new Builder();
    }

    public class Builder extends GraphAbstractNotEmpty.GenericGraphBuilder<TreeSimpleNotEmpty.Builder> {

        @Override
        public TreeSimpleNotEmpty build() {
            return TreeSimpleNotEmpty.this;
        }
    }
}
