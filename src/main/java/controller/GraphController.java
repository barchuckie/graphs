package controller;

import graphmodel.Graph;

import java.util.Scanner;

public abstract class GraphController implements Controller {

    protected Graph graph;

    protected void addEdgesWithWeights(Scanner scanner) {
        int source, destination, weight;
        System.out.print("Insert number of edges: ");
        int edgesCount = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < edgesCount; i++) {
            source = scanner.nextInt();
            destination = scanner.nextInt();
            weight = scanner.nextInt();

            graph.addEdgeWithWeight(source, destination, weight);
        }
    }

    protected void addEdges(Scanner scanner) {
        int source, destination;
        System.out.print("Insert number of edges: ");
        int edgesCount = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < edgesCount; i++) {
            source = scanner.nextInt();
            destination = scanner.nextInt();

            graph.addEdge(source, destination);
        }
    }

}
