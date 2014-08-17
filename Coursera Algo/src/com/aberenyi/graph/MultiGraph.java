package com.aberenyi.graph;


public class MultiGraph extends Graph {

    /**
     * Given a graph g, construct a graph allowing parallel edges.
     * 
     * @param g
     */
    public MultiGraph(Graph g) {
        super(false);
        edgeMap = g.edgeMap;
        vertexMap = g.vertexMap;
    }

    public MultiGraph() {
        super(false);
    }

}
