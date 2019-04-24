package mst.kruskal;

import graphmodel.Edge;
import graphmodel.Graph;
import mst.MST;
import mst.UnionFind;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KruskalMST implements MST {

    @Override
    public List<Edge> findMST(Graph graph) {
        UnionFind sets = new UnionFind(graph.getVerticesCount());
        ArrayList<Edge> edges = new ArrayList<>();
        ArrayList<Edge> mstEdges = new ArrayList<>();
        int u, v;

        for (int vertex = 0; vertex < graph.getVerticesCount(); vertex++) {
            sets.makeSet(vertex);
            for (Edge edge : graph.getEdgesFrom(vertex)) {
                if (edge.getDestinationVertex() > vertex) {
                    edges.add(edge);
                }
            }
        }

        edges.sort(Comparator.comparingInt(Edge::getWeight));

        for (Edge edge : edges) {
            u = edge.getSourceVertex();
            v = edge.getDestinationVertex();
            if (sets.findSet(u) != sets.findSet(v)) {
                mstEdges.add(edge);
                sets.union(u, v);
            }
        }

        return mstEdges;
    }

}
