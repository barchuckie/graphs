package maximal_matching;

public class MaximalMatchingMain {
    public static void main(String [] args) {
        if (args.length < 4) {
            printUsage();
            return;
        }

        boolean isLP = false;
        String filename = "maxflowmodel.mod";
        int size = 0;
        int degree = 0;
        MaximalMatchingController app;

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "--size":
                    if (i < args.length-1) {
                        try {
                            size = Integer.parseInt(args[i+1]);
                        } catch (NumberFormatException e) {
                            printUsage();
                        }
                    }
                    break;

                case "--degree":
                    if (i < args.length-1) {
                        try {
                            degree = Integer.parseInt(args[i+1]);
                        } catch (NumberFormatException e) {
                            printUsage();
                        }
                    }
                    break;

                case "--glpk":
                    isLP = true;
                    if (i < args.length-1) {
                        filename = args[i+1];
                    } else printUsage();
                    break;

                default:
                    break;
            }
        }

        if (size == 0 || degree == 0) {
            printUsage();
        }

        if (isLP) {
            app = new MaximalMatchingController(size, degree, filename);
        } else {
            app = new MaximalMatchingController(size, degree);
        }
        app.start();
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("MaximalMatchingMain --size {1...16} --degree {1...size} (--glpk filename)");
    }
}
