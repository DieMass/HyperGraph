package die.mass.Tree.Tree;

import die.mass.Graphs.Graph.GraphAbstractEmpty;
import die.mass.Graphs.parts.vertices.EmptyVertex;

abstract public class TreeAbstractEmpty extends GraphAbstractEmpty implements Tree {

    private EmptyVertex root;

    @Override
    public EmptyVertex getRoot() {
        return root;
    }
}
