package die.mass.Graphs.GraphSimple;

import die.mass.Graphs.Graph.Graph;
import die.mass.Graphs.parts.vertices.Vertex;

import java.util.Arrays;
import java.util.Collection;

public interface GraphSimple extends Graph {

    default boolean addEdge(Vertex ... vertices) {
        return addEdge(Arrays.asList(vertices));
    }

    <V extends Vertex> boolean addEdge(Collection<V> vertices);

}
