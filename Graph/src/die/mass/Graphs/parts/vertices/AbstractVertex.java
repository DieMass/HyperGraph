package die.mass.Graphs.parts.vertices;

import die.mass.IDGenerator;

public class AbstractVertex implements Vertex {

    private Long id;

    public AbstractVertex() {
        this.id = IDGenerator.getID();
    }

    @Override
    public Long getId() {
        return id;
    }

    protected <T extends AbstractVertex> int compare(T o) {
        return this.id.compareTo(o.getId());
    }

    @Override
    public String toString() {
        return "{v" + id + '}';
    }
}
