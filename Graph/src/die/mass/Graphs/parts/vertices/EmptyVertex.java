package die.mass.Graphs.parts.vertices;


public class EmptyVertex  extends AbstractVertex implements Comparable<EmptyVertex> {

    @Override
    public int compareTo(EmptyVertex emptyVertex) {
        return compare(emptyVertex);
    }

}
