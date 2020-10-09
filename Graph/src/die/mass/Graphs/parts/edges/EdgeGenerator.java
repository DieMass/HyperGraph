package die.mass.Graphs.parts.edges;

import die.mass.Graphs.parts.vertices.Vertex;
import die.mass.Graphs.parts.vertices.WeightVertex;

import java.util.Arrays;
import java.util.Collection;

public class EdgeGenerator {


    private boolean sizeMoreThanZero(Collection<? extends Vertex> vertices) {
        return !vertices.isEmpty() || serr(0);
    }

    private boolean allVerticesHaveOneClass(Collection<? extends Vertex> vertices, Class<? extends Vertex> vertexType) {
        if (vertexType.isInterface()) return serr(1);
        for (Vertex vertex : vertices) {
            if (!vertex.getClass().equals(vertexType)) return serr(2, vertexType, vertex.getClass());
        }
        return true;
    }

    private boolean allVerticesHaveOneWeight(Collection<? extends Vertex> vertices, Class<?> weightClass) {
        for (WeightVertex<?> vertex : (Collection<? extends WeightVertex<?>>) vertices) {
            if (!vertex.getValue().getClass().equals(weightClass)) {
                return serr(4, weightClass, vertex.getValue().getClass());
            }
        }
        return true;
    }

    public SimpleEdge getSimpleEdge(Vertex... vertices) {
        return getSimpleEdge(Arrays.asList(vertices));
    }

    public SimpleEdge getSimpleEdge(Collection<? extends Vertex> vertices) {
        if (!sizeMoreThanZero(vertices)) return null;
        Class<? extends Vertex> typeOfVertex = vertices.iterator().next().getClass();
        if (!allVerticesHaveOneClass(vertices, typeOfVertex)) return null;
        if (WeightVertex.class.isAssignableFrom(typeOfVertex)) {
            Class<?> typeOfWeight = ((WeightVertex<?>) vertices.iterator().next()).getValue().getClass();
            if (!allVerticesHaveOneWeight(vertices, typeOfWeight)) return null;
            return SimpleEdge.builder().setTypeOfVertex(typeOfVertex).setTypeOfVertexWeight(typeOfWeight).setVertices(vertices).build();

        }
        return SimpleEdge.builder().setTypeOfVertex(typeOfVertex).setVertices(vertices).build();
    }

    public Arrow getArrow(Vertex[] begin, Vertex[] end) {
        return getArrow(Arrays.asList(begin), Arrays.asList(end));
    }

    public Arrow getArrow(Collection<? extends Vertex> begin, Collection<? extends Vertex> end) {
        if (!sizeMoreThanZero(begin) || !sizeMoreThanZero(end)) return null;
        Class<? extends Vertex> typeOfVertex = begin.iterator().next().getClass();
        if (!allVerticesHaveOneClass(begin, typeOfVertex) || !allVerticesHaveOneClass(end, typeOfVertex)) return null;
        if (WeightVertex.class.isAssignableFrom(typeOfVertex)) {
            Class<?> weightClass = ((WeightVertex<?>) begin.iterator().next()).getValue().getClass();
            if (!allVerticesHaveOneWeight(begin, weightClass) || !allVerticesHaveOneWeight(end, weightClass))
                return null;
            return Arrow.builder().setTypeOfVertexWeight(weightClass).setTypeOfVertex(typeOfVertex).setBegin(begin).setEnd(end).build();
        }
        return Arrow.builder().setTypeOfVertex(typeOfVertex).setBegin(begin).setEnd(end).build();
    }

    public <E> Arc getArc(E weight, Vertex... vertices) {
        return getArc(Arrays.asList(vertices), weight);
    }

    public <E> Arc getArc(Collection<? extends Vertex> vertices, E weight) {
        if (!sizeMoreThanZero(vertices)) return null;
        Class<? extends Vertex> typeOfVertex = vertices.iterator().next().getClass();
        if (!allVerticesHaveOneClass(vertices, typeOfVertex)) return null;
        if (WeightVertex.class.isAssignableFrom(typeOfVertex)) {
            Class<?> typeOfVertexWeight = ((WeightVertex<?>) vertices.iterator().next()).getValue().getClass();
            if (!allVerticesHaveOneWeight(vertices, typeOfVertexWeight)) return null;
            return new Arc<E>().builder().setTypeOfVertex(typeOfVertex).setTypeOfVertexWeight(typeOfVertexWeight).setVertices(vertices).setWeight(weight).build();
        }
        return new Arc<E>().builder().setTypeOfVertex(typeOfVertex).setVertices(vertices).setWeight(weight).build();
    }

    public <E> ArcDirected getArcDirected(Vertex[] begin, Vertex[] end, E weight) {
        return getArcDirected(Arrays.asList(begin), Arrays.asList(end), weight);
    }

    public <E> ArcDirected getArcDirected(Collection<? extends Vertex> begin, Collection<? extends Vertex> end, E weight) {
        if (!sizeMoreThanZero(begin) || !sizeMoreThanZero(end)) return null;
        Class<? extends Vertex> typeOfVertex = begin.iterator().next().getClass();
        if (!allVerticesHaveOneClass(begin, typeOfVertex) || !allVerticesHaveOneClass(end, typeOfVertex)) return null;
        if (WeightVertex.class.isAssignableFrom(typeOfVertex)) {
            Class<?> typeOfVertexWeight = ((WeightVertex<?>) begin.iterator().next()).getValue().getClass();
            if (!allVerticesHaveOneWeight(begin, typeOfVertexWeight) || !allVerticesHaveOneWeight(end, typeOfVertexWeight))
                return null;
            return new ArcDirected<E>().builder().setTypeOfVertex(typeOfVertex).setBegin(begin).setEnd(end).setTypeOfVertexWeight(typeOfVertexWeight).setWeight(weight).build();
        }
        return new ArcDirected<E>().builder().setTypeOfVertex(typeOfVertex).setBegin(begin).setEnd(end).setWeight(weight).build();
    }

    public <E extends Edge> E getEdge(Class<E> edgeType, Object... args) {
        if (edgeType.equals(SimpleEdge.class)) {
            return (E) getSimpleEdge((Collection<? extends Vertex>) args[0]);
        }
        if (edgeType.equals(Arrow.class)) {
            return (E) getArrow((Collection<? extends Vertex>) args[0], (Collection<? extends Vertex>) args[1]);
        }
        if (edgeType.equals(Arc.class)) {
            return (E) getArc((Collection<? extends Vertex>) args[0], args[1]);
        }
        if (edgeType.equals(ArcDirected.class)) {
            return (E) getArcDirected((Collection<? extends Vertex>) args[0], (Collection<? extends Vertex>) args[1], args[2]);
        }
        throw new IllegalArgumentException();
    }

    private boolean serr(int i, Object... args) {
        switch (i) {
            case 0: {
                System.err.println("Дан пустой массив");
                break;
            }
            case 1: {
                System.err.println("Дан массив интерфейсов");
                break;
            }
            case 2: {
                System.err.println("Классы вершин не совпадают: (" + ((Class) args[0]).getSimpleName() + " expected, received " + ((Class) args[1]).getSimpleName() + ")");
                break;
            }
            case 4: {
                System.err.println("Классы весов вершин не совпадают: (" + ((Class) args[0]).getSimpleName() + " expected, received " + ((Class) args[1]).getSimpleName() + ")");
            }
        }
        return false;
    }
}