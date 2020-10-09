package die.mass.Tree.Tree;

import die.mass.Graphs.Graph.GraphAbstractNotEmpty;
import die.mass.Graphs.parts.vertices.ValueVertex;

public abstract class TreeAbstractNotEmpty extends GraphAbstractNotEmpty implements Tree {

    private ValueVertex root;

    @Override
    public ValueVertex getRoot() {
        return root;
    }
}
