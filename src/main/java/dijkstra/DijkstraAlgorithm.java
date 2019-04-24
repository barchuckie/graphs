package dijkstra;

import graphmodel.Edge;
import graphmodel.Graph;
import priorityqueue.PriorityQueue;

import java.util.LinkedList;
import java.util.List;


public class DijkstraAlgorithm {
    private Graph graph;

    public DijkstraAlgorithm(Graph graph) {
        this.graph = graph;
    }

    public List<Edge>[] findShortestPaths(int startingVertex) {
        PriorityQueue queue = new PriorityQueue(graph.getVerticesCount());
        List<Edge>[] paths = new List[graph.getVerticesCount()];
        int [] dist = new int[graph.getVerticesCount()];

        for (int vertex = 0; vertex < graph.getVerticesCount(); vertex++) {
            dist[vertex] = Integer.MAX_VALUE;
            paths[vertex] = new LinkedList<>();
            queue.insert(vertex, dist[vertex]);
        }

        dist[startingVertex] = 0;
        queue.setPriority(startingVertex, dist[startingVertex]);

        while (!queue.isEmpty()) {
            int u = queue.pop();
            for (Edge e : graph.getEdgesFrom(u)) {
                int v = e.getDestinationVertex();
                if (dist[v] > dist[u] + e.getWeight()) {
                    dist[v] = dist[u] + e.getWeight();
                    paths[v] = new LinkedList<>();
                    paths[v].addAll(paths[u]);
                    paths[v].add(e);
                    queue.setPriority(v, dist[v]);
                }
            }
        }

        return paths;
    }

    public List<Edge> findShortestPathTo(int startingVertex, int destinationVertex) {
        return findShortestPaths(startingVertex)[destinationVertex];
    }
}
