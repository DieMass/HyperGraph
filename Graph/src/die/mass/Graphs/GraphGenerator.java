package die.mass.Graphs;

import die.mass.Graphs.Graph.Graph;
import die.mass.Graphs.GraphDirected.GraphDirectedEmpty;
import die.mass.Graphs.GraphFull.GraphFullEmpty;
import die.mass.Graphs.GraphFull.GraphFullNotEmpty;
import die.mass.Graphs.GraphSimple.GraphSimpleEmpty;
import die.mass.Graphs.GraphSimple.GraphSimpleNotEmpty;
import die.mass.Graphs.GraphDirected.GraphDirectedNotEmpty;
import die.mass.Graphs.GraphWeight.GraphWeightEmpty;
import die.mass.Graphs.GraphWeight.GraphWeightNotEmpty;
import die.mass.Tree.TreeDirected.TreeDirectedNotEmpty;
import die.mass.Tree.TreeFull.TreeFullNotEmpty;
import die.mass.Tree.TreeSimple.TreeSimpleNotEmpty;
import die.mass.Tree.TreeWeight.TreeWeightNotEmpty;

public class GraphGenerator {

    public GraphBuilder template() {
        return new GraphBuilder();
    }

    public GraphSimpleEmpty getSimpleEmptyGraph(int uniformCount) {
        return GraphSimpleEmpty.builder().setUniformCount(uniformCount).build();
    }

    public GraphSimpleNotEmpty getSimpleNotEmptyGraph(int uniformCount, Class<?> vertexWeightType) {
        return GraphSimpleNotEmpty.builder().setUniformCount(uniformCount).setVertexWeightType(vertexWeightType).build();
    }

    public GraphDirectedEmpty getDirectedEmptyGraph(int uniformCount) {
        return GraphDirectedEmpty.builder().setUniformCount(uniformCount).build();
    }

    public GraphDirectedNotEmpty getDirectedNotEmptyGraph(int uniformCount, Class<?> vertexWeightType) {
        return GraphDirectedNotEmpty.builder().setUniformCount(uniformCount).setVertexWeightType(vertexWeightType).build();
    }

    public GraphWeightEmpty getWeightEmptyGraph(int uniformCount, Class<?> edgeWeightType) {
        return GraphWeightEmpty.builder().setUniformCount(uniformCount).setEdgeWeightType(edgeWeightType).build();
    }

    public GraphWeightNotEmpty getWeightNotEmptyGraph(int uniformCount, Class<?> vertexWeightType, Class<?> edgeWeightType) {
        return GraphWeightNotEmpty.builder().setUniformCount(uniformCount).setEdgeWeightType(edgeWeightType).setVertexWeightType(vertexWeightType).build();
    }

    public GraphFullEmpty getFullEmptyGraph(int uniformCount, Class<?> edgeWeightType) {
        return GraphFullEmpty.builder().setUniformCount(uniformCount).setEdgeWeightType(edgeWeightType).build();
    }

    public GraphFullNotEmpty getFullNotEmptyGraph(int uniformCount, Class<?> vertexWeightType, Class<?> edgeWeightType) {
        return GraphFullNotEmpty.builder().setUniformCount(uniformCount).setEdgeWeightType(edgeWeightType).setVertexWeightType(vertexWeightType).build();
    }

//    public static TreeSimpleEmpty getSimpleEmptyTree(int uniformCount) {
//        return TreeSimpleEmpty.builder().setUniformCount(uniformCount).build();
//    }

    public TreeSimpleNotEmpty getSimpleNotEmptyTree(int uniformCount, Class<?> vertexWeightType) {
        return TreeSimpleNotEmpty.builder().setUniformCount(uniformCount).setVertexWeightType(vertexWeightType).build();
    }

    public TreeDirectedNotEmpty getDirectedNotEmptyTree(int uniformCount, Class<?> vertexWeightType) {
        return TreeDirectedNotEmpty.builder().setUniformCount(uniformCount).setVertexWeightType(vertexWeightType).build();
    }

    public TreeWeightNotEmpty getWeightNotEmptyTree(int uniformCount, Class<?> vertexWeightType, Class<?> edgeWeightType) {
        return TreeWeightNotEmpty.builder().setUniformCount(uniformCount).setVertexWeightType(vertexWeightType).setEdgeWeightType(edgeWeightType).build();
    }

    public TreeFullNotEmpty getFullNotEmptyTree(int uniformCount, Class<?> vertexWeightType, Class<?> edgeWeightType) {
        return TreeFullNotEmpty.builder().setUniformCount(uniformCount).setVertexWeightType(vertexWeightType).setEdgeWeightType(edgeWeightType).build();
    }


    public class GraphBuilder {

        boolean isDirected;
        boolean isTree;
        Class<?> vertexWeightType;
        Class<?> edgeWeightType;
        int uniformCount = 2;

        public GraphBuilder setDirected() {
            isDirected = true;
            return this;
        }

        public GraphBuilder setVertexWeightType(Class<?> vertexWeightType) {
            this.vertexWeightType = vertexWeightType;
            return this;
        }

        public GraphBuilder setEdgeWeightType(Class<?> edgeWeightType) {
            this.edgeWeightType = edgeWeightType;
            return this;
        }

        public GraphBuilder setTree() {
            isTree = true;
            return this;
        }

        public GraphBuilder setUniformCount(int uniformCount) {
            if (uniformCount > 1) this.uniformCount = uniformCount;
            return this;
        }

        public <G extends Graph> G build() {
            if (!isTree && !isDirected && vertexWeightType == null && edgeWeightType == null)
                return (G) GraphGenerator.this.getSimpleEmptyGraph(uniformCount);
            if (!isTree && !isDirected && vertexWeightType != null && edgeWeightType == null)
                return (G) GraphGenerator.this.getSimpleNotEmptyGraph(uniformCount, vertexWeightType);
            if (!isTree && isDirected && vertexWeightType == null && edgeWeightType == null)
                return (G) GraphGenerator.this.getDirectedEmptyGraph(uniformCount);
            if (!isTree && isDirected && vertexWeightType != null && edgeWeightType == null)
                return (G) GraphGenerator.this.getDirectedNotEmptyGraph(uniformCount, vertexWeightType);
            if (!isTree && !isDirected && vertexWeightType == null && edgeWeightType != null)
                return (G) GraphGenerator.this.getWeightEmptyGraph(uniformCount, edgeWeightType);
            if (!isTree && !isDirected && vertexWeightType != null && edgeWeightType != null)
                return (G) GraphGenerator.this.getWeightNotEmptyGraph(uniformCount, vertexWeightType, edgeWeightType);
            if (!isTree && isDirected && vertexWeightType == null && edgeWeightType != null)
                return (G) GraphGenerator.this.getFullEmptyGraph(uniformCount, edgeWeightType);
            if (!isTree && isDirected && vertexWeightType != null && edgeWeightType != null)
                return (G) GraphGenerator.this.getFullNotEmptyGraph(uniformCount, vertexWeightType, edgeWeightType);
            if (isTree && !isDirected && vertexWeightType == null && edgeWeightType == null)
                return null;
            if (isTree && !isDirected && vertexWeightType != null && edgeWeightType == null)
                return (G) GraphGenerator.this.getSimpleNotEmptyTree(uniformCount, vertexWeightType);
            if (isTree && isDirected && vertexWeightType == null && edgeWeightType == null)
                return null;
            if (isTree && isDirected && vertexWeightType != null && edgeWeightType == null)
                return (G) GraphGenerator.this.getDirectedNotEmptyTree(uniformCount, vertexWeightType);
            if (isTree && !isDirected && vertexWeightType == null && edgeWeightType != null)
                return null;
            if (isTree && !isDirected && vertexWeightType != null && edgeWeightType != null)
                return (G) GraphGenerator.this.getWeightNotEmptyTree(uniformCount, vertexWeightType, edgeWeightType);
            if (isTree && isDirected && vertexWeightType == null && edgeWeightType != null)
                return null;
            if (isTree && isDirected && vertexWeightType != null && edgeWeightType != null)
                return (G) GraphGenerator.this.getFullNotEmptyTree(uniformCount, vertexWeightType, edgeWeightType);
            return null;
        }

    }

}
