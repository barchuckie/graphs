package mst;

import mst.kruskal.KruskalController;
import mst.prim.PrimController;

public class MSTMain {

    public static void main(String [] args) {
        if (args.length > 0) {
            String arg = args[0];
            if (arg.equals("-p")) {
                MSTController app = new PrimController();
                app.start();
            } else if (arg.equals("-k")) {
                MSTController app = new KruskalController();
                app.start();
            } else {
                System.out.println("Usage: MSTMain -p|-k");
            }
        } else {
            System.out.println("Usage: MSTMain -p|-k");
        }
    }

}
