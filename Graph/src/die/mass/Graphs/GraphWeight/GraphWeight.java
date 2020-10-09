package die.mass.Graphs.GraphWeight;

import die.mass.Graphs.parts.vertices.Vertex;

import java.util.Arrays;
import java.util.Collection;

public interface GraphWeight extends GraphWeightGeneral {

    default <E> boolean addEdge(Vertex[] vertices, E edge) {
        return addEdge(Arrays.asList(vertices), edge);
    }

    default <E> boolean addEdge(E edge, Vertex[] vertices) {
        return addEdge(Arrays.asList(vertices), edge);
    }

    <V extends Vertex, E> boolean addEdge(Collection<V> vertices, E weight);

}
