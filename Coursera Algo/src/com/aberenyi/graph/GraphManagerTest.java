package com.aberenyi.graph;

import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class GraphManagerTest {

    @Test
    public void testReadEdgeList() {
        List<Edge> edgeList = GraphManager
                .readEdgeList("/Users/aberenyi/Documents/workspace/Coursera Algo 2/input/algo2/assign1/edges.txt");
        Assert.assertEquals(2184, edgeList.size());
    }

    @Test
    public void testGraph() {
        Graph g = new Graph();
        Edge edge = new Edge(1, 2, 3);
        Edge b = g.addEdge(edge);
        //System.out.println(edge.toString() + edge.hashCode() + b);
        edge = new Edge(1, 2, 3);
        b = g.addEdge(edge);
        //System.out.println(edge.toString() + edge.hashCode() + b);

        Assert.assertEquals(1, g.getEdgeCount());

    }

    @Test
    public void testReadGraph() {
        Graph g = GraphManager
                .readGraph("/Users/aberenyi/Documents/workspace/Coursera Algo 2/input/algo2/assign1/edges.txt");
        Assert.assertEquals(2184, g.getEdgeCount());
    }

    @Test
    public void testReadSimpleGraph() {
        Graph g = GraphManager
                .readSimpleGraphFromAdjacencyList(
                        "/Users/aberenyi/Documents/workspace/Coursera Algo 2/input/algo1/assign3/kargerMinCut2.txt",
                        true);
        Assert.assertEquals(200, g.getVertexCount());
        Assert.assertEquals(2517, g.getEdgeCount());
    }

    @Test
    public void testReadSimpleGraph2() {
        Graph g = GraphManager
                .readSimpleGraphFromAdjacencyList(
                        "/Users/aberenyi/Documents/workspace/Coursera Algo 2/input/algo1/assign3/testAdjacencyList.txt",
                        true);
        Assert.assertEquals(4, g.getVertexCount());
        Collection<Edge> es = g.getEdges();
        for (Edge edge : es) {
            System.out.println(edge);
        }
        
        Assert.assertEquals(5, g.getEdgeCount());
    }

}
