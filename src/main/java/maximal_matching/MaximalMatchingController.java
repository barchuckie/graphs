package maximal_matching;

import edmonds_karp.EdmondsKarpAlgorithm;
import graphmodel.DirectedGraph;
import graphmodel.Edge;
import linear_problem_generator.MaxFlowLinearProblemGenerator;

import java.util.Random;

public class MaximalMatchingController {
    private int size;
    private int degree;
    private boolean isLP;
    private DirectedGraph graph;
    private Random random;

    MaximalMatchingController(int size, int degree, boolean isLP) {
        this.size = size;
        this.degree = degree;
        this.isLP = isLP;
        random = new Random();
    }

    void start() {
        long startTime, elapsedTime;

        startTime = System.nanoTime();
        createBigraph();
        elapsedTime = System.nanoTime() - startTime;
        System.err.println("Total build time: " + elapsedTime / 1000000 + " ms");

        if (isLP) {
            MaxFlowLinearProblemGenerator modelGenerator = new MaxFlowLinearProblemGenerator(graph);
            modelGenerator.generateModelFile();
        }

        startTime = System.nanoTime();
        EdmondsKarpAlgorithm algorithm = new EdmondsKarpAlgorithm(graph);
        algorithm.maxFlow(0, graph.getVerticesCount() - 1);
        elapsedTime = System.nanoTime() - startTime;

        System.err.println("Total time: " + elapsedTime / 1000000 + " ms");
        System.err.println("Result: " + algorithm.getPathCounter());
    }

    private void createBigraph() {
        int setCount = (int) Math.pow(2, size);
        int verticesCount = 2*setCount + 2;
        graph = new DirectedGraph(verticesCount);
        int randomVertex;

        for (int v = 1; v <= setCount; v++) {
            graph.addEdgeWithCapacity(0, v, 1, 0);
            graph.addEdgeWithCapacity(v, 0, 0, 0);
            graph.addEdgeWithCapacity(verticesCount-1-v, verticesCount - 1, 1, 0);
            graph.addEdgeWithCapacity(verticesCount - 1, verticesCount-1-v, 0, 0);

            for (int i = 0; i < degree; i++) {
                randomVertex = random.nextInt(setCount) + setCount + 1;
                graph.addEdgeWithCapacity(v, randomVertex, 1, 0);
                graph.addEdgeWithCapacity(randomVertex, v, 0, 0);
            }
        }
    }

    private void printBigraph() {
        for (int v = 0; v < graph.getVerticesCount(); v++) {
            System.out.print(v + " : ");
            for (Edge e : graph.getEdgesFrom(v)) {
                if (e.getCapacity() == 1) {
                    System.out.print(e.getDestinationVertex() + " ");
                }
            }
            System.out.println();
        }
    }
}
