package die.mass.Graphs.parts.edges;

import die.mass.Graphs.parts.vertices.Vertex;

import java.util.Collection;
import java.util.TreeSet;

public class Arrow extends AbstractEdge implements DirectedEdge, Comparable<Arrow> {

    protected TreeSet<Vertex> begin;
    protected TreeSet<Vertex> end;

    private Arrow() {}

    @Override
    public int compareTo(Arrow o) {
        return compare(o);
    }

    //TODO: реализовать клонирование, не позволяющее изменить состав ребра и каждое из вершин в ребре
    @Override
    public Collection<Vertex> getBegin() {
        return new TreeSet<>(begin);
    }

    @Override
    public Collection<Vertex> getEnd() {
        return new TreeSet<>(end);
    }

    @Override
    public  <Q extends Vertex> Collection<Q> getVertices() {
        Collection<Vertex> all = getEnd();
        all.addAll(getBegin());
        return (Collection<Q>) all;
    }

    @Override
    public String toString() {
        return "{e" + getId() + ", begin=" + begin.toString() +
                ", end=" + end.toString() + "}";
    }

    static ArrowBuilder builder() {
        return new Arrow().new ArrowBuilder();
    }

    class ArrowBuilder extends AbstractEdgeBuilder<ArrowBuilder> {

        @Override
        public ArrowBuilder setVertices(Collection<? extends Vertex> vertices) {
            return setBegin(vertices);
        }

        public ArrowBuilder setBegin(Collection<? extends Vertex> begin) {
            Arrow.this.begin = new TreeSet<>(begin);
            return self();
        }

        public ArrowBuilder setEnd(Collection<? extends Vertex> end) {
            Arrow.this.end = new TreeSet<>(end);
            return self();
        }

        @Override
        public Arrow build() {
            Arrow.this.uniformCount = Arrow.this.begin.size() + Arrow.this.end.size();
            return Arrow.this;
        }
    }
}
