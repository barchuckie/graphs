package maximal_matching;

import controller.Controller;
import edmonds_karp.EdmondsKarpAlgorithm;
import graphmodel.DirectedGraph;
import graphmodel.Edge;
import linear_problem_generator.MaxFlowLinearProblemGenerator;

import java.util.Random;

public class MaximalMatchingController implements Controller {
    private int size;
    private int degree;
    private boolean isLP;
    private DirectedGraph graph;
    private Random random;

    long time;
    int match;

    MaximalMatchingController(int size, int degree) {
        this.size = size;
        this.degree = degree;
        this.isLP = false;
        random = new Random();
        createBigraph();
    }

    MaximalMatchingController(int size, int degree, String filename) {
        this.size = size;
        this.degree = degree;
        this.isLP = true;
        random = new Random();
        createBigraph();

        MaxFlowLinearProblemGenerator modelGenerator = new MaxFlowLinearProblemGenerator(graph, filename);
        modelGenerator.generateModelFile();
    }

    @Override
    public void start() {
        long startTime, elapsedTime;

        startTime = System.nanoTime();
        EdmondsKarpAlgorithm algorithm = new EdmondsKarpAlgorithm(graph);
        algorithm.maxFlow(0, graph.getVerticesCount() - 1);
        elapsedTime = System.nanoTime() - startTime;
        time = elapsedTime;
        match = algorithm.getPathCounter();
        System.err.println("Total time: " + elapsedTime / 1000000 + " ms");
        System.out.println("Result: " + algorithm.getPathCounter());
    }

    private void createBigraph() {
        long startTime, elapsedTime;
        startTime = System.nanoTime();
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
                if (checkVerticesConnection(v, randomVertex)) {
                    graph.addEdgeWithCapacity(v, randomVertex, 1, 0);
                    graph.addEdgeWithCapacity(randomVertex, v, 0, 0);
                } else i--;
            }
        }
        elapsedTime = System.nanoTime() - startTime;
        System.err.println("Build time: " + elapsedTime / 1000000 + " ms");
    }

    private boolean checkVerticesConnection(int source, int dest) {
        for (Edge e : graph.getEdgesFrom(source)) {
            if (e.getDestinationVertex() == dest) return false;
        }
        return true;
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
