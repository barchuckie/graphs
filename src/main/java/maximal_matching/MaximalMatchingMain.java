package maximal_matching;

public class MaximalMatchingMain {
    public static void main(String [] args) {
        if (args.length < 4) {
            printUsage();
            return;
        }

        if (args[0].equals("--size") && args[2].equals("--degree")) {
            try {
                int size = Integer.parseInt(args[1]);
                int degree = Integer.parseInt(args[3]);
                MaximalMatchingController app = new MaximalMatchingController(size, degree);
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
