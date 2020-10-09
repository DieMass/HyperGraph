package die.mass;

import die.mass.Graphs.Graph.GraphNotEmpty;
import die.mass.Graphs.GraphFull.GraphFull;
import die.mass.Graphs.GraphGenerator;
import die.mass.Graphs.parts.vertices.WeightVertex;
import die.mass.Tree.TreeSimple.TreeSimpleNotEmpty;

public class Main {

    public static void main(String[] args) {

    }

    public static void graph() {
        GraphGenerator gg = new GraphGenerator();
        GraphFull graphFull = gg.template().setDirected().setEdgeWeightType(String.class).setVertexWeightType(String.class).build();
        GraphNotEmpty graphNotEmpty = (GraphNotEmpty) graphFull;
        String[] interfaces = {"Graph", "EmptyGraph", "NotEmptyGraph", "GraphDirectedGeneral", "GraphWeightGeneral",
                "GraphSimple", "GraphDirected", "GraphWeight", "GraphFull"};
        String[] classes = {"AbstractGraph", "AbstractEmptyGraph", "AbstractNotEmptyGraph",
                "GraphSimpleEmpty", "GraphSimpleNotEmpty",
                "GraphDirectedEmpty", "GraphDirectedNotEmpty",
                "GraphWeightEmpty", "GraphWeightNotEmpty",
                "GraphFullEmpty", "GraphFullNotEmpty"};
        String[] dep = {"extends", "implements"};
        for (String vert : interfaces) graphNotEmpty.addVertex(vert);
        for (String vert : classes) graphNotEmpty.addVertex(vert);
        setDependency(graphFull, interfaces[1], interfaces[0], dep[0]);
        setDependency(graphFull, interfaces[2], interfaces[0], dep[0]);
        setDependency(graphFull, interfaces[3], interfaces[0], dep[0]);
        setDependency(graphFull, interfaces[4], interfaces[0], dep[0]);
        setDependency(graphFull, interfaces[5], interfaces[0], dep[0]);
        setDependency(graphFull, interfaces[6], interfaces[3], dep[0]);
        setDependency(graphFull, interfaces[7], interfaces[4], dep[0]);
        setDependency(graphFull, interfaces[8], interfaces[3], dep[0]);
        setDependency(graphFull, interfaces[8], interfaces[4], dep[0]);
        setDependency(graphFull, classes[0], interfaces[0], dep[1]);
        setDependency(graphFull, classes[1], classes[0], dep[0]);
        setDependency(graphFull, classes[2], classes[0], dep[0]);
        setDependency(graphFull, classes[3], classes[1], dep[0]);
        setDependency(graphFull, classes[5], classes[1], dep[0]);
        setDependency(graphFull, classes[7], classes[1], dep[0]);
        setDependency(graphFull, classes[9], classes[1], dep[0]);
        setDependency(graphFull, classes[4], classes[2], dep[0]);
        setDependency(graphFull, classes[6], classes[2], dep[0]);
        setDependency(graphFull, classes[8], classes[2], dep[0]);
        setDependency(graphFull, classes[10], classes[2], dep[0]);

//        setExtendsDependency(graph, verts[0], verts[8]);
        System.out.println(graphFull.isCycled());
        System.out.println(graphFull.getShortestPath(graphNotEmpty.getVertex("GraphDirected"), graphNotEmpty.getVertex("Graph")));
    }

    private static void setDependency(GraphFull graph, String first, String second, String dependence) {
        graph.addEdge(graph.getVertices().stream().filter(i -> ((WeightVertex)i).getValue().equals(first)).findFirst().get(),
                graph.getVertices().stream().filter(i -> ((WeightVertex)i).getValue().equals(second)).findFirst().get(), dependence);
    }

    public static void tree() {
        GraphGenerator gg = new GraphGenerator();
        TreeSimpleNotEmpty tree = gg.template().setTree().setVertexWeightType(String.class).build();
    }
}
