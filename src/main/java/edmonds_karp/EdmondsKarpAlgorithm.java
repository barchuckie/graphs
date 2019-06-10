package edmonds_karp;

import graphmodel.DirectedGraph;
import graphmodel.Edge;

import java.util.ArrayList;

public class EdmondsKarpAlgorithm {

    private DirectedGraph graph;
    private int pathCounter;

    public EdmondsKarpAlgorithm(DirectedGraph graph) {
        this.graph = graph;
    }

    public void maxFlow(int source, int sink) {
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph);
        ArrayList<Edge> path;
        int[] parent;
        int v;
        int pathFlow;
        Edge e;
        pathCounter = 0;

        while (bfs.findPath(source, sink)) {
            pathCounter++;
            parent = bfs.getParent();
            pathFlow = Integer.MAX_VALUE;
            path = new ArrayList<>();
            v = sink;
            while (parent[v] > -1) {
                e = graph.getEdge(parent[v], v);
                path.add(e);
                pathFlow = Math.min(e.getResidualCapacity(), pathFlow);
                v = parent[v];
            }
            for (int i = 0; i < path.size(); i++) {
                path.get(i).changeFlow(pathFlow);
                graph.getEdge(path.get(i).getDestinationVertex(), path.get(i).getSourceVertex()).changeFlow(-pathFlow);
            }
        }
    }

    public int getPathCounter() {
        return pathCounter;
    }
}
