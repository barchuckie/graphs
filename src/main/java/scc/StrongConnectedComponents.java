package scc;

import graphmodel.DirectedGraph;

import java.util.ArrayList;

public class StrongConnectedComponents {
    private DirectedGraph graph;

    StrongConnectedComponents(DirectedGraph graph) {
        this.graph = graph;
    }

    ArrayList<ArrayList<Integer>> findSCC() {
        DirectedGraph reversed = graph.reverse();

        DepthFirstSearch searchReversed = new DepthFirstSearch(graph);

        int [] order = searchReversed.calculatePost();

        DepthFirstSearch searchGraph = new DepthFirstSearch(reversed);

        return searchGraph.findSCC(order);
    }
}
