package hypercube_max_flow;

import controller.Controller;
import edmonds_karp.EdmondsKarpAlgorithm;
import graphmodel.DirectedGraph;
import graphmodel.Edge;
import graphmodel.HyperCube;
import linear_problem_generator.MaxFlowLinearProblemGenerator;

public class MaxFlowController implements Controller {
    private DirectedGraph graph;
    long time;
    int paths;
    int flow;

    MaxFlowController(int size) {
        generateHyperCube(size);
    }

    MaxFlowController(int size, String filename) {
        generateHyperCube(size);

        MaxFlowLinearProblemGenerator modelGenerator = new MaxFlowLinearProblemGenerator(graph, filename);
        modelGenerator.generateModelFile();
    }

    private void generateHyperCube(int size) {
        long startTime, elapsedTime;

        startTime = System.nanoTime();
        graph = new HyperCube(size);
        elapsedTime = System.nanoTime() - startTime;
        System.err.println("Build time: " + elapsedTime / 1000000 + " ms");
    }

    @Override
    public void start() {
        long startTime, elapsedTime;

        startTime = System.nanoTime();
        EdmondsKarpAlgorithm algorithm = new EdmondsKarpAlgorithm(graph);
        algorithm.maxFlow(0, graph.getVerticesCount() - 1);
        elapsedTime = System.nanoTime() - startTime;

        paths = algorithm.getPathCounter();
        time = elapsedTime;
        flow = calculateSinkFlow();

        System.err.println("Total time: " + elapsedTime / 1000000 + " ms");
        System.err.println("Paths: " + algorithm.getPathCounter());
        System.out.println("Flow: " + calculateSinkFlow());
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
