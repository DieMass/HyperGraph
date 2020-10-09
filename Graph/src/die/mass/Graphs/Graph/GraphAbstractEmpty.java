package die.mass.Graphs.Graph;

import die.mass.Graphs.parts.vertices.EmptyVertex;

import java.util.Collection;
import java.util.HashSet;

public abstract class GraphAbstractEmpty extends GraphAbstract implements GraphEmpty {

    protected HashSet<EmptyVertex> vertices;

    protected GraphAbstractEmpty() {
        this.vertices = new HashSet<>();
    }

    @Override
    protected Collection<EmptyVertex> getPrivateVertices() {
        return vertices;
    }

    //TODO: use clone
    @Override
    public Collection<EmptyVertex> getVertices() {
        return getPrivateVertices();
    }

    @Override
    public boolean addVertex() {
        return getPrivateVertices().add(vertexGenerator.getEmptyVertex());
    }
}
