//package die.mass.Graphs.Graph;
//
//import die.mass.Graphs.parts.edges.Edge;
//import die.mass.Graphs.parts.vertices.Vertex;
//
//import java.lang.reflect.Field;
//import java.util.*;
//
//public class GraphNodeList<V> extends AbstractGraph<V> implements Graph<V> {
//
//    private TreeMap<Vertex<V>, TreeMap<Vertex<V>, TreeSet<Edge<V>>>> graph;
//    //Множество отображений из вершины в дерево, каждый элемент которого есть отображение
//    //из вершины, инцидентной ключу, в множество рёбер, инцидентных обоим вершинам
//    //я сам охуел, пока это писал
//
//    public GraphNodeList() {
//        this.graph = new TreeMap<>();
//    }
//
//    @Override
//    public <Q extends Vertex<V>> Set<Q> getVertices() {
//        return (Set<Q>) getGraph().keySet();  //O(1), cause keySet is a field of TreeSet
//    }
//
//    protected <Q extends Vertex<V>, T extends Edge<V>> TreeMap<Q, TreeMap<Q, TreeSet<T>>> getGraph() {
//        try {
//            Field matric = this.getClass().getDeclaredField("graph");
//            matric.setAccessible(true);
//            return (TreeMap<Q, TreeMap<Q, TreeSet<T>>>) matric.get(this);
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return new TreeMap<>();
//    }
//
//    protected <Q extends Vertex<V>, T extends Edge<V>> boolean addingVertex(Q vertex) {
//        if (!getGraph().containsKey(vertex)) {
//            getGraph().put(vertex, new TreeMap());
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public <Q extends Vertex<V>, T extends Edge<V>> boolean removeVertex(Q vertex) {
//        if (getGraph().containsKey(vertex)) {
//            getGraph().get(vertex).keySet().forEach(i -> getGraph().get(i).remove(vertex));
//            getGraph().remove(vertex);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public void clearAll() {
//        getGraph().clear();
//    }
//
//    @Override
//    public <Q extends Vertex<V>, T extends Edge<V>> Collection<Q> getNeighbors(Q vertex) {
//        TreeSet<Q> c = new TreeSet(getGraph().get(vertex).keySet());
//        c.remove(vertex);
//        return c;
//    }
//
//    public <Q extends Vertex<V>, T extends Edge<V>> boolean removeAllEdgesBetweenVertices(Q begin, Q end) {
//        if (getGraph().containsKey(begin) && getGraph().containsKey(end)) {
//            getGraph().get(begin).remove(end);
//            getGraph().get(end).remove(begin);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public <Q extends Vertex<V>, T extends Edge<V>> Collection<T> getEdgesBetweenVertices(Q begin, Q end) {
//        return (Collection<T>) getGraph().get(begin).get(end);
//    }
//
//    @Override
//    public <Q extends Vertex<V>, T extends Edge<V>> Collection<T> getIncidentEdges(Q vertex) {
//        TreeSet<T> tree = new TreeSet<>();
//        getGraph().get(vertex).values().forEach(i -> tree.addAll((Collection<? extends T>) i));
//        return tree;
//    }
//
//    @Override
//    protected <Q extends Vertex<V>, T extends Edge<V>> boolean addingEdge(T edge) {
//        TreeSet<T> set = new TreeSet<>();
//        set.add(edge);
//        for(Vertex<V> vertex: edge.getVertices()) {
//            addVertex(vertex);
//            TreeMap<Vertex<V>, TreeSet<Edge<V>>> its = getGraph().get(vertex);
//            if (!its.containsKey(edge.getAnotherVertices(vertex))) its.put(edge.getAnotherVertices(vertex), (TreeSet<Edge<V>>) set);
//            else return its.get(edge.getAnotherVertices(vertex)).add(edge);
//        }
//        return true;
//    }
//
//    @Override
//    public <T extends Edge<V>> boolean removeEdge(T edge) {
//        if (getGraph().containsKey(edge.getBegin()) && getGraph().containsKey(edge.getEnd())) {
//            edge.getVertices().forEach(i -> {
//                getGraph().get(i).get(edge.getAnotherVertices(i)).remove(edge);
//                if (getGraph().get(i).get(edge.getAnotherVertices(i)).isEmpty()) {
//                    getGraph().get(i).remove(edge.getAnotherVertices(i));
//                }
//            });
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public <T extends Edge<V>> boolean containsEdge(T edge) {
//        return getGraph().get(edge.getVertexWithSmallestID()).get(edge.getAnotherVertices(edge.getVertexWithSmallestID())).contains(edge);
//    }
//
//    //TODO: можно написать красиво, чтобы не чекал два раза одни и те же рёбра
//    @Override
//    public <T extends Edge<V>> TreeSet<T> getEdges() {
//        TreeSet<T> tree = new TreeSet<>();
//        getGraph().values().forEach(i -> i.values().forEach(j -> tree.addAll((Collection<? extends T>) j)));
//        return tree;
//    }
//
//    @Override
//    public int countOfEdges() {
//        return getEdges().size();
//    }
//
//    @Override
//    public void clearEdges() {
//        getGraph().values().forEach(j -> j.values().forEach(i -> i.clear()));
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        getGraph().keySet().forEach(vertex -> {
//            sb.append(vertex);
//            sb.append(":\n");
//            getGraph().get(vertex).values().forEach(tree -> {
//                tree.forEach(edge -> {
//                    sb.append(edge);
//                    sb.append('\n');
//                });
//            });
//        });
//        return sb.toString();
//    }
//}
