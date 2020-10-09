package die.mass.Graphs.GraphWeight;

import die.mass.Graphs.Graph.Graph;
import die.mass.Graphs.parts.edges.WeightEdge;
import die.mass.Graphs.parts.vertices.ValueVertex;

import java.util.stream.Collectors;

public interface GraphWeightGeneral extends Graph {

    Class<?> getEdgeWeightType();

    default <V> boolean containsEdgeValue(V value) {
        return value.getClass().equals(getEdgeWeightType())
                && getEdges().stream()
                .map(edge -> ((WeightEdge) edge).getWeight())
                .collect(Collectors.toList()).contains(value);
    }

    default <T> WeightEdge getEdge(T value) {
        return (WeightEdge) this.getEdges().stream()
                .filter(edge -> ((WeightEdge) edge).getWeight().equals(value))
                .findFirst().get();
    }

    interface GenericWeightBuilder<B extends GenericWeightBuilder<B>> {

        B setEdgeWeightType(Class<?> edgeWeightType);
    }
}
