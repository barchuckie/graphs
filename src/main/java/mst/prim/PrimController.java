package mst.prim;

import mst.MST;
import mst.MSTController;

public class PrimController extends MSTController {

    @Override
    public MST getAlgorithm() {
        return new PrimMST();
    }

}
