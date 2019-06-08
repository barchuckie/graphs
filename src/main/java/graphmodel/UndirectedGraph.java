package graphmodel;

public class UndirectedGraph extends Graph {

    public UndirectedGraph(int verticesCount) {
        super(verticesCount);
    }

    @Override
    public void addEdge(int vertex1, int vertex2) {
        if (vertex1 < verticesCount && vertex2 < verticesCount) {
            Edge edge1 = new Edge(vertex1, vertex2);
            adjList[vertex1].add(edge1);
            Edge edge2 = new Edge(vertex2, vertex1);
            adjList[vertex2].add(edge2);
        }
    }

    @Override
    public void addEdgeWithWeight(int vertex1, int vertex2, int weight) {
        if (vertex1 < verticesCount && vertex2 < verticesCount) {
            Edge edge1 = new Edge(vertex1, vertex2, weight);
            adjList[vertex1].add(edge1);
            Edge edge2 = new Edge(vertex2, vertex1, weight);
            adjList[vertex2].add(edge2);
        }
    }

    @Override
    public void addEdgeWithCapacity(int vertex1, int vertex2, int capacity, int flow) {
        if (vertex1 < verticesCount && vertex2 < verticesCount) {
            Edge edge1 = new Edge(vertex1, vertex2, capacity, flow);
            adjList[vertex1].add(edge1);
            Edge edge2 = new Edge(vertex2, vertex1, capacity, flow);
            adjList[vertex2].add(edge2);
        }
    }
}
