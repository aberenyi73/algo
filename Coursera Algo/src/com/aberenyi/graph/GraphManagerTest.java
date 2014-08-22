package com.aberenyi.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

import com.bioseek.StopWatch;

public class GraphManagerTest {

    @Test
    public void testReadEdgeList() {
        List<Edge> edgeList = GraphManager
                .readEdgeList("/Users/aberenyi/git/algo/Coursera Algo/input/algo2/assign1/edges.txt");
        Assert.assertEquals(2184, edgeList.size());
    }

    @Test
    public void testGraph() {
        Graph g = new Graph();
        Edge edge = new Edge(1, 2, 3);
        edge.setIntKey(1);
        g.addEdge(edge);
        // System.out.println(edge.toString() + edge.hashCode() + b);
        edge = new Edge(1, 2, 3);
        edge.setIntKey(1);
        g.addEdge(edge);
        // System.out.println(edge.toString() + edge.hashCode() + b);

        Assert.assertEquals(2, g.getEdgeCount());

    }

    @Test
    public void testReadGraph() {
        Graph g = GraphManager
                .readGraph("/Users/aberenyi/git/algo/Coursera Algo/input/algo2/assign1/edges.txt");
        Assert.assertEquals(2184, g.getEdgeCount());
    }

    @Test
    public void testReadSimpleGraph() {
        Graph g = new Graph();
        Map<Integer, Integer> dm = GraphManager
                .loadSimpleGraphFromAdjacencyList(
                        "/Users/aberenyi/git/algo/Coursera Algo/input/algo1/assign3/kargerMinCut2.txt",
                        g);
        Assert.assertEquals(200, g.getVertexCount());
        int edgeCount = 0;
        for (Integer vertexLabel : dm.keySet()) {
            Assert.assertEquals(dm.get(vertexLabel),
                    (Integer) g.getVertex(vertexLabel).getEdgeCount());
            edgeCount += dm.get(vertexLabel);
        }
        Assert.assertEquals(edgeCount / 2 /* 2517 */, g.getEdgeCount());

        for (Edge edge : g.getEdges()) {
            if (edge.vertex1.equals(edge.vertex2))
                Assert.fail("self loop " + edge);
        }

        String out = "/Users/aberenyi/git/algo/Coursera Algo/input/algo1/assign3/kargerMinCut2Out.txt";
        GraphManager.writeToAdjacencyList(g, out);
    }

    @Test
    public void testTimeReadSimpleGraph() {
        Graph g = new Graph();

        StopWatch st = new StopWatch();
        st.start();
        for (int i = 0; i < 1000; i++) {
            g.reset();
            Map<Integer, Integer> dm = GraphManager
                    .loadSimpleGraphFromAdjacencyList(
                            "/Users/aberenyi/git/algo/Coursera Algo/input/algo1/assign3/kargerMinCut2.txt",
                            g);
        }
        st.stop();
        System.out.println(" average read time millis: " + st.getTime() / 1000);

    }

    @Test
    public void testReadSimpleGraph2() {
        Graph g = new Graph();
        GraphManager
                .loadSimpleGraphFromAdjacencyList(
                        "/Users/aberenyi/git/algo/Coursera Algo/input/algo1/assign3/testAdjacencyList.txt",
                        g);
        Assert.assertEquals(4, g.getVertexCount());
        Collection<Edge> es = g.getEdges();
        for (Edge edge : es) {
            System.out.println(edge);
        }

        Assert.assertEquals(5, g.getEdgeCount());
    }

    @Test
    public void testWriteSimpleGraph2() {
        Graph g = new Graph();
        final String fileName = "/Users/aberenyi/git/algo/Coursera Algo/input/algo1/assign3/testAdjacencyList.txt";
        GraphManager.loadSimpleGraphFromAdjacencyList(fileName, g);
        String out = "/Users/aberenyi/git/algo/Coursera Algo/input/algo1/assign3/testAdjacencyListOut.txt";
        GraphManager.writeToAdjacencyList(g, out);
    }

    @Test
    public void testCountCargerEdges() {
        Set<String> connectivitySet = new TreeSet<String>();
        BufferedReader br = null;
        try {
            String fileName = "/Users/aberenyi/git/algo/Coursera Algo/input/algo1/assign3/kargerMinCut2.txt";
            FileReader fi = new FileReader(fileName);
            br = new BufferedReader(fi);
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] elements = line.split(" ");
                final int key1 = Integer.parseInt(elements[0]);
                for (int i = 1; i < elements.length; i++) {
                    final int key2 = Integer.parseInt(elements[i]);
                    String key;
                    if (key1 < key2) {
                        key = "" + key1 + ":" + key2;
                    } else {
                        key = "" + key2 + ":" + key1;
                    }
                    connectivitySet.add(key);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Assert.assertEquals(2517, connectivitySet.size());
    }

    @Test
    public void testSortKargerEdges() {
        SortedSet<Integer> connectivitySet = new TreeSet<Integer>();
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            String fileName = "/Users/aberenyi/git/algo/Coursera Algo/input/algo1/assign3/kargerMinCut2.txt";
            br = new BufferedReader(new FileReader(fileName));
            bw = new BufferedWriter(
                    new FileWriter(
                            "/Users/aberenyi/git/algo/Coursera Algo/input/algo1/assign3/kargerMinCut2Sorted.txt"));
            String line = null;
            while ((line = br.readLine()) != null) {
                connectivitySet.clear();
                String[] elements = line.split(" ");
                String line2 = elements[0];
                for (int i = 1; i < elements.length; i++) {
                    connectivitySet.add(Integer.parseInt(elements[i]));
                }
                for (Integer e : connectivitySet) {
                    line2 += " " + e;
                }
                bw.write(line2 + "\n");

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
            }
            try {
                bw.close();
            } catch (IOException e) {
            }
        }
    }

}
