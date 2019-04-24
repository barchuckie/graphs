package mst;

import controller.GraphController;
import graphmodel.Edge;
import graphmodel.UndirectedGraph;

import java.util.List;
import java.util.Scanner;

public abstract class MSTController extends GraphController {

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        int verticesCount;

        System.out.print("Insert number of vertices: ");
        verticesCount = scanner.nextInt();

        graph = new UndirectedGraph(verticesCount);

        addEdgesWithWeights(scanner);
        /*graph.addEdgeWithWeight(0, 1, 5);
        graph.addEdgeWithWeight(0, 2, 6);
        graph.addEdgeWithWeight(0, 3, 4);
        graph.addEdgeWithWeight(1, 2, 1);
        graph.addEdgeWithWeight(1, 3, 2);
        graph.addEdgeWithWeight(2, 3, 2);
        graph.addEdgeWithWeight(2, 4, 5);
        graph.addEdgeWithWeight(2, 5, 3);
        graph.addEdgeWithWeight(3, 5, 4);
        graph.addEdgeWithWeight(4, 5, 4);*/

        result();
    }

    private void result() {
        MST mstAlgorithm = getAlgorithm();
        List<Edge> mst = mstAlgorithm.findMST(graph);
        int weight = 0;
        int u, v, w;

        for (Edge edge : mst) {
            u = edge.getSourceVertex();
            v = edge.getDestinationVertex();
            w = edge.getWeight();

            System.out.println(u + " " + v + " " + w);

            weight += w;
        }

        System.out.println(weight);
    }

    public abstract MST getAlgorithm();
}
