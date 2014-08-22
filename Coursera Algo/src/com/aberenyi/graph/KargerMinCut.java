package com.aberenyi.graph;

public class KargerMinCut {

    /**
     * Implementation of Karger's min cut
     * 
     * @param g Undirected simple graph with no parallel edges, each edge of
     *            weight 1.
     * @return The minimum number of cuts of this graph.
     * @throws GraphException
     */
    public static int calculate(String fileName) {

        int minEdges = Integer.MAX_VALUE;
        Graph g = new Graph();
        GraphManager.loadSimpleGraphFromAdjacencyList(fileName, g);

        int vertexCount = g.getVertexCount();
        int iteration = (int) (vertexCount * vertexCount * Math
                .log(vertexCount));
        do {
            g.reset();
            GraphManager.loadSimpleGraphFromAdjacencyList(fileName, g);
            final int edgeCount = oneCut(g);
            if (edgeCount < minEdges) {
                minEdges = edgeCount;
                System.out.println("new minCut " + minEdges);
            }
        } while (iteration-- > 0);
        return minEdges;
    }

    /**
     * Implementation of Karger's min cut
     * 
     * @param g Undirected simple graph with no parallel edges, each edge of
     *            weight 1.
     * @return The minimum number of cuts of this graph.
     * @throws GraphException
     */
    public static int oneCut(Graph g) {
        Vertex v = null;
        while (g.getVertexCount() > 2) {
            // pick a random edge
            Edge e = g.getRandomEdge();
            // collapse the two vertices
            v = g.collapseEdge(e);
            // remove self nodes of the new vertex
            g.removeSelfLoops(v);
        }
        return v.getEdgeCount();
    }
}
