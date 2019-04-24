package scc;

import graphmodel.Edge;
import graphmodel.Graph;

import java.util.ArrayList;

public class DepthFirstSearch {
    private final Graph graph;
    private boolean [] visited;
    private int [] post;
    private int idx;

    DepthFirstSearch(Graph graph) {
        this.graph = graph;
        visited = new boolean [graph.getVerticesCount()];
        post = new int [graph.getVerticesCount()];
    }

    private void prepare() {
        post = new int [graph.getVerticesCount()];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        idx = 0;
    }

    public int [] calculatePost() {
        prepare();

        for (int v = 0; v < graph.getVerticesCount(); v++) {
            if (!visited[v]) {
                explore(v);
            }
        }

        return post;
    }

    public ArrayList<ArrayList<Integer>> findSCC(int [] order) {
        ArrayList<ArrayList<Integer>> sccs = new ArrayList<>();

        prepare();

        for (int i = order.length-1; i >= 0 ; i--) {
            int v = order[i];
            if (!visited[v]) {
                sccs.add(explore(v));
            }
        }

        return sccs;
    }

    private ArrayList<Integer> explore(int vertex) {
        visited[vertex] = true;
        ArrayList<Integer> component = new ArrayList<>();

        for (Edge edge : graph.getEdgesFrom(vertex)) {
            int u = edge.getDestinationVertex();
            if (!visited[u]) {
                component.addAll(explore(u));
            }
        }

        component.add(vertex);
        post[idx] = vertex;
        idx++;
        return component;
    }
}
