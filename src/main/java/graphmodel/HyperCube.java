package graphmodel;

import java.util.Random;

public class HyperCube extends DirectedGraph {
    private Random random;
    private int size;

    public HyperCube(int size) {
        super((int) Math.pow(2, size));
        this.size = size;
        random = new Random();
        createHyperCube();
    }

    private void createHyperCube() {
        for (int v = 0; v < getVerticesCount(); v++) {
            addEdgesFrom(v);
        }
    }

    private void addEdgesFrom(int v) {
        /* l = max {hV, zV, hDest, zDest}
         * hW > hV & zV > zW => l = max {hW, zV} */
        int i = 1;
        int potentialVertex, capacity;
        int hV = Integer.bitCount(v);
        int hDest = hV + 1;
        int zV = size - hV;
        int capacityBound = (int) Math.pow(2, Math.max(hDest, zV));

        while (i < getVerticesCount()) {
            potentialVertex = v^i;
            if (potentialVertex > v) {
                capacity = random.nextInt(capacityBound) + 1;
                addEdgeWithCapacity(v, potentialVertex, capacity, 0);
            } else {
                addEdgeWithCapacity(v, potentialVertex, 0, 0);
            }
            i = (i << 1);
        }
    }

    @Override
    public Edge getEdge(int sourceVertex, int destinationVertex) {
        return getEdgesFrom(sourceVertex)
                .get(Integer.numberOfTrailingZeros(sourceVertex^destinationVertex));
    }
}
