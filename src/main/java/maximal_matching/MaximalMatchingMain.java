package maximal_matching;

public class MaximalMatchingMain {
    public static void main(String [] args) {
        if (args.length < 4) {
            printUsage();
            return;
        }

        boolean isLP = false;

        if (args[0].equals("--size") && args[2].equals("--degree")) {
            try {
                int size = Integer.parseInt(args[1]);
                int degree = Integer.parseInt(args[3]);
                if (args.length > 4 && args[4].equals("--glpk")) {
                    isLP = true;
                }
                MaximalMatchingController app = new MaximalMatchingController(size, degree, isLP);
                app.start();
            } catch (NumberFormatException e) {
                printUsage();
            }
        }
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("MaximalMatchingMain --size {1...16} --degree {1...size}");
    }
}
