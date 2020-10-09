//package die.mass.Graphs.GraphFull;
//
//import die.mass.Graphs.parts.edges.Edge;
//import die.mass.Graphs.GraphDirected.GraphDirectedMatrix;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//public class GraphFullMatrix<V, E> extends GraphDirectedMatrix<V> implements GraphFull<V,E> {
//
//    private ArrayList<ArrayList<ArrayList<ArcDirected<V,E>>>> matrix;
//
//    public GraphFullMatrix() {
//        this.matrix = new ArrayList<>();
//    }
//
//    public GraphFullMatrix(Collection<Vertex<V>> c) {
//        this();
//        c.forEach(this::addVertex);
//    }
//
//    @Override
//    public boolean addEdge(Vertex<V> begin, Vertex<V> end, E edge) {
//        return addEdge(new ArcDirected<>(begin, end, edge));
//    }
//
//    @Override
//    public <T extends Edge<V>> boolean addEdge(T edge, E weight) {
//        return false;
//    }
//}