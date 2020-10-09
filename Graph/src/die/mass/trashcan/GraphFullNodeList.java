//package die.mass.Graphs.GraphFull;
//
//import die.mass.Graphs.parts.edges.Edge;
//import die.mass.Graphs.GraphDirected.GraphDirectedNodeList;
//
//import java.util.TreeMap;
//import java.util.TreeSet;
//
//public class GraphFullNodeList<V, E> extends GraphDirectedNodeList<V> implements GraphFull<V,E> {
//
//    private TreeMap<Vertex<V>, TreeMap<Vertex<V>, TreeSet<ArcDirected<V, E>>>> graph;
//
//    public GraphFullNodeList() {
//        this.graph = new TreeMap<>();
//    }
//
//    @Override
//    public boolean addEdge(Vertex<V> begin, Vertex<V> end, E edge) {
//        return addEdge(new ArcDirected<>(begin, end, edge));
//
//    }
//
//    @Override
//    public <T extends Edge<V>> boolean addEdge(T edge, E weight) {
//        return addEdge(new ArcDirected<>(edge.getBegin(), edge.getEnd(), weight));
//    }
//}
