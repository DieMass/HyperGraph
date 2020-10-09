package die.mass.Graphs.parts.edges;

public class SimpleEdge extends AbstractEdge implements Comparable<SimpleEdge> {

    private SimpleEdge() {}

    @Override
    public int compareTo(SimpleEdge o) {
        return compare(o);
    }

    static SimpleEdgeBuilder builder() {
        return new SimpleEdge().new SimpleEdgeBuilder();
    }

    @Override
    public String toString() {
        return "{e" + getId() + ", vertices=" + getVertices().toString() +
                "}";
    }

    class SimpleEdgeBuilder extends AbstractEdgeBuilder<SimpleEdgeBuilder> {

        @Override
        public SimpleEdge build() {
            return SimpleEdge.this;
        }
    }
}
