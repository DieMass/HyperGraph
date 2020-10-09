//package die.mass.Graphs.GraphWithWeight;
//
//import die.mass.Graphs.Graph.GraphMatrix;
//import die.mass.Graphs.parts.edges.Edge;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//public class GraphWeightMatrix<V, E> extends GraphMatrix<V> implements GraphWeight<V, E> {
//
//    private ArrayList<ArrayList<ArrayList<Arc<V,E>>>> matrix;
//
//    public GraphWeightMatrix() {
//        this.matrix = new ArrayList<>();
//    }
//
//    public GraphWeightMatrix(Collection<Vertex<V>> c ) {
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
