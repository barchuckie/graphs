package graphmodel;

public class Edge {
    private final int destinationVertex;
    private final int sourceVertex;
    private Integer weight;
    private Integer capacity;
    private Integer flow;

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

    Edge(int sourceVertex, int destinationVertex, Integer capacity, Integer flow) {
        this.sourceVertex = sourceVertex;
        this.destinationVertex = destinationVertex;
        this.capacity = capacity;
        this.flow = flow;
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getFlow() {
        return flow;
    }

    public void setFlow(Integer flow) {
        this.flow = flow;
    }

    public void changeFlow(int df) {
        flow += df;
    }

    public Integer getResidualCapacity() {
        return capacity - flow;
    }
}
