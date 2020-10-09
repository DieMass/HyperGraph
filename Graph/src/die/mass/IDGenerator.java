package die.mass;

public class IDGenerator {

    private static Long id = 0L;

    public static Long getID() {
        return  ++id;
    }

}
