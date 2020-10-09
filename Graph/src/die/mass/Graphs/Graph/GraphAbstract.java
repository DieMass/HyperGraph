package die.mass.Graphs.Graph;

import die.mass.Graphs.GraphDirected.GraphDirectedGeneral;
import die.mass.Graphs.GraphWeight.GraphWeightGeneral;
import die.mass.Graphs.parts.edges.*;
import die.mass.Graphs.parts.vertices.EmptyVertex;
import die.mass.Graphs.parts.vertices.ValueVertex;
import die.mass.Graphs.parts.vertices.Vertex;
import die.mass.Graphs.parts.vertices.VertexGenerator;
import die.mass.Tree.Tree.Tree;

import java.util.*;


public abstract class GraphAbstract implements Graph {

    protected int uniformCount;
    protected Class<? extends Vertex> vertexType;
    protected Class<? extends Edge> edgeType;
    protected Class<?> edgeWeightType;
    protected boolean isDirected;
    protected boolean isWeight;
    protected boolean isTree;
    protected VertexGenerator vertexGenerator;
    protected EdgeGenerator edgeGenerator;

    protected GraphAbstract() {
        this.isDirected = GraphDirectedGeneral.class.isAssignableFrom(this.getClass());
        this.isWeight = GraphWeightGeneral.class.isAssignableFrom(this.getClass());
        this.isTree = Tree.class.isAssignableFrom(this.getClass());
        this.vertexGenerator = new VertexGenerator();
        this.edgeGenerator = new EdgeGenerator();
        this.vertexType = GraphEmpty.class.isAssignableFrom(this.getClass()) ? EmptyVertex.class : ValueVertex.class;
        this.edgeType = !isDirected && !isWeight ? SimpleEdge.class : isDirected && isWeight ? ArcDirected.class : isDirected ? Arrow.class : Arc.class;
    }

    protected abstract <V extends Vertex> Collection<V> getPrivateVertices();

    protected abstract <V extends Edge> Collection<V> getPrivateEdges();

    @Override
    public int countOfVertices() {
        return getVertices().size();
    }

    protected <V extends Vertex> boolean checkEqualsingOfVertices(Collection<V> vertices) {
        if (vertices.size() != uniformCount) {
            System.out.println("Разное количество вершин (" + vertices.size() + " != " + uniformCount + ")");
            return false;
        }
        for (Vertex vertex : vertices) {
            if (!vertex.getClass().equals(vertexType)) {
                System.err.println("Разный тип вершин (" + vertex.getClass().getSimpleName() + " != " + vertexType.getSimpleName() + ")");
                return false;
            }
        }
        return true;
    }

    @Override
    public <V extends Vertex> boolean removeVertex(V vertex) {
        if (vertexMayBelongToGraph(vertex) && getPrivateVertices().contains(vertex)) {
            getPrivateEdges().forEach(i -> {
                if (i.getVertices().contains(vertex)) getPrivateEdges().remove(i);
            });
            return getPrivateVertices().remove(vertex);
        }
        return false;
    }

    @Override
    public <V extends Vertex> boolean containsVertex(V vertex) {
        return vertexMayBelongToGraph(vertex) && getPrivateVertices().contains(vertex);
    }

    private <V extends Vertex> boolean vertexMayBelongToGraph(V vertex) {
        return vertex.getClass().equals(vertexType) &&
                (!ValueVertex.class.isAssignableFrom(vertexType) ||
                        ((GraphNotEmpty) this).getVertexWeightType().equals(((ValueVertex) vertex).getValue().getClass()));
    }

    @Override
    public <T extends Edge> boolean removeEdge(T edge) {
        return edgeMayBelongToGraph(edge) && getPrivateEdges().remove(edge);
    }

    @Override
    public <T extends Edge> boolean containsEdge(T edge) {
        return edgeMayBelongToGraph(edge) && getPrivateEdges().contains(edge);
    }

    private <T extends Edge> boolean edgeMayBelongToGraph(T edge) {
        return edge.getClass().equals(edgeType) &&
                (!WeightEdge.class.isAssignableFrom(edgeType) ||
                        ((GraphWeightGeneral) this).getEdgeWeightType().equals(((WeightEdge) edge).getWeight().getClass()));
    }

    @Override
    public Collection<? extends Edge> getEdges() {
        return getPrivateEdges();
    }

    @Override
    public int countOfEdges() {
        return getPrivateEdges().size();
    }

    @Override
    public void clearEdges() {
        getPrivateEdges().clear();
    }

    @Override
    public void clearAll() {
        getPrivateEdges().clear();
        getPrivateVertices().clear();
    }

    public <V extends Vertex> boolean containsVertices(Collection<V> vertices) {
        for (Vertex vertex : vertices) {
            if (!containsVertex(vertex)) return false;
        }
        return true;
    }

    //писался для неорграфов чтобы работал поиск циклов над ними, поэтому не запаривался насчёт орграфов
    @Override
    public <E extends Edge, V extends Vertex> Collection<E> getEdgesBetweenVertices(Collection<V> vertices) {
        Collection<E> edges = new HashSet<>();
        if (containsVertices(vertices))
            getPrivateEdges().stream().filter(edge -> edge.getVertices().containsAll(vertices)).forEach(edge -> edges.add((E) edge));
        return edges;
    }

    @Override
    public <E extends Edge, V extends Vertex> Collection<E> getEdgesBetweenVertices(V... vertices) {
        return getEdgesBetweenVertices(Arrays.asList(vertices));
    }

    //    @Override
//    public <T extends Graph> boolean containsAll(T graph) {
//        if (new TreeSet<>(Arrays.asList(this.getClass().getInterfaces())).containsAll(new TreeSet<>(Arrays.asList(graph.getClass().getInterfaces())))) {
//            Collection<Vertex> vertices1ist = getVertices();
//            ArrayList<? extends Edge> edgeslist = null;
//            try {
//                Method m = this.getClass().getMethod("getEdges");
//                m.setAccessible(true);
//                edgeslist = (ArrayList<? extends Edge>) m.invoke(this);
//            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//                e.printStackTrace();
//            }
//            boolean a[] = {true};
//            graph.getVertices().forEach(i -> {
//                if (a[0]) {
//                    a[0] = vertices1ist.contains(i);
//                }
//            });
//            if (a[0]) {
//                ArrayList<? extends Edge> finalEdgeslist = edgeslist;
//                graph.getEdges().forEach(i -> {
//                    if (a[0] && finalEdgeslist != null) {
//                        a[0] = finalEdgeslist.contains(i);
//                    }
//                });
//            }
//            return a[0];
//        }
//        System.out.print("First graph is " + this.getClass().getSimpleName() + " and it implements: ");
//        Arrays.asList(this.getClass().getInterfaces()).forEach(i -> System.out.println(i.getSimpleName()));
//        System.out.print("Second graph is " + graph.getClass().getSimpleName() + " and it implements: ");
//        Arrays.asList(graph.getClass().getInterfaces()).forEach(i -> System.out.println(i.getSimpleName()));
//        System.err.println("ЭТО РАЗНЫЕ ГРАФЫ КАК ТЫ СРАВНИШЬ ТАБУРЕТКУ И КРЕСЛО АЛО");
//        return false;
//    }
//
//    @Override
//    public <T extends Graph> boolean addAll(T graph) {
//        boolean[] a = {true};
//        graph.getVertices().forEach(i -> {
//            if (a[0] && !containsVertex(i)) {
//                a[0] = addVertex(i);
//            }
//        });
//        graph.getEdges().forEach(i -> {
//            if (a[0] && !containsEdge(i)) {
//                a[0] = addEdge(i);
//            }
//        });
//        return a[0];
//    }
//
//    @Override
//    public <Q extends Vertex, T extends Graph> boolean removeAll(T graph) {
//        boolean[] a = {true};
//        ArrayList<Q> vertices1ist = (ArrayList<Q>) graph.getVertices();
//        vertices1ist.forEach(i -> {
//            a[0] = removeVertex(i);
//        });
//        return a[0];
//    }
    //TODO: сделать без использования getEdgesBetweenVertices
//    @Override
//    public <Q extends Vertex, T extends Edge>> boolean removeAllEdgesBetweenVertices(Q begin, Q end) {
//        return getPrivateEdges().removeAll(getEdgesBetweenVertices(begin, end));
//    }
//
//    @Override
//    public <Q extends Vertex<V>, T extends Edge<V>> ArrayList<T> getIncidentEdges(Q vertex) {
//        ArrayList<T> list = new ArrayList<>();
//        getPrivateEdges().forEach(i -> {
//            if (i.getBegin().equals(vertex) || i.getEnd().equals(vertex)) {
//                list.add((T) i);
//            }
//        });
//        return list;
//    }

    @Override
    public <Q extends Vertex> List<Q> getShortestPath(Q begin, Q end) {
        return breadthFirstSearch(begin, end);
    }

    public <Q extends Vertex> List<Q> getShortestPath(Collection<Q> begin, Collection<Q> end) {
        return null;//breadthFirstSearch(begin, end);
    }

    //"поиск в ширину" с басурманского
    private <Q extends Vertex> List<Q> breadthFirstSearch(Q begin, Q end) {
        TreeMap<Q, Q> parents = new TreeMap<>();       //map child -> parent
        Queue<Q> queue = new ArrayDeque<>();           //vertices from begin to end path
        if (addToQueue(begin, end, queue, parents)) return addToPath(parents, end, begin);
        return new ArrayList<>();
    }

    private <Q extends Vertex> boolean addToQueue(Q vertex, Q end, Queue<Q> queue, TreeMap<Q, Q> parents) {
        boolean[] f = {false};
        TreeSet<Q> buf = new TreeSet<>(isDirected ? ((GraphDirectedGeneral) this).getChildren(Collections.singleton(vertex)) : this.getNeighbors(Collections.singleton(vertex)));
        buf.forEach(i -> {
            if (!f[0]) {
                if (!parents.containsKey(i)) {
                    parents.put(i, vertex);
                    queue.add(i);
                }
                if (i == end) {
                    f[0] = true;
                }
            }
        });
        if (!f[0]) {
            if (queue.peek() != null) {
                return addToQueue(queue.poll(), end, queue, parents);
            } else {
                return false;
            }
        }
        return true;
    }

    private <Q extends Vertex> List<Q> addToPath(TreeMap<Q, Q> parents, Q end, Q begin) {
        List<Q> path = new ArrayList<>();
        Q bufVertex = end;
        while (bufVertex != begin) {             //метод вызывается, когда точно есть путь от начала до конца, значит тут while в бесконечность не уйдёт
            path.add(bufVertex);                 //добавляю вершины с конца
            bufVertex = parents.get(bufVertex);
        }
        path.add(begin);                        //добавляю начало, ибо на нём цикл завершился
        Collections.reverse(path);              //разворачиваю лист, чтобы элементы встали в правильный поряок
        return path;
    }

    @Override
    public <V extends Vertex> Collection<V> getNeighbors(Collection<V> vertices) {
        Collection<Vertex> answer = new HashSet<>();
        Collection<Edge> edges = getPrivateEdges();
        edges.stream().filter(edge -> edge.getVertices().containsAll(vertices)).
                forEach(edge -> answer.addAll(edge.getVertices()));
        answer.removeAll(vertices);
        return (Collection<V>) answer;
    }

    @Override
    public boolean isCycled() {
        return depthFirstSearch();
    }

    private <Q extends Vertex> boolean depthFirstSearch() {
        Set<Q> grey = new HashSet<>();
        Set<Q> black = new HashSet<>();
        Collection<Q> vertices = getPrivateVertices();
        for (Vertex vertex : vertices) {
            grey.add((Q) vertex);
            if (!black.contains(vertex)) {
                boolean answer = checkToCycled(grey, black, null, (Q) vertex);
                if (answer) return true;
            }
        }
        return false;
    }

    private <Q extends Vertex> boolean checkToCycled(Set<Q> grey, Set<Q> black, Q parent, Q vertex) {
        Set<Q> set = new HashSet<>(isDirected ?
                ((GraphDirectedGeneral) this).getChildren(Collections.singleton(vertex))
                : this.getNeighbors(Collections.singleton(vertex)));
        if (!set.isEmpty()) {
            for (Q child : set) {
                if (!isDirected && countEdgesBetweenVerticesLessThanTwo(vertex, child)) {
                    return true;
                }
                if (isDirected || child != parent) {
                    boolean isGrey = grey.contains(child);
                    boolean isBlack = black.contains(child);
                    if (isGrey && !isBlack) {
                        return true;
                    }
                    if (isGrey && isBlack) continue;
                    grey.add(child);
                    boolean answer = checkToCycled(grey, black, vertex, child);
                    if (answer) return true;
                }
            }
        }
        black.add(vertex);
        return false;
    }

    private <Q extends Vertex> boolean countEdgesBetweenVerticesLessThanTwo(Q vertex, Q child) {
        Edge[] edgis = {null};
        Collection<Edge> edges = getPrivateEdges();
        boolean f = getPrivateEdges().stream()
                .filter(edge -> edge.getVertices().contains(vertex) && edge.getVertices().contains(child)).count() > 1;
//        for (Edge edge : edges) {
//            if (edge.getVertices().contains(vertex) && edge.getVertices().contains(child)) {
//                if (edgis[0] == null) {
//                    edgis[0] = edge;
//                } else {
//                    System.out.println(edgis[0] + " " + edge);
//                    return false;
//                }
//            }
//        }
//        return true;
        return f;
    }

    @Override
    public boolean isDirected() {
        return isDirected;
    }

    @Override
    public boolean isWeight() {
        return isWeight;
    }

    @Override
    public boolean isTree() {
        return isTree;
    }

    @Override
    public int getUniformCount() {
        return uniformCount;
    }

    public Class<?> getVertexType() {
        return vertexType;
    }

    public Class<?> getEdgeType() {
        return edgeType;
    }

    @Override
    public boolean isEmpty() {
        return getPrivateVertices().isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append(":\nVertices");
        sb.append(getPrivateVertices());
        sb.append("\nEdges");
        sb.append(getPrivateEdges());
        ArrayList<String> list = new ArrayList<>();
        var list1 = new ArrayList<>();
        return sb.toString();
    }

    protected abstract class GenericGraphBuilder<B extends GenericGraphBuilder<B>> {

        public B setUniformCount(int uniformCount) {
            GraphAbstract.this.uniformCount = uniformCount;
            return self();
        }

        @SuppressWarnings("unchecked")
        protected B self() {
            return (B) this;
        }

        public abstract <G extends Graph> G build();
    }

}
