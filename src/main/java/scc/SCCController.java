package scc;

import controller.GraphController;
import graphmodel.DirectedGraph;

import java.util.ArrayList;
import java.util.Scanner;

public class SCCController extends GraphController {

    private DirectedGraph graph;

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        int verticesCount;

        System.out.print("Insert number of vertices: ");
        verticesCount = scanner.nextInt();

        graph = new DirectedGraph(verticesCount);
        super.graph = this.graph;

        addEdges(scanner);

        result();
    }

    private void result() {
        long startTime, elapsedTime;
        StrongConnectedComponents strongConnectedComponents = new StrongConnectedComponents(graph);

        startTime = System.nanoTime();
        ArrayList<ArrayList<Integer>> sccs = strongConnectedComponents.findSCC();
        elapsedTime = System.nanoTime() - startTime;

        for (ArrayList<Integer> scc : sccs) {
            for (int i = scc.size() - 1; i >= 0; i--) {
                Integer vertex = scc.get(i);
                System.out.print(vertex + " ");
            }
            System.out.println();
        }

        System.err.println("Total time: " + elapsedTime/1000000 + " ms");
    }
}
