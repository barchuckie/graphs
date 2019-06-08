package edmonds_karp;

import graphmodel.DirectedGraph;
import graphmodel.Edge;

public class EdmondsKarpAlgorithm {

    private DirectedGraph graph;
    private int size;
    private int pathCounter;

    public EdmondsKarpAlgorithm(DirectedGraph graph, int size) {
        this.graph = graph;
        this.size = size;
    }

    public void maxFlow(int source, int sink) {
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph);
        Edge [] path = new Edge[size];
        int[] parent;
        int v;
        int pathFlow;
        int pathIdx;
        pathCounter = 0;

        while (bfs.findPath(source, sink)) {
            pathCounter++;
            parent = bfs.getParent();
            pathFlow = Integer.MAX_VALUE;
            pathIdx = 0;
            v = sink;
            while (parent[v] > -1) {
                path[pathIdx] = graph.getEdge(parent[v], v);
                pathFlow = Math.min(path[pathIdx].getResidualCapacity(), pathFlow);
                v = parent[v];
                pathIdx++;
            }
            for (int i = 0; i < path.length; i++) {
                path[i].changeFlow(pathFlow);
                graph.getEdge(path[i].getDestinationVertex(), path[i].getSourceVertex()).changeFlow(-pathFlow);
            }
        }
    }

    public int getPathCounter() {
        return pathCounter;
    }
}
