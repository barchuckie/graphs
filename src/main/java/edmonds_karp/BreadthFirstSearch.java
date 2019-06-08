package edmonds_karp;

import graphmodel.Edge;
import graphmodel.Graph;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {
    private Graph graph;
    private int [] parent;
    private int [] dist;
    private Queue<Integer> queue;

    BreadthFirstSearch(Graph graph) {
        this.graph = graph;
        dist = new int [graph.getVerticesCount()];
        parent = new int [graph.getVerticesCount()];
    }

    private void prepare() {
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }
        queue = new LinkedList<>();
    }

    boolean findPath(int source, int destination) {
        prepare();
        dist[source] = 0;
        queue.add(source);
        int vertex;

        while (!queue.isEmpty()) {
            vertex = queue.poll();
            for (Edge edge : graph.getResidualEdgesFrom(vertex)) {
                if (dist[edge.getDestinationVertex()] == Integer.MAX_VALUE) {
                    queue.add(edge.getDestinationVertex());
                    dist[edge.getDestinationVertex()] = dist[vertex] + 1;
                    parent[edge.getDestinationVertex()] = vertex;
                }
            }
        }

        return parent[destination] > -1;
    }

    int[] getParent() {
        return parent;
    }
}
