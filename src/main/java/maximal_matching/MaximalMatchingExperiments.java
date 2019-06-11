package maximal_matching;

public class MaximalMatchingExperiments {
    public static void main(String [] args) {
        MaximalMatchingController app;
        int match;
        long time;

        System.out.println("k, i, match, time");
        for (int k = 3; k < 11; k++) {
            for (int i = 1; i <= k; i++) {
                match = 0;
                time = 0;
                for (int j = 0; j < 10; j++) {
                    app = new MaximalMatchingController(k, i);
                    app.start();
                    match += app.match;
                    time += app.time;
                }
                System.out.println(k + ", " + i + ", " + match/10 +  ", " + time/10000000);
            }
        }
    }
}
