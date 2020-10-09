package die.mass.Graphs.parts.edges;

import die.mass.Graphs.parts.vertices.Vertex;
import die.mass.IDGenerator;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

abstract class AbstractEdge implements Edge {

	protected int uniformCount;
	private Class<? extends Vertex> verticesType;
	private Class<?> verticesWeightType;
	private TreeSet<? extends Vertex> vertices;
	private Long id;

	protected AbstractEdge() {
		this.id = IDGenerator.getID();
	}

	@Override
	public <Q extends Vertex> Collection<Q> getVertices() {
		return (Collection<Q>) vertices;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Class<? extends Vertex> getVerticesType() {
		return verticesType;
	}

	@Override
	public Class<?> getVerticesWeightType() {
		return verticesWeightType;
	}

	@Override
	public Integer getUniformCount() {
		return uniformCount;
	}

	protected <T extends AbstractEdge> int compare(T o) {
		return this.id.compareTo(o.getId());
	}

	abstract class AbstractEdgeBuilder<B extends AbstractEdgeBuilder<B>> {

		public B setTypeOfVertex(Class<? extends Vertex> verticesType) {
			AbstractEdge.this.verticesType = verticesType;
			return self();
		}

		public B setTypeOfVertexWeight(Class<?> verticesWeightType) {
			AbstractEdge.this.verticesWeightType = verticesWeightType;
			return self();
		}

		public B setVertices(Collection<? extends Vertex> vertices) {
			AbstractEdge.this.vertices = new TreeSet<>(vertices);
			AbstractEdge.this.uniformCount = vertices.size();
			return self();
		}

		B self() {
			return (B) this;
		}

		public abstract <E extends AbstractEdge> E build();
	}
}
