package mst;

public class UnionFind {

    private int[] rank;
    private int[] root;

    public UnionFind(int setCount) {
        rank = new int[setCount];
        root = new int[setCount];
    }

    public void makeSet(int x) {
        root[x] = x;
        rank[x] = 0;
    }

    public void union(int x, int y) {
        int xRoot = findSet(x);
        int yRoot = findSet(y);

        if (rank[xRoot] > rank[yRoot]) {
            root[yRoot] = xRoot;
        } else {
            root[xRoot] = yRoot;
            if (rank[x] == rank[y]) {
                rank[yRoot]++;
            }
        }
    }

    public int findSet(int x) {
        if (x != root[x]) {
            root[x] = findSet(root[x]);
        }
        return root[x];
    }
}
