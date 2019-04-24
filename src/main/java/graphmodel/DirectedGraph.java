package graphmodel;

public class DirectedGraph extends Graph {

    public DirectedGraph(int verticesCount) {
        super(verticesCount);
    }

    @Override
    public void addEdge(int sourceVertex, int destinationVertex) {
        if (sourceVertex < verticesCount && destinationVertex < verticesCount) {
            Edge edge = new Edge(sourceVertex, destinationVertex);
            adjList[sourceVertex].add(edge);
        }
    }

    @Override
    public void addEdgeWithWeight(int sourceVertex, int destinationVertex, int weight) {
        if (sourceVertex < verticesCount && destinationVertex < verticesCount) {
            Edge edge = new Edge(sourceVertex, destinationVertex, weight);
            adjList[sourceVertex].add(edge);
        }
    }

    public DirectedGraph reverse() {
        DirectedGraph graph = new DirectedGraph(verticesCount);
        for (int vertex = 0; vertex < verticesCount; vertex++) {
            for (Edge e : adjList[vertex]) {
                graph.adjList[e.getDestinationVertex()].add(e.getReversedEdge());
            }
        }
        return graph;
    }

}
