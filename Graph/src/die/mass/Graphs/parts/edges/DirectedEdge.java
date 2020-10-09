package die.mass.Graphs.parts.edges;

import die.mass.Graphs.parts.vertices.Vertex;

import java.util.Collection;

public interface DirectedEdge extends Edge {

    Collection<Vertex> getBegin();
    Collection<Vertex> getEnd();

}
