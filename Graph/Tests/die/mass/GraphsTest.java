package die.mass;

import die.mass.Graphs.Graph.*;
import die.mass.Graphs.GraphDirected.*;
import die.mass.Graphs.GraphFull.*;
import die.mass.Graphs.GraphGenerator;
import die.mass.Graphs.GraphSimple.GraphSimple;
import die.mass.Graphs.GraphWeight.GraphWeight;
import die.mass.Graphs.GraphWeight.GraphWeightGeneral;
import die.mass.Graphs.parts.vertices.Vertex;
import die.mass.Tree.Tree.Tree;
import org.junit.*;

import java.util.*;
import java.util.stream.Collectors;

public class GraphsTest {

    static Map<List<Collection<Class<?>>>, List<? extends Graph>> lists = new HashMap<>();
    static GraphGenerator gg = new GraphGenerator();
    static List<Graph> graphs;
    static List<GraphDirectedGeneral> graphDirectedGenerals = new ArrayList<>();
    static List<Graph> graphNotDirectedGenerals = new ArrayList<>();
    static List<GraphWeightGeneral> graphWeightGenerals = new ArrayList<>();
    static List<GraphSimple> graphSimples = new ArrayList<>();
    static List<GraphDirected> graphDirecteds = new ArrayList<>();
    static List<GraphWeight> graphWeights = new ArrayList<>();
    static List<GraphFull> graphFulls = new ArrayList<>();
    static List<GraphEmpty> graphEmpties = new ArrayList<>();
    static List<GraphNotEmpty> graphNotEmpties = new ArrayList<>();
    static List<Tree> trees = new ArrayList<>();
    static List<GraphDirectedGeneral> treeDirectedGenerals = new ArrayList<>();
    static List<Tree> treeNotDirectedGenerals = new ArrayList<>();
    static String[] names = {"alfa", "bravo", "charlie", "delta", "echo", "foxtrot", "golf", "hotel", "india", "kilo", "lima", "papa", "whiskey", "x-ray", "yankee", "zulu"};

    @BeforeClass
    public static void fillingGraphLists() {
        graphs = Arrays.asList(
                gg.template().build(),
                gg.template().setVertexWeightType(String.class).build(),
                gg.template().setDirected().build(),
                gg.template().setDirected().setVertexWeightType(String.class).build(),
                gg.template().setEdgeWeightType(String.class).build(),
                gg.template().setEdgeWeightType(String.class).setVertexWeightType(String.class).build(),
                gg.template().setDirected().setEdgeWeightType(String.class).build(),
                gg.template().setDirected().setEdgeWeightType(String.class).setVertexWeightType(String.class).build(),
                gg.template().setTree().setVertexWeightType(String.class).build(),
                gg.template().setTree().setDirected().setVertexWeightType(String.class).build(),
                gg.template().setTree().setEdgeWeightType(String.class).setVertexWeightType(String.class).build(),
                gg.template().setTree().setDirected().setEdgeWeightType(String.class).setVertexWeightType(String.class).build()
        );
        //        каким граф должен быть, чтобы лежать в списке           каким не должен быть      куда класть
        lists.put(Arrays.asList(Collections.singleton(GraphSimple.class), Collections.emptyList()), graphSimples);
        lists.put(Arrays.asList(Collections.singleton(GraphDirected.class), Collections.emptyList()), graphDirecteds);
        lists.put(Arrays.asList(Collections.singleton(GraphWeight.class), Collections.emptyList()), graphWeights);
        lists.put(Arrays.asList(Collections.singleton(GraphFull.class), Collections.emptyList()), graphFulls);
        lists.put(Arrays.asList(Collections.singleton(GraphEmpty.class), Collections.emptyList()), graphEmpties);
        lists.put(Arrays.asList(Collections.singleton(GraphNotEmpty.class), Collections.emptyList()), graphNotEmpties);
        lists.put(Arrays.asList(Collections.singleton(Tree.class), Collections.emptyList()), trees);
        lists.put(Arrays.asList(Collections.singleton(GraphDirectedGeneral.class), Collections.emptyList()), graphDirectedGenerals);
        lists.put(Arrays.asList(Collections.singleton(GraphWeightGeneral.class), Collections.emptyList()), graphWeightGenerals);
        lists.put(Arrays.asList(Arrays.asList(Tree.class, GraphDirectedGeneral.class), Collections.emptyList()), treeDirectedGenerals);
        lists.put(Arrays.asList(Collections.singleton(Tree.class), Collections.singleton(GraphDirectedGeneral.class)),
                treeNotDirectedGenerals);
        lists.put(Arrays.asList(Collections.singleton(Graph.class), Collections.singleton(GraphDirectedGeneral.class)),
                graphNotDirectedGenerals);

        graphs.forEach(graph -> {
            System.out.println(graph.getClass().getSimpleName());
            lists.forEach((classes, graphs) -> {
                if (classes.get(0).stream()
                        .filter(aClass -> aClass.isAssignableFrom(graph.getClass()))
                        .count() == classes.get(0).size()
                        && classes.get(1).stream()
                        .noneMatch(aClass -> aClass.isAssignableFrom(graph.getClass()))) {
                    addingGraphToList(graph, (List<Graph>) graphs);
                }
            });
        });
    }

    private static void addingGraphToList(Graph graph, List<Graph> list) {
        list.add(graph);
    }

    @After
    public void clearingGraphs() {
        graphs.forEach(Graph::clearAll);
    }

    @Test
    public void sout() {
        lists.forEach((classes, graphs) -> {
            System.out.println("good classes");
            classes.get(0).forEach(i -> System.out.println(i.getSimpleName()));
            System.out.println("bad classes");
            classes.get(1).forEach(i -> System.out.println(i.getSimpleName()));
            System.out.println("graphs");
            graphs.forEach(i -> System.out.println(i.getClass().getSimpleName()));
        });
    }

    @Test
    public void soutAllParametersOfGraphs() {
        System.out.println("ClassName:    1. isDirected    2. isWeight    3. vertexType    4. edgeType    5. vertexWeightType    6. edgeWeightType");
        for (Graph graph : graphs) {
            System.out.println(graph.getClass().getSimpleName() +
                    ": 1." + graph.isDirected() +
                    "  2." + graph.isWeight() +
                    "  3." + (((GraphAbstract) graph).getVertexType() != null ? ((GraphAbstract) graph).getVertexType().getSimpleName() : ((GraphAbstract) graph).getVertexType()) +
                    "  4." + (((GraphAbstract) graph).getEdgeType() != null ? ((GraphAbstract) graph).getEdgeType().getSimpleName() : ((GraphAbstract) graph).getEdgeType()) +
                    "  5." + ((GraphNotEmpty.class.isAssignableFrom(graph.getClass()) ? ((GraphNotEmpty) graph).getVertexWeightType().getSimpleName() : "null") +
                    "  6." + (graph.isWeight() ? ((GraphWeightGeneral) graph).getEdgeWeightType().getSimpleName() : "null")));
        }
    }

    public void addingVertices(int count) {
        graphEmpties.forEach(graph -> {
            for (int i = 0; i < count; i++) graph.addVertex();
        });
        graphNotEmpties.forEach(graph -> {
            for (int i = 0; i < count; i++)
                graph.addVertex(names[i % names.length] + i);
        });
    }

    @Test
    public void addingTwoVerticesTest() {
        graphEmpties.forEach(graph -> {
            graph.addVertex();
            graph.addVertex();
        });
        graphNotEmpties.forEach(graph -> {
            graph.addVertex("alfa");
            graph.addVertex("bravo");
        });
        graphs.forEach(graph -> System.out.println(graph.countOfVertices() + " = " + graph.getClass().getSimpleName() + ".countOfVertices()"));
        long count = graphs.stream().filter(graph -> graph.countOfVertices() == 2).count();
        Assert.assertEquals(graphs.size(), count);
    }

    @Test
    public void addingAncorrectVerticesTest() {
        List<Integer> integers = graphNotEmpties.stream().map(Graph::countOfVertices).collect(Collectors.toList());
        graphNotEmpties.forEach(graph -> graph.addVertex(1));
        Assert.assertArrayEquals(integers.toArray(), graphNotEmpties.stream().map(Graph::countOfVertices).collect(Collectors.toList()).toArray());
    }

    @Test
    public void addingEdgeTest() {
        int uniformCount = graphs.stream().findFirst().get().getUniformCount();
        addingVertices(10);
        graphSimples.forEach(graph -> {
            System.out.println(graph.getClass().getSimpleName());
            Vertex v = graph.getVertices().stream().findFirst().get();
            Vertex v2 = graph.getVertices().stream().filter(vertex -> !vertex.equals(v)).findFirst().get();
            Vertex[] v2v2 = {v2, v2};
            graph.addEdge(v, v);
            graph.addEdge(Arrays.asList(v, v2));
            System.out.println(graph);
        });
        graphDirecteds.forEach(graph -> {
            System.out.println(graph.getClass().getSimpleName());
            Vertex v = graph.getVertices().stream().findFirst().get();
            Vertex v2 = graph.getVertices().stream().filter(vertex -> !vertex.equals(v)).findFirst().get();
            graph.addEdge(Collections.singleton(v), Collections.singleton(v2));
            graph.addEdge(new Vertex[]{v}, new Vertex[]{v});
            graph.addEdge(v2, v2);
        });
        graphWeights.forEach(graph -> {
            System.out.println(graph.getClass().getSimpleName());
            Vertex v = graph.getVertices().stream().findFirst().get();
            Vertex v2 = graph.getVertices().stream().filter(vertex -> !vertex.equals(v)).findFirst().get();
            graph.addEdge(new Vertex[]{v, v2}, "ves");
            graph.addEdge(Arrays.asList(v, v), "pes");
            graph.addEdge("bes", new Vertex[]{v2, v2});
        });
        graphFulls.forEach(graph -> {
            System.out.println(graph.getClass().getSimpleName());
            Vertex v = graph.getVertices().stream().findFirst().get();
            Vertex v2 = graph.getVertices().stream().filter(vertex -> !vertex.equals(v)).findFirst().get();
            graph.addEdge(Collections.singleton(v), Collections.singleton(v2), "Bohemian Rhapsody");
            graph.addEdge(new Vertex[]{v}, new Vertex[]{v}, "Under pressure");
            graph.addEdge(v2, v2, "We will rock you");
        });
        graphs.forEach(graph -> System.out.println(graph.countOfEdges() + " = " + graph.getClass().getSimpleName() + ".countOfEdges()"));
        long count = graphs.stream().filter(graph -> graph.countOfEdges() == 3).count();
        Assert.assertEquals(graphs.size(), count);
    }

    @Test
    public void getVertexTest() {
        addingVertices(5);
        List<Vertex> vertices = new ArrayList<>();
        graphNotEmpties.forEach(graph -> {
            vertices.add(graph.getVertex("alfa0"));
            System.out.println(graph);
        });
        Assert.assertEquals(graphNotEmpties.size(), vertices.size());
    }

    @Test
    public void graphsToString() {
        graphs.forEach(graph -> System.out.println(graph.toString() + '\n'));
    }

    @Test
    public void getChildrenTest() {
        graphDirectedGenerals.forEach(graph -> {
            Vertex v = graph.getVertices().stream().findFirst().get();
            System.out.println(graph);
            System.out.println("v = " + v);
            System.out.println(graph.getChildren(Collections.singleton(v)));
        });
    }

//    @Test
//    public void separatingEdgeTest() {
//        addingVertices(10);
//        addingEdgeTest();
//        graphDirectedGenerals.forEach(System.out::println);
//        graphDirectedGenerals.stream().forEach(graph -> graph.getEdges().stream().filter(edge -> {
//            Collection<Vertex> begin = ((DirectedEdge)edge).getBegin();
//            Collection<Vertex> end = ((DirectedEdge)edge).getEnd();
//            return !(begin.containsAll(end) && end.containsAll(begin));
//        }).forEach(edge -> {
//            if (graphFulls.contains(graph)) {
//                ((GraphFull)graph).addEdge(((DirectedEdge) edge).getBegin(), )
//            }
//        }));
//    }

    public static class GeneralDirectedTest {

        @Test
        public void test() {
            Assert.assertTrue(true);
        }
    }

    public static class GeneralWeightTest {

        @Test
        public void test() {

        }

    }
}