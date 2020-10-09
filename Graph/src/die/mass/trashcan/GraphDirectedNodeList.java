//package die.mass.Graphs.GraphDirected;
//
//import die.mass.Graphs.Graph.GraphNodeList;
//import die.mass.Graphs.parts.edges.DirectedEdge;
//import die.mass.Graphs.parts.edges.Edge;
//import die.mass.Graphs.parts.vertices.Vertex;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.*;
//
//public class GraphDirectedNodeList<V> extends GraphNodeList<V> implements GraphDirected<V> {
//
//    private TreeMap<Vertex<V>, TreeMap<Vertex<V>, TreeSet<Arrow<V>>>> graph;
//
//    public GraphDirectedNodeList() {
//        this.graph = new TreeMap<>();
//    }
//
//    private enum Methods {
//        getBegin("getBegin"), getEnd("getEnd");
//
//        Methods(String name) {
//        }
//    }
//
//    private <Q extends Vertex<V>> TreeSet<Q> gettingRelatives(Q vertex, String method) {
//        TreeMap<Vertex<V>, TreeSet<Edge<V>>> tm = getGraph().get(vertex);
//        TreeSet<Q> answer = new TreeSet<>();
//        try {
//            Method m = AbstractEdge.class.getDeclaredMethod(method);
//            m.setAccessible(true);
//            for (Vertex<V> i : tm.keySet()) {
//                for (Edge<V> e : tm.get(i)) {
//                    if (m.invoke(e) == vertex) {
//                        answer.add(e.getAnotherVertices(vertex));
//                        break;
//                    }
//                }
//            }
//        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        answer.remove(vertex);
//        return answer;
//    }
//
//    @Override
//    public <Q extends Vertex<V>> TreeSet<Q> getChildren(Q vertex) {
//        return gettingRelatives(vertex, Methods.getBegin.name());
//    }
//
//    @Override
//    public <Q extends Vertex<V>> TreeSet<Q> getParents(Q vertex) {
//        return gettingRelatives(vertex, Methods.getEnd.name());
//    }
//
//    private <Q extends Vertex<V>, T extends DirectedEdge<V>> TreeSet<T> gettingEdges(String method, Q vertex) {
//        TreeSet<T> answer = new TreeSet<>();
//        TreeMap<Vertex<V>, TreeSet<Edge<V>>> tm = getGraph().get(vertex);
//        try {
//            Method m = Edge.class.getMethod(method);
//            m.setAccessible(true);
//            for (Vertex<V> i : tm.keySet()) {
//                for (Edge<V> e : tm.get(i)) {
//                    if (m.invoke(i).equals(vertex)) answer.add((T) i);
//                }
//
//            }
//        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        return answer;
//    }
//
//    @Override
//    public <Q extends Vertex<V>, T extends DirectedEdge<V>> TreeSet<T> getExitingEdges(Q vertex) {
//        return gettingEdges(Methods.getBegin.name(), vertex);
//    }
//
//    @Override
//    public <Q extends Vertex<V>, T extends DirectedEdge<V>> TreeSet<T> getEnteringEdges(Q vertex) {
//        return gettingEdges(Methods.getEnd.name(), vertex);
//    }
//}
