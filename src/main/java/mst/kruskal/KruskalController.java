package mst.kruskal;

import mst.MST;
import mst.MSTController;

public class KruskalController extends MSTController {

    @Override
    public MST getAlgorithm() {
        return new KruskalMST();
    }

}
