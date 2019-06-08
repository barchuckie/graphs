package hypercube_max_flow;

import controller.Controller;
import edmonds_karp.EdmondsKarpAlgorithm;
import graphmodel.DirectedGraph;
import graphmodel.Edge;
import graphmodel.HyperCube;

public class MaxFlowController implements Controller {
    private int size;
    private DirectedGraph graph;


    MaxFlowController(int size) {
        this.size = size;
        long startTime, elapsedTime;

        startTime = System.nanoTime();
        graph = new HyperCube(size);
        elapsedTime = System.nanoTime() - startTime;
        System.err.println("Total time: " + elapsedTime / 1000000 + " ms");
    }

    @Override
    public void start() {
        long startTime, elapsedTime;

        startTime = System.nanoTime();
        EdmondsKarpAlgorithm algorithm = new EdmondsKarpAlgorithm(graph, size);
        algorithm.maxFlow(0, graph.getVerticesCount() - 1);
        elapsedTime = System.nanoTime() - startTime;

        System.err.println("Total time: " + elapsedTime / 1000000 + " ms");
        System.err.println("Init flow: " + calculateInitFlow());
        System.err.println("Sink flow: " + calculateSinkFlow());
        System.err.println("Paths: " + algorithm.getPathCounter());
    }

    private void printFlowArray() {
        for (int v = 0; v < graph.getVerticesCount(); v++) {
            System.out.print(v + " : ");
            for (Edge e : graph.getEdgesFrom(v)) {
                System.out.print(e.getDestinationVertex() + "(" + e.getFlow() + ") ");
            }
            System.out.println();
        }
    }

    private int calculateInitFlow() {
        int flow = 0;
        for (Edge e : graph.getEdgesFrom(0)) {
            flow += e.getFlow();
        }
        return flow;
    }

    private int calculateSinkFlow() {
        int flow = 0;
        for (Edge e : graph.getEdgesFrom(graph.getVerticesCount()-1)) {
            flow += e.getFlow();
        }
        return -flow;
    }
}
