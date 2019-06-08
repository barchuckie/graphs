package maximal_matching;

import edmonds_karp.EdmondsKarpAlgorithm;
import graphmodel.DirectedGraph;
import graphmodel.Edge;

import java.util.ArrayList;
import java.util.Random;

public class MaximalMatchingController {
    private int size;
    private int degree;
    private DirectedGraph graph;
    private Random random;

    MaximalMatchingController(int size, int degree) {
        this.size = size;
        this.degree = degree;
        random = new Random();
    }

    void start() {
        long startTime, elapsedTime;

        startTime = System.nanoTime();
        createBigraph();
        printBigraph();
        EdmondsKarpAlgorithm algorithm = new EdmondsKarpAlgorithm(graph, size);
        algorithm.maxFlow(0, graph.getVerticesCount() - 1);
        elapsedTime = System.nanoTime() - startTime;

        System.err.println("Total time: " + elapsedTime / 1000000 + " ms");
        System.err.println("Paths: " + algorithm.getPathCounter());
    }

    private void createBigraph() {
        int setCount = (int) Math.pow(2, size);
        int verticesCount = 2*setCount + 2;
        graph = new DirectedGraph(verticesCount);
        ArrayList<Integer> leftVertices = new ArrayList<>();
        int randomIdx;

        for (int i = 1; i <= setCount; i++) {
            leftVertices.add(i);
            graph.addEdgeWithCapacity(0, i, 1, 0);
            graph.addEdgeWithCapacity(i, 0, 0, 0);
            graph.addEdgeWithCapacity(verticesCount-1-i, verticesCount - 1, 1, 0);
            graph.addEdgeWithCapacity(verticesCount - 1, verticesCount-1-i, 0, 0);
        }

        int x = degree + 1; //edge to source is added
        for (int v = setCount + 1; v <= 2*setCount; v++) {
            for (int i = 0; i < degree; i++) {
                randomIdx = random.nextInt(leftVertices.size());
                graph.addEdgeWithCapacity(leftVertices.get(randomIdx), v,1, 0);
                graph.addEdgeWithCapacity(v,leftVertices.get(randomIdx), 0, 0);
                if (graph.getEdgesFrom(leftVertices.get(randomIdx)).size() == x) {
                    leftVertices.remove(randomIdx);
                }
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
