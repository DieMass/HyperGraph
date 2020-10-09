package die.mass.Graphs.parts.vertices;

public class VertexGenerator {

    public EmptyVertex getEmptyVertex() {
        return new EmptyVertex();
    }

    public <V> ValueVertex<V> getValueVertex(V value) {
        if(value == null) return serr(0);
        return new ValueVertex<>(value);
    }

    private <T extends Vertex> T serr(int i) {
        switch (i) {
            case 0: {
                System.err.println("Нулевой вес");
                break;
            }
            default: {
                System.err.println("geg");
            }
        }
        return null;
    }

}
