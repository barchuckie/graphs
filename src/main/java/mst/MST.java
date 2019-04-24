package mst;

import graphmodel.Edge;
import graphmodel.Graph;

import java.util.List;

public interface MST {
    List<Edge> findMST(Graph graph);
}
