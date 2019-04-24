package dijkstra;

import controller.GraphController;
import graphmodel.DirectedGraph;
import graphmodel.Edge;

import java.util.List;
import java.util.Scanner;

class DijkstraController extends GraphController {

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        int verticesCount, startingVertex;

        System.out.print("Insert number of vertices: ");
        verticesCount = scanner.nextInt();

        graph = new DirectedGraph(verticesCount);

        addEdgesWithWeights(scanner);

        System.out.print("Insert starting vertex: ");
        startingVertex = scanner.nextInt();

        printPaths(startingVertex);
    }

    private void printPaths(int startingVertex) {
        int weight;
        long startTime, elapsedTime;

        DijkstraAlgorithm algorithm = new DijkstraAlgorithm(graph);

        startTime = System.nanoTime();
        List<Edge>[] paths = algorithm.findShortestPaths(startingVertex);
        elapsedTime = System.nanoTime() - startTime;

        for (int vertex = 0; vertex < paths.length; vertex++) {
            weight = 0;

            for (Edge e : paths[vertex]) {
                weight += e.getWeight();
                System.err.print(e.getSourceVertex() + " -" + e.getWeight() + "-> " + e.getDestinationVertex() + "\t");
            }
            System.err.println();

            System.out.println(vertex + " " + weight);
        }
        System.err.println("Total time: " + elapsedTime/1000000 + " ms");
    }

}
