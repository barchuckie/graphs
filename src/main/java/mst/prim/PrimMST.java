package mst.prim;

import graphmodel.Edge;
import graphmodel.Graph;
import mst.MST;
import priorityqueue.PriorityQueue;

import java.util.ArrayList;
import java.util.List;

public class PrimMST implements MST {

    @Override
    public List<Edge> findMST(Graph graph) {
        int [] cost = new int[graph.getVerticesCount()];
        Edge [] prev = new Edge[graph.getVerticesCount()];
        ArrayList<Edge> mstEdges = new ArrayList<>(graph.getVerticesCount());
        PriorityQueue queue = new PriorityQueue(graph.getVerticesCount());
        int u, v, w;

        for (int vertex = 0; vertex < graph.getVerticesCount(); vertex++) {
            cost[vertex] = Integer.MAX_VALUE;
            prev[vertex] = null;
            queue.insert(vertex, cost[vertex]);
        }
        cost[0] = 0;
        queue.setPriority(0, cost[0]);

        while (!queue.isEmpty()) {
            u = queue.pop();
            if (prev[u] != null) {
                mstEdges.add(prev[u]);
            }
            for (Edge edge : graph.getEdgesFrom(u)) {
                v = edge.getDestinationVertex();
                w = edge.getWeight();
                if (cost[v] > w) {
                    cost[v] = w;
                    prev[v] = edge;
                    queue.setPriority(v, cost[v]);
                }
            }
        }

        return mstEdges;
    }

}
