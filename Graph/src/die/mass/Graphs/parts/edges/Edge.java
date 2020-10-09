package die.mass.Graphs.parts.edges;

import die.mass.Graphs.parts.vertices.Vertex;

import java.util.Collection;

public interface Edge {

    <Q extends Vertex> Collection<Q> getVertices();
//    <Q extends Vertex> Collection<Q> getAnotherVertices(Q vertex);
//    <Q extends Vertex> Q getVertexWithSmallestID();
    Long getId();
    Class<? extends Vertex> getVerticesType();
    Class<?> getVerticesWeightType();
    Integer getUniformCount();

}
