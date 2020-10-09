//package die.mass.Graphs.GraphDirected;
//
//import die.mass.Graphs.Graph.GraphMatrix;
//import die.mass.Graphs.parts.edges.DirectedEdge;
//import die.mass.Graphs.parts.edges.Edge;
//import die.mass.Graphs.parts.vertices.Vertex;
//
//import java.util.ArrayList;
//import java.util.TreeSet;
//
//public class GraphDirectedMatrix<V> extends GraphMatrix<V> implements GraphDirected<V> {
//
//    private ArrayList<ArrayList<ArrayList<GraphDirected.Arrow<V>>>> matrix;
//
//    public GraphDirectedMatrix() {
//        matrix = new ArrayList<>();
//    }
//
//    @Override
//    public <Q extends Vertex<V>, T extends Edge<V>> TreeSet<Q> getNeighbors(Q vertex) {
//        TreeSet<Q> list = getChildren(vertex);
//        list.addAll(getParents(vertex));
//        return list;
//    }
//
//    @Override
//    public <Q extends Vertex<V>> TreeSet<Q> getChildren(Q vertex) {
//        return gettingRelatives(vertex, false);
//    }
//
//    @Override
//    public <Q extends Vertex<V>> TreeSet<Q> getParents(Q vertex) {
//        return gettingRelatives(vertex, true);
//    }
//
//    protected <Q extends Vertex<V>, T extends Edge<V>> TreeSet<Q> gettingRelatives(Q vertex, boolean isParents) {
//        ArrayList<ArrayList<TreeSet<T>>> matrics = getPrivateMatrix();
//        TreeSet<Q> list = new TreeSet<>();
//        int a[] = {vertices.indexOf(vertex), 0};
//        if (!isParents)
//            a[1] = a[0];                 //если дети — чекаем к-тую строку (i,к), если родители — к-тый столбец (к,i)
//        for (int i = 0; i < vertices.size(); i++) {
//            if (isParents) a[1] = i;
//            else a[0] = i;
//            if (!matrics.get(a[1]).get(a[0]).isEmpty()) list.add((Q) vertices.get(i));
//        }
//        list.remove(vertex);
//        return list;
//    }
//
//    @Override
//    public <Q extends Vertex<V>, T extends DirectedEdge<V>> ArrayList<T> getExitingEdges(Q vertex) {
//        return gettingEdges(vertex, true);
//    }
//
//    @Override
//    public <Q extends Vertex<V>, T extends DirectedEdge<V>> ArrayList<T> getEnteringEdges(Q vertex) {
//        return gettingEdges(vertex, false);
//    }
//
//    private <Q extends Vertex<V>, T extends DirectedEdge<V>> ArrayList<T> gettingEdges(Q vertex, boolean isExiting) {
//        ArrayList<T> list = new ArrayList<>();
//        int c = checkToContainsVertex(vertex);
//        if (c != -1) {
//            ArrayList<ArrayList<TreeSet<T>>> matrics = getPrivateMatrix();
//            if (isExiting) matrics.forEach(i -> list.addAll(i.get(c)));
//            else matrics.get(c).forEach(list::addAll);
//        }
//        return list;
//    }
//}
