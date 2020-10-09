package die.mass.Graphs.Graph;

import die.mass.Graphs.parts.edges.Edge;
import die.mass.Graphs.parts.vertices.Vertex;

import java.util.*;

public interface Graph {

    <V extends Vertex> Collection<V> getVertices();

    <V extends Vertex> boolean removeVertex(V vertex);

    <V extends Vertex> boolean containsVertex(V vertex);

    int countOfVertices();

    Collection<? extends Edge> getEdges();

    <E extends Edge> boolean removeEdge(E edge);

    <E extends Edge> boolean containsEdge(E edge);

    int countOfEdges();


    <E extends Edge, V extends Vertex> Collection<E> getEdgesBetweenVertices(V... vertices);

    <E extends Edge, V extends Vertex> Collection<E> getEdgesBetweenVertices(Collection<V> vertices);

    <V extends Vertex> Collection<V> getNeighbors(Collection<V> vertices);

    //TODO: getShortestPath between two collections
    <Q extends Vertex> List<Q> getShortestPath(Q begin, Q end);

    void clearEdges();

    void clearAll();

    boolean isEmpty();

    boolean isCycled();

    boolean isTree();

    boolean isWeight();

    boolean isDirected();

    int getUniformCount();
}
