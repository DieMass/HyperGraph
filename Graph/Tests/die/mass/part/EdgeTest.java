//package die.mass.part;
//
//import die.mass.Graphs.parts.edges.Edge;
//import die.mass.Graphs.parts.edges.EdgeGenerator;
//import die.mass.Graphs.parts.vertices.Vertex;
//import die.mass.Graphs.parts.vertices.VertexGenerator;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.lang.reflect.InvocationTargetException;
//import java.util.ArrayList;
//import java.util.Random;
//
//public class EdgeTest {
//
//    Random random = new Random();
//    ArrayList<Vertex> correctEmptyVertices;
//    ArrayList<Vertex> correctLongVerticesBegin;
//    ArrayList<Vertex> correctLongVerticesEnd;
//    ArrayList<Vertex> correctIntegerVertices;
//
//    @Before
//    public void fillingLists() throws InvocationTargetException, IllegalAccessException {
//        correctEmptyVertices = new ArrayList<>();
//        correctIntegerVertices = new ArrayList<>();
//        correctLongVerticesBegin = new ArrayList<>();
//        correctLongVerticesEnd = new ArrayList<>();
//        fillingLists(random.nextInt(14)+ 5, correctEmptyVertices, true);
//        fillingLists(random.nextInt(14)+ 5, correctLongVerticesBegin, false, random.nextLong());
//        fillingLists(random.nextInt(14)+ 5, correctLongVerticesEnd, false, random.nextLong());
//        fillingLists(random.nextInt(14)+ 5, correctIntegerVertices, false, random.nextInt(1000));
//    }
//
//    private void fillingLists(int i1, ArrayList<Vertex> vertices, boolean isEmpty, Object... args) throws InvocationTargetException, IllegalAccessException {
//        for (int i = 0; i < i1; i++) {
//            vertices.add(get(isEmpty, args));
//        }
//    }
//
//    private Vertex get(boolean isEmpty, Object ... args) {
//        return isEmpty ? VertexGenerator.getEmptyVertex() : VertexGenerator.getValueVertex(args[0]);
//    }
//
//    @Test
//    public void test() {
//        correctEmptyVertices.forEach(System.out::print);
//        correctIntegerVertices.forEach(System.out::print);
//        correctLongVerticesBegin.forEach(System.out::print);
//    }
//
//    @Test
//    public void creatingCorrectEdges() {
//        System.out.println(EdgeGenerator.getSimpleEdge(correctEmptyVertices));
//        System.out.println(EdgeGenerator.getSimpleEdge(correctIntegerVertices));
//        System.out.println(EdgeGenerator.getSimpleEdge(correctLongVerticesBegin));
//        System.out.println(EdgeGenerator.getArrow(correctLongVerticesBegin, correctLongVerticesEnd));
//        System.out.println(EdgeGenerator.getArrow(correctLongVerticesEnd, correctLongVerticesBegin));
//        System.out.println(EdgeGenerator.getArc(correctEmptyVertices,"name"));
//        System.out.println(EdgeGenerator.getArc(correctIntegerVertices,"name"));
//        System.out.println(EdgeGenerator.getArc(correctLongVerticesBegin,"name"));
//        System.out.println(EdgeGenerator.getArcDirected(correctLongVerticesBegin, correctLongVerticesEnd, "name1"));
//    }
//
//    @Test
//    public void uncorrectCollections() {
//        Assert.assertNull(EdgeGenerator.getArrow(correctLongVerticesBegin, correctIntegerVertices));
//    }
//
//
//    public void sout(Edge edge) {
//        System.out.println("id = " + edge.getId());
//        System.out.println("size = " + edge.getUniformCount());
//        System.out.println(edge.getVerticesType().getSimpleName());
//        System.out.println(edge.getVerticesWeightType());
//    }
//
//    @Test
//    public void equalTest() {
////        Assert.assertTrue(!vertex.equals(vertex1));
//    }
//}
