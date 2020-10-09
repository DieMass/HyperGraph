package die.mass;

import die.mass.Graphs.Graph.Graph;
import die.mass.Graphs.Graph.GraphEmpty;
import die.mass.Graphs.Graph.GraphNotEmpty;
import die.mass.Graphs.GraphDirected.GraphDirected;
import die.mass.Graphs.GraphDirected.GraphDirectedGeneral;
import die.mass.Graphs.GraphFull.GraphFull;
import die.mass.Graphs.GraphGenerator;
import die.mass.Graphs.parts.vertices.Vertex;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

public class GraphsGeneralDirectedTest {

    static GraphGenerator gg = new GraphGenerator();
    static List<GraphDirectedGeneral> graphs;
    static List<GraphEmpty> graphEmpties = new ArrayList<>();
    static List<GraphNotEmpty> graphNotEmpties = new ArrayList<>();
    static List<GraphDirected> graphDirecteds = new ArrayList<>();
    static List<GraphFull> graphFulls = new ArrayList<>();
    static String[] names = {"alfa", "bravo", "charlie", "delta", "echo", "foxtrot", "golf", "hotel", "india", "kilo", "lima", "papa", "whiskey", "x-ray", "yankee", "zulu"};


    @BeforeClass
    public static void fillingGraphLists() {
        int uniformCount = 2;
        Class<?> vertexWeightType = String.class;
        Class<?> edgeWeightType = Integer.class;
        graphs = Arrays.asList(
                gg.getDirectedEmptyGraph(uniformCount),
                gg.getDirectedNotEmptyGraph(uniformCount, vertexWeightType),
                gg.getFullEmptyGraph(uniformCount, edgeWeightType),
                gg.getFullNotEmptyGraph(uniformCount, vertexWeightType, edgeWeightType),
                gg.getDirectedNotEmptyTree(uniformCount, vertexWeightType),
                gg.getFullNotEmptyTree(uniformCount, vertexWeightType, edgeWeightType));
        graphs.forEach(graph -> {
            if (GraphEmpty.class.isAssignableFrom(graph.getClass())) graphEmpties.add((GraphEmpty) graph);
            if (GraphNotEmpty.class.isAssignableFrom(graph.getClass())) graphNotEmpties.add((GraphNotEmpty) graph);
            if (GraphDirected.class.isAssignableFrom(graph.getClass())) graphDirecteds.add((GraphDirected) graph);
            if (GraphFull.class.isAssignableFrom(graph.getClass())) graphFulls.add((GraphFull) graph);
        });
    }

    @Before
    public void fillingGraphs() {       //создаёт остов
        int verticesCount = 400;
        for (int i = 0; i < verticesCount; i++) {
            graphEmpties.forEach(GraphEmpty::addVertex);
            for (GraphNotEmpty graph : graphNotEmpties) graph.addVertex(names[i % names.length] + i);
        }
        graphDirecteds.forEach(graph -> {
            Vertex vertex = graph.getVertices().stream().findFirst().get();
            graph.getVertices().stream().filter(v -> !v.equals(vertex)).forEach(v -> {
                graph.addEdge(vertex, v);
                graph.addEdge(v, vertex);
            });
        });
        graphDirecteds.forEach(graph -> System.out.println(graph.getClass().getSimpleName() + " " + graph.getEdges().size()));
        graphFulls.forEach(graph -> {
            Vertex vertex = graph.getVertices().stream().findFirst().get();
            graph.getVertices().stream().filter(v -> !v.equals(vertex)).forEach(v -> graph.addEdge(vertex, v, 1));
        });
    }

    @After
    public void clearingGraphs() {
        graphs.forEach(Graph::clearAll);
    }

    @Test
    public void name() {
        graphs.forEach(graph -> {
            System.out.println(graph.getClass().getSimpleName());
            System.out.println(graph.getChildren(Collections.singleton(graph.getVertices().stream().findFirst().get())).size());//не дети
        });
    }
}
