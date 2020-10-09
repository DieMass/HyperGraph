package die.mass.Graphs.GraphDirected;

import die.mass.Graphs.Graph.Graph;
import die.mass.Graphs.parts.edges.DirectedEdge;
import die.mass.Graphs.parts.vertices.Vertex;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public interface GraphDirected extends Graph, GraphDirectedGeneral {

    default boolean addEdge(Vertex begin, Vertex end) {
        return addEdge(Collections.singleton(begin), Collections.singleton(end));
    }

    default boolean addEdge(Vertex[] begin, Vertex[] end) {
        return addEdge(Arrays.asList(begin), Arrays.asList(end));
    }

    <V extends Vertex> boolean addEdge(Collection<V> begin, Collection<V> end);

}
