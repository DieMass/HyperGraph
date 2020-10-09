package die.mass.Graphs.GraphFull;

import die.mass.Graphs.GraphDirected.GraphDirectedGeneral;
import die.mass.Graphs.GraphWeight.GraphWeightGeneral;
import die.mass.Graphs.parts.edges.DirectedEdge;
import die.mass.Graphs.parts.vertices.Vertex;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public interface GraphFull extends GraphWeightGeneral, GraphDirectedGeneral {

    default <E> boolean addEdge(Vertex begin, Vertex end, E edgeWeight) {
        return addEdge(Collections.singleton(begin), Collections.singleton(end), edgeWeight);
    }

    default <E> boolean addEdge(Vertex[] begin, Vertex[] end, E edgeWeight) {
        return addEdge(Arrays.asList(begin), Arrays.asList(end), edgeWeight);
    }

    <V extends Vertex, E> boolean addEdge(Collection<V> begin, Collection<V> end, E edgeWeight);

}
