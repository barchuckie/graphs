package graphmodel;

import dijkstra.DijkstraAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Graph {
    final protected int verticesCount;

    final protected ArrayList<Edge>[] adjList;

    public Graph(int verticesCount) {
        this.verticesCount = verticesCount;

        adjList = new ArrayList[verticesCount];

        for (int i = 0; i < verticesCount; ++i) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void removeEdge(int v1, int v2) {
        if (v1 < verticesCount && v2 < verticesCount) {
            for (Edge neighbour : adjList[v1]) {
                if(neighbour.getDestinationVertex() == v2) {
                    adjList[v1].remove(neighbour);
                    break;
                }
            }
            for (Edge neighbour : adjList[v2]) {
                if(neighbour.getDestinationVertex() == v1) {
                    adjList[v2].remove(neighbour);
                    break;
                }
            }
        }
    }

    public List<Edge> getEdgesFrom(int v) {
        return adjList[v];
    }

    public int getVerticesCount() {
        return verticesCount;
    }

    public void removeEdge(Edge Edge) {
        for (ArrayList<Edge> Edges : adjList) {
            for (Edge e : Edges) {
                if(Objects.equals(Edge, e)) {
                    Edges.remove(Edge);
                    break;
                }
            }
        }
    }

    public abstract void addEdge(int vertex1, int vertex2);
    public abstract void addEdgeWithWeight(int vertex1, int vertex2, int weight);

    public List<Edge> findShortestPath(int startingVertex, int destinationVertex) {
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(this);
        return dijkstraAlgorithm.findShortestPathTo(startingVertex, destinationVertex);
    }
}
