package dijkstra;

import graphmodel.DirectedGraph;

public class DijkstraMain {

    public static void main(String [] args) {
        DijkstraController app = new DijkstraController();
        app.start();
        /*int verticesCount = 8;
        int startingVertex = 3;

        DirectedGraph graph = new DirectedGraph(verticesCount);
        graph.addEdgeWithWeight(0, 1, 1);
        graph.addEdgeWithWeight(1, 2, 4);
        graph.addEdgeWithWeight(2, 3, 7);
        graph.addEdgeWithWeight(3, 4, 2);
        graph.addEdgeWithWeight(4, 5, 8);
        graph.addEdgeWithWeight(5, 6, 3);
        graph.addEdgeWithWeight(6, 7, 5);
        graph.addEdgeWithWeight(7, 0, 3);

        app.printPaths(graph, startingVertex);*/
    }

}
