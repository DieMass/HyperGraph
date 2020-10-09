package die.mass.Graphs.parts.edges;

public class Arc<E> extends AbstractEdge implements WeightEdge<E>, Comparable<Arc<E>> {

    private E weight;

    Arc() {}

    @Override
    public E getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Arc<E> o) {
        return compare(o);
    }

    @Override
    public String toString() {
        return "{e" + getId() + ", vertices=" + getVertices().toString() +
                ", weight=" + weight.toString() + "}";
    }

    ArcBuilder builder() {
        return new ArcBuilder();
    }

    class ArcBuilder extends AbstractEdgeBuilder<ArcBuilder> {

        public ArcBuilder setWeight(E weight) {
            Arc.this.weight = weight;
            return self();
        }

        @Override
        public Arc build() {
            return Arc.this;
        }
    }
}
