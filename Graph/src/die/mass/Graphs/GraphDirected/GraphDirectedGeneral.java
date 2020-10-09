package die.mass.Graphs.GraphDirected;

import die.mass.Graphs.Graph.Graph;
import die.mass.Graphs.parts.edges.DirectedEdge;
import die.mass.Graphs.parts.edges.Edge;
import die.mass.Graphs.parts.edges.WeightEdge;
import die.mass.Graphs.parts.vertices.ValueVertex;
import die.mass.Graphs.parts.vertices.Vertex;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

public interface GraphDirectedGeneral extends Graph {

    @Override
    default <V extends Vertex> Collection<V> getNeighbors(Collection<V> vertices) {
        Collection<V> answer = getChildren(vertices);
        answer.addAll(getParents(vertices));
        return answer;
    }   //TODO: optimize

    default <Q extends Vertex> Collection<Q> getChildren(Collection<Q> vertices) {
        return getRelatives(vertices, false);
    }

    default <Q extends Vertex> Collection<Q> getParents(Collection<Q> vertices) {
        return getRelatives(vertices, true);
    }

    private <Q extends Vertex> Collection<Q> getRelatives(Collection<Q> vertices, boolean isGetParents) {
        Collection<Vertex> answer = new HashSet<>();
        Collection<? extends DirectedEdge> arrows = (Collection<? extends DirectedEdge>) getEdges();
        arrows.stream().filter(edge -> isGetParents ?
                edge.getEnd().containsAll(vertices) :
                edge.getBegin().containsAll(vertices))
                .forEach(edge -> answer.addAll(edge.getEnd()));
        answer.removeAll(vertices);
        return (Collection<Q>) answer;
    }

    default <Q extends Vertex> Collection<? extends DirectedEdge> getExitingEdges(Q vertex) {
        return getExitingEdges(Collections.singleton(vertex));
    }

    default <Q extends Vertex> Collection<? extends DirectedEdge> getExitingEdges(Collection<Q> vertices) {
        return getEdgesFromVertices(vertices, true);
    }

    default <Q extends Vertex> Collection<? extends DirectedEdge> getEnteringEdges(Q vertex) {
        return getEnteringEdges(Collections.singleton(vertex));
    }

    default <Q extends Vertex> Collection<? extends DirectedEdge> getEnteringEdges(Collection<Q> vertices) {
        return getEdgesFromVertices(vertices, false);
    }

    private <Q extends Vertex> Collection<? extends DirectedEdge> getEdgesFromVertices(Collection<Q> vertices, boolean isExiting) {
        return ((Collection<DirectedEdge>) getEdges()).stream()
                .filter(edge -> isExiting ?
                        edge.getBegin().containsAll(vertices) && vertices.containsAll((edge).getBegin()) :
                        edge.getEnd().containsAll(vertices) && vertices.containsAll((edge).getEnd()))
                .collect(Collectors.toSet());
    }

}
