package die.mass.Graphs.Graph;

import die.mass.Graphs.parts.vertices.ValueVertex;
import die.mass.Graphs.parts.vertices.Vertex;

public interface GraphNotEmpty extends Graph { //graph with weight in vertices

    Class<?> getVertexWeightType();

    <T> boolean addVertex(T value);
    <V extends Vertex> boolean removeVertex(V vertex);
    <V extends Vertex> boolean containsVertex(V vertex);
    <T> ValueVertex<T> getVertex(T value);
//    <V> boolean containsVertexValue(V value);


}
