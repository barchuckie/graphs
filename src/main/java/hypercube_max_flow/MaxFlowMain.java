package hypercube_max_flow;

public class MaxFlowMain {
    public static void main(String [] args) {
        if (args.length < 2) {
            printUsage();
            return;
        }

        boolean isLP = false;

        if (args[0].equals("--size")) {
            try {
                int size = Integer.parseInt(args[1]);
                if (args.length > 2 && args[2].equals("--glpk")) {
                    isLP = true;
                }
                MaxFlowController app = new MaxFlowController(size, isLP);
                app.start();
            } catch (NumberFormatException e) {
                printUsage();
            }
        }
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("MaxFlowMain --size {1...16}");
    }
}
