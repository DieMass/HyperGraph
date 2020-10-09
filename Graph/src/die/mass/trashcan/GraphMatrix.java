//package die.mass.Graphs.Graph;
//
//import die.mass.Graphs.parts.edges.Edge;
//import die.mass.Graphs.parts.vertices.Vertex;
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.TreeMap;
//import java.util.TreeSet;
//
//public class GraphMatrix<V> extends AbstractGraph<V> implements Graph<V> {
//
//    private ArrayList<ArrayList<TreeSet<Edge<V>>>> matrix;
//    protected TreeMap<Integer, Vertex<V>> vertices;
//
//    public GraphMatrix() {
//        this.vertices = new TreeMap<>();
//        this.matrix = new ArrayList<>();
//    }
//
//    @Override
//    public <Q extends Vertex<V>> Collection<Q> getVertices() {
//        return (Collection<Q>) vertices.values();
//    }
//
//    protected <T extends Edge<V>> ArrayList<ArrayList<TreeSet<T>>> getPrivateMatrix() {
//        try {
//            Field matric = this.getClass().getDeclaredField("matrix");
//            matric.setAccessible(true);
//            return (ArrayList<ArrayList<TreeSet<T>>>) matric.get(this);
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return new ArrayList<>();
//    }
//
//    protected <Q extends Vertex<V>, T extends Edge<V>> boolean addingVertex(Q vertex) {
//        ArrayList<ArrayList<TreeSet<T>>> matrics = getPrivateMatrix();
//        int c = matrics.size();
//        for (int i = 0; i < matrics.size(); i++) {
//            matrics.get(i).add(new TreeSet<>());
//        }
//        matrics.add(new ArrayList<>());
//        for (int i = 0; i <= c; i++) {
//            matrics.get(c).add(new TreeSet<>());
//        }
//        vertices.put(vertices.size(),vertex);
//        return true;
//    }
//
//    @Override
//    public <Q extends Vertex<V>, T extends Edge<V>> boolean removeVertex(Q vertex) {
//        return removingVertex((Vertex<V>) vertex);
//    }
//
//    protected <T extends Edge<V>> boolean removingVertex(Vertex<V> vertex) {
//        vertices.;
//        ArrayList<ArrayList<TreeSet<T>>> matrics = getPrivateMatrix();
//        matrics.remove(c);
//        matrics.forEach(i -> i.remove(c));
//        return vertices.remove(vertex);
//    }
//
//    public <Q extends Vertex<V>, T extends Edge<V>> boolean removeAllEdgesBetweenVertices(Q begin, Q end) {
//        int b = checkToContainsVertex(begin);
//        int e = checkToContainsVertex(end);
//        if (b != -1 && e != -1) {
//            getPrivateMatrix().get(e).get(b).clear();
//            getPrivateMatrix().get(b).get(e).clear();
//            return getPrivateMatrix().get(e).get(b).isEmpty() && getPrivateMatrix().get(b).get(e).isEmpty();
//        }
//        return false;
//    }
//
//    @Override
//    public <Q extends Vertex<V>, T extends Edge<V>> TreeSet<T> getEdgesBetweenVertices(Q begin, Q end) {
//        int b = checkToContainsVertex(begin);
//        int e = checkToContainsVertex(end);
//        return b != -1 && e != -1 ? (TreeSet<T>) getPrivateMatrix().get(e).get(b) : new TreeSet<>();
//    }
//
//    protected <Q extends Vertex<V>> int checkToContainsVertex(Q vertex) {
//        int c = vertices.indexOf(vertex);
//        if (c == -1)
//            System.err.println("Вершина " + vertex + " не принадлежит графу " + this.getClass().getSimpleName());
//        return c;
//    }
//
//    @Override
//    public <Q extends Vertex<V>, T extends Edge<V>> TreeSet<Q> getNeighbors(Q vertex) {
//        ArrayList<ArrayList<TreeSet<T>>> matrics = getPrivateMatrix();
//        TreeSet<Q> list = new TreeSet<>();
//        int c = vertices.indexOf(vertex);
//        matrics.get(c).forEach(i -> {
//            Q v = (Q) vertices.get(matrics.get(c).indexOf(i));
//            if (!i.isEmpty() && !v.equals(vertex)) list.add(v);
//        });
//        return list;
//    }
//
//    @Override
//    public <Q extends Vertex<V>, T extends Edge<V>> ArrayList<T> getIncidentEdges(Q vertex) {
//        ArrayList<T> list = new ArrayList<>();
//        int c = vertices.indexOf(vertex);
//        if (c == -1) {
//            System.err.println("Вершина не принадлежит графу");
//            return list;
//        }
//        getPrivateMatrix().get(c).forEach(i -> list.addAll((Collection<T>) i));
//        getPrivateMatrix().forEach(i -> list.addAll((Collection<T>) i.get(c)));
//        return list;
//    }
//
//    protected <Q extends Vertex<V>, T extends Edge<V>> boolean addingEdge(T edge) {
//        int[] a = createAIndexes(edge, true);
//        ArrayList<ArrayList<TreeSet<T>>> matric = getPrivateMatrix();
//        if (this.isDirected) return matric.get(a[0]).get(a[1]).add(edge);
//        if (a[0] != a[1]) return matric.get(a[1]).get(a[0]).add(edge) && matric.get(a[0]).get(a[1]).add(edge);
//        return matric.get(a[0]).get(a[0]).add(edge);
//    }
//
//    @Override
//    public <T extends Edge<V>> boolean removeEdge(T edge) {
//        int[] a = createAIndexes(edge, false);
//        ArrayList<ArrayList<TreeSet<T>>> matrics = getPrivateMatrix();
//        if (a[0] != -1 || a[1] != -1) {
//            if (a[0] != a[1])   //тут одинарный амперсанд, ибо надо оба действия выполнять
//                return matrics.get(a[1]).get(a[0]).remove(edge) & matrics.get(a[0]).get(a[1]).remove(edge);
//            return matrics.get(a[0]).get(a[0]).remove(edge);
//        }
//        return false;
//    }
//
//    @Override
//    public <T extends Edge<V>> boolean containsEdge(T edge) {
//        int[] a = createAIndexes(edge, false);
//        if (a == null) return false;
//        ArrayList<ArrayList<TreeSet<T>>> matrics = getPrivateMatrix();
//        return matrics.get(a[1]).get(a[0]).contains(edge) || matrics.get(a[0]).get(a[1]).contains(edge);
//    }
//
//
//    protected <T extends Edge<V>> int[] createAIndexes(T edge, boolean adding) {
//        int[] a = {vertices.indexOf(edge.getBegin()), vertices.indexOf(edge.getEnd())};
//        for (int i = 0; i < 2; i++) {
//            if (a[i] == -1) {
//                if (adding) {
//                    addVertex((Vertex<V>) (i == 0 ? edge.getBegin() : edge.getEnd()));
//                    a[i] += vertices.size();
//                } else return null;
//            }
//        }
//        return a;
//    }
//
//
//    @Override
//    public <T extends Edge<V>> ArrayList<T> getEdges() {
//        ArrayList<ArrayList<TreeSet<T>>> matrics = getPrivateMatrix();
//        ArrayList<T> list = new ArrayList<>();
//        int c = vertices.size();
//        for (int i = 0; i < c; i++) {
//            int b = i;
//            if (this.isDirected) b = c - 1;
//            for (int j = 0; j <= b; j++) {
//                list.addAll(matrics.get(i).get(j));
//            }
//        }
//        return list;
//    }
//
//    @Override
//    public int countOfEdges() {
//        return getEdges().size();
//    }
//
//    @Override
//    public void clearEdges() {
//        matrix.forEach(i -> i.forEach(Collection::clear));
//    }
//
//    @Override
//    public void clearAll() {
//        vertices.clear();
//        getPrivateMatrix().clear();
//    }
//
//
//    @Override
//    public String toString() {
//        return createString();
//    }
//
//    //вынес в отдельный метод для параметризации матрицы
//    private <T extends Edge<V>> String createString() {
//        ArrayList<ArrayList<TreeSet<T>>> matrics = getPrivateMatrix();
//        int[] a = {0};
//        StringBuilder sb = new StringBuilder();
//        sb.append("   ");
//        for (int i = 0; i < matrics.size(); i++) {
//            sb.append(i + 1);
//            sb.append(" ");
//        }
//        sb.append("\n");
//        for (int i = 0; i < matrics.size(); i++) {
//            sb.append(a[0] + 1);
//            sb.append("  ");
//            matrics.forEach(j -> {
//                sb.append(j.get(a[0]).size());
//                sb.append(" ");
//            });
//            sb.append("\n");
//            a[0]++;
//        }
//        return sb.toString();
//    }
//
//}
