package hypercube_max_flow;

public class MaxFlowMain {
    public static void main(String [] args) {
        if (args.length < 2) {
            printUsage();
            return;
        }

        boolean isLP = false;
        String filename = "maxflowmodel.mod";
        int size = 0;
        MaxFlowController app;

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "--size":
                    if (i < args.length-1) {
                        try {
                            size = Integer.parseInt(args[i+1]);
                        } catch (NumberFormatException e) {
                            printUsage();
                            return;
                        }
                    }
                    break;

                case "--glpk":
                    isLP = true;
                    if (i < args.length-1) {
                        filename = args[i+1];
                    }
                    break;

                default:
                    break;
            }
        }

        if (size == 0) {
            printUsage();
            return;
        }

        if (isLP) {
            app = new MaxFlowController(size, filename);
        } else {
            app = new MaxFlowController(size);
        }
        app.start();
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("MaxFlowMain --size {1...16} (--glpk filename)");
    }
}
