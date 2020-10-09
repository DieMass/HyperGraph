//package die.mass.Graphs.GraphWithWeight;
//
//import die.mass.Graphs.Graph.GraphNodeList;
//import die.mass.Graphs.parts.edges.Edge;
//
//import java.util.Collection;
//import java.util.TreeMap;
//import java.util.TreeSet;
//
//public class GraphWeightNodeList<V,E> extends GraphNodeList<V> implements GraphWeight<V,E> {
//
//    private TreeMap<Vertex<V>, TreeMap<Vertex<V>, TreeSet<Arc<V, E>>>> graph;
//
//    public GraphWeightNodeList() {
//        this.graph = new TreeMap<>();
//    }
//
//    public GraphWeightNodeList(Collection<Vertex<V>> c) {
//        this();
//        c.forEach(this::addVertex);
//    }
//
//    @Override
//    public boolean addEdge(Vertex<V> begin, Vertex<V> end, E edge) {
//        return false;
//    }
//
//    @Override
//    public <T extends Edge<V>> boolean addEdge(T edge, E weight) {
//        return false;
//    }
//}
