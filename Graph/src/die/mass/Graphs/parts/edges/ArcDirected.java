package die.mass.Graphs.parts.edges;

import die.mass.Graphs.parts.vertices.Vertex;

import java.util.Collection;
import java.util.TreeSet;

public class ArcDirected<E> extends AbstractEdge implements WeightEdge<E>, DirectedEdge, Comparable<ArcDirected<E>> {

    protected E weight;
    protected TreeSet<Vertex> begin;
    protected TreeSet<Vertex> end;

    protected ArcDirected() {}

    @Override
    public E getWeight() {
        return weight;
    }

    @Override
    public int compareTo(ArcDirected<E> o) {
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
    public <Q extends Vertex> Collection<Q> getVertices() {
        Collection<Vertex> all = getEnd();
        all.addAll(getBegin());
        return (Collection<Q>) all;
    }

    @Override
    public String toString() {
        return "{e" + getId() + ", begin=" + begin.toString() +
                ", end=" + end.toString() + ", weight=" + weight.toString() + "}";
    }

    ArcDirectedBuilder builder() {
        return new ArcDirected().new ArcDirectedBuilder();
    }

    class ArcDirectedBuilder extends AbstractEdgeBuilder<ArcDirectedBuilder> {

        @Override
        public ArcDirectedBuilder setVertices(Collection<? extends Vertex> vertices) {
            return setBegin(vertices);
        }

        public ArcDirectedBuilder setWeight(E weight) {
            ArcDirected.this.weight = weight;
            return self();
        }

        public ArcDirectedBuilder setBegin(Collection<? extends Vertex> begin) {
            ArcDirected.this.begin = new TreeSet<>(begin);
            return self();
        }

        public ArcDirectedBuilder setEnd(Collection<? extends Vertex> end) {
            ArcDirected.this.end = new TreeSet<>(end);
            return self();
        }

        @Override
        public ArcDirected build() {
            ArcDirected.this.uniformCount = ArcDirected.this.begin.size() + ArcDirected.this.end.size();
            return ArcDirected.this;
        }
    }
}