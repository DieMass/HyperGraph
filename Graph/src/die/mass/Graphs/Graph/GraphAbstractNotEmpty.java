package die.mass.Graphs.Graph;

import die.mass.Graphs.parts.vertices.ValueVertex;
import die.mass.Graphs.parts.vertices.Vertex;
import die.mass.Graphs.parts.vertices.WeightVertex;

import java.util.Collection;
import java.util.HashSet;

public abstract class GraphAbstractNotEmpty extends GraphAbstract implements GraphNotEmpty {

    protected Class<?> vertexWeightType;
    protected HashSet<ValueVertex> vertices;

    protected GraphAbstractNotEmpty() {
        this.vertices = new HashSet<>();
    }

    @Override
    protected Collection<ValueVertex> getPrivateVertices() {
        return vertices;
    }

    @Override
    public Collection<ValueVertex> getVertices() {
        return getPrivateVertices();
    }

    @Override
    public <T> boolean addVertex(T vertex) {
        if (typesOfVerticesIsEqual(vertex)) {
            return addingVertex(vertex);
        }
        return false;
    }

    protected <T> boolean typesOfVerticesIsEqual(T vertex) {
        if (vertex.getClass().equals(vertexWeightType)) return true;
        System.err.println("Разный тип весов вершин (нужный "
                + vertexWeightType.getSimpleName() + " != " + vertex.getClass().getSimpleName() + ")");
        return false;
    }

    @Override
    protected <V extends Vertex> boolean checkEqualsingOfVertices(Collection<V> vertices) {
        if (super.checkEqualsingOfVertices(vertices)) {
            for (Vertex vertex : vertices) {
                if (!((WeightVertex) vertex).getValue().getClass().equals(vertexWeightType)) {
                    System.out.println("Разный тип весов вершин (" +
                            ((WeightVertex) vertex).getValue().getClass().getSimpleName() + " != " + vertexWeightType.getSimpleName() + ")");
                    return false;
                }
            }
        }
        return true;
    }

    protected <Q> boolean addingVertex(Q vertex) {
        return getPrivateVertices().add(vertexGenerator.getValueVertex(vertex));
    }

    @Override
    public <T> ValueVertex getVertex(T value) {
        return getPrivateVertices().stream().filter(vertex -> vertex.getValue().equals(value)).findFirst().get();
    }

    public Class<?> getVertexWeightType() {
        return vertexWeightType;
    }

    protected abstract class GenericGraphBuilder<B extends GenericGraphBuilder<B>> extends GraphAbstract.GenericGraphBuilder<B> {

        public B setVertexWeightType(Class<?> vertexWeightType) {
            GraphAbstractNotEmpty.this.vertexWeightType = vertexWeightType;
            return self();
        }
    }
}
