package die.mass.Tree.TreeWeight;

import die.mass.Graphs.GraphWeight.GraphWeight;
import die.mass.Graphs.GraphWeight.GraphWeightNotEmpty;
import die.mass.Graphs.parts.edges.Arc;
import die.mass.Graphs.parts.edges.Edge;
import die.mass.Graphs.parts.vertices.ValueVertex;
import die.mass.Graphs.parts.vertices.Vertex;
import die.mass.Tree.Tree.Tree;
import die.mass.Tree.Tree.TreeAbstractNotEmpty;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TreeWeightNotEmpty extends TreeAbstractNotEmpty implements GraphWeight {

    private Set<Arc> edges;

    protected TreeWeightNotEmpty() {
        this.edges = new HashSet<>();
    }

    @Override
    protected <V extends Edge> Collection<V> getPrivateEdges() {
        return (Collection<V>) edges;
    }

    @Override
    public <V extends Vertex, E> boolean addEdge(Collection<V> vertices, E weight) {
        return checkEqualsingOfVertices(vertices) && addingEdge((Collection<ValueVertex>)vertices, weight);
    }

    protected <E> boolean addingEdge(Collection<ValueVertex> vertices, E weight) {
        Collection<ValueVertex> notContains = vertices.stream().filter(vertex -> !containsVertex(vertex)).collect(Collectors.toSet());
        getPrivateVertices().addAll(notContains);
        Arc edge = edgeGenerator.getArc(vertices, weight);
        getPrivateEdges().add(edge);
        if (isCycled()) {
            removeEdge(edge);
            getPrivateVertices().removeAll(notContains);
            return false;
        }
        return true;
    }

    @Override
    public Class<?> getEdgeWeightType() {
        return edgeWeightType;
    }

    public static TreeWeightNotEmpty.Builder builder() {
        return new TreeWeightNotEmpty().new Builder();
    }

    public class Builder extends GenericGraphBuilder<TreeWeightNotEmpty.Builder> implements GenericWeightBuilder<TreeWeightNotEmpty.Builder> {

        @Override
        public TreeWeightNotEmpty.Builder setEdgeWeightType(Class<?> edgeWeightType) {
            TreeWeightNotEmpty.this.edgeWeightType = edgeWeightType;
            return self();
        }

        @Override
        public TreeWeightNotEmpty build() {
            return TreeWeightNotEmpty.this;
        }
    }
}
