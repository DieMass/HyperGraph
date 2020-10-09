package die.mass.Graphs.parts.vertices;

public class ValueVertex<V> extends AbstractVertex implements WeightVertex<V>, Comparable<ValueVertex<V>> {

    private V value;

    private ValueVertex() {
        super();
        this.value = (V) new Object();
    }

    public ValueVertex(V value) {
        super();
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    @Override
    public int compareTo(ValueVertex<V> valueVertex) {
        return compare(valueVertex);
    }

    @Override
    public String toString() {
        return "{v" + this.getId() + ", value=" + value + '}';
    }
}
