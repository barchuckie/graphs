package linear_problem_generator;

import graphmodel.DirectedGraph;
import graphmodel.Edge;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class MaxFlowLinearProblemGenerator {
    private DirectedGraph graph;
    private final String INPUT_FILE_NAME = "src/main/resources/max_flow_model.txt";
    private final String OUTPUT_DIRECTORY = "/Users/barchuckie/";
    private final String filename;

    public MaxFlowLinearProblemGenerator(DirectedGraph graph, String filename) {
        this.graph = graph;
        this.filename = filename;
    }

    public void generateModelFile() {
        try {
            Scanner scanner = new Scanner(new File(Paths.get(INPUT_FILE_NAME).toUri()));
            PrintWriter output = new PrintWriter(new FileWriter(OUTPUT_DIRECTORY + filename));

            while (scanner.hasNextLine())
                output.println(scanner.nextLine());

            scanner.close();
            output.println();
            int n = graph.getVerticesCount() - 1;
            output.println("param n := " + n + ";");
            output.println();
            output.println("param : E :   c :=");
            boolean newLine = false;

            for (int v = 0; v < graph.getVerticesCount(); v++) {
                for (Edge e : graph.getEdgesFrom(v)) {
                    if (e.getCapacity() > 0) {
                        if (newLine) {
                            output.println();
                        }
                        newLine = true;
                        output.print("\t\t" + v + " " + e.getDestinationVertex() + "  " + e.getCapacity());
                    }
                }
            }

            output.println(";");
            output.println();
            output.println("end;");
            output.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
