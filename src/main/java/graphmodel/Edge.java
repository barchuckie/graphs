package graphmodel;

public class Edge {
    private final int destinationVertex;
    private final int sourceVertex;
    private Integer weight;

    Edge(int sourceVertex, int destinationVertex) {
        this.sourceVertex = sourceVertex;
        this.destinationVertex = destinationVertex;
        weight = null;
    }

    Edge(int sourceVertex, int destinationVertex, Integer weight) {
        this.sourceVertex = sourceVertex;
        this.destinationVertex = destinationVertex;
        this.weight = weight;
    }

    Edge(Edge edge) {
        this.sourceVertex = edge.sourceVertex;
        this.destinationVertex = edge.destinationVertex;
        this.weight = edge.weight;
    }

    public Edge getReversedEdge() {
        return new Edge(destinationVertex, sourceVertex, weight);
    }

    public int getSourceVertex() {
        return sourceVertex;
    }

    public int getDestinationVertex() {
        return destinationVertex;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
