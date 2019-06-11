package hypercube_max_flow;

public class MaxFlowExperiments {
    public static void main(String [] args) {
        MaxFlowController app;
        int pathCounter, flow;
        long time;

        System.out.println("k, flow, paths, time");
        for (int k = 1; k < 17; k++) {
            pathCounter = 0;
            flow = 0;
            time = 0;
            for (int i = 0; i < 10; i++) {
                app = new MaxFlowController(k);
                app.start();
                pathCounter += app.paths;
                flow += app.flow;
                time += app.time;
            }
            System.out.println(k + ", " + flow/10 + ", " + pathCounter/10 + ", " + time/10000000);
        }
    }
}
