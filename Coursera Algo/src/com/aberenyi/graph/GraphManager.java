package com.aberenyi.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class GraphManager {

    private static final String DELIMITER = " ";

    /**
     * Reads a text file with edges. It has the format <br>
     * [number_of_nodes] [number_of_edges] <br>
     * [one_node_of_edge_1] [other_node_of_edge_1] [edge_1_cost] <br>
     * [one_node_of_edge_2] [other_node_of_edge_2] [edge_2_cost] ... <br>
     * For example, the third line of the file is "2 3 -8874", indicating that
     * there is an edge connecting vertex #2 and vertex #3 that has cost -8874.
     * You should NOT assume that edge costs are positive, nor should you assume
     * that they are distinct.
     * 
     * @param fileName The path to the file.
     * @return a List of Edge objects, whose vertices are "disconnected", that
     *         is, each edge will have distinct Vertex object for end points
     *         even if they have the same label.
     */
    public static List<Edge> readEdgeList(String fileName) {
        List<Edge> answer = new ArrayList<Edge>();
        BufferedReader br = null;
        try {
            FileReader fi = new FileReader(fileName);
            br = new BufferedReader(fi);
            String line = null;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first) {
                    first = false;
                    continue;
                }
                String[] elements = line.split(DELIMITER);
                int node1 = Integer.parseInt(elements[0]);
                int node2 = Integer.parseInt(elements[1]);
                int cost = Integer.parseInt(elements[2]);
                Edge edge = new Edge(node1, node2, cost);
                answer.add(edge);
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
        return answer;
    }

    /**
     * Reads a text file with edges. It has the format <br>
     * [number_of_nodes] [number_of_edges]<br>
     * [one_node_of_edge_1] [other_node_of_edge_1] [edge_1_cost] <br>
     * [one_node_of_edge_2] [other_node_of_edge_2] [edge_2_cost] ... <br>
     * For example, the third line of the file is "2 3 -8874", indicating that
     * there is an edge connecting vertex #2 and vertex #3 that has cost -8874.
     * You should NOT assume that edge costs are positive, nor should you assume
     * that they are distinct.
     * 
     * @param fileName The path to the file.
     * @return The Graph with adjacency list initialized.
     */
    public static Graph readGraph(String fileName) {
        Graph g = new Graph();
        BufferedReader br = null;
        try {
            FileReader fi = new FileReader(fileName);
            br = new BufferedReader(fi);
            String line = null;
            boolean first = true;
            int edgeKey = 0;
            while ((line = br.readLine()) != null) {
                if (first) {
                    first = false;
                    continue;
                }
                String[] elements = line.split(DELIMITER);
                int vertex1 = Integer.parseInt(elements[0]);
                Vertex v1 = new Vertex(vertex1);
                if (!g.containsVertex(vertex1)) {
                    g.addVertex(v1);
                }
                int vertex2 = Integer.parseInt(elements[1]);
                Vertex v2 = new Vertex(vertex2);
                if (!g.containsVertex(v2)) {
                    g.addVertex(v2);
                }
                int cost = Integer.parseInt(elements[2]);
                Edge edge = new Edge(v1, v2, cost);
                edge.setIntKey(edgeKey++);
                g.addEdge(edge);
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
        return g;
    }

    /**
     * Read a simple graph in from an adjacency list.
     * 
     * @param fileName The file contains the adjacency list representation of a
     *            simple undirected graph. There are 200 vertices labeled 1 to
     *            200. The first column in the file represents the vertex label,
     *            and the particular row (other entries except the first column)
     *            tells all the vertices that the vertex is adjacent to. So for
     *            example, the 6th row looks like :
     *            "6   155 56  52  120 ......". This just means that the vertex
     *            with label 6 is adjacent to (i.e., shares an edge with) the
     *            vertices with labels 155,56,52,120,......,etc
     * @param isSimple If true, assume that the input represents a simple map.
     * 
     * @return The graph with edges and vertices connected, no self-loops or
     *         double edges allowed, all edge weights are 1.
     */
    public static Graph readSimpleGraphFromAdjacencyList(String fileName, boolean isSimple) {
        Graph g = new Graph();
        BufferedReader br = null;
        try {
            FileReader fi = new FileReader(fileName);
            br = new BufferedReader(fi);
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] elements = line.split(DELIMITER);
                // The first column always has a new vertex that we haven't seen
                Vertex v1 = g.getCreateAddVertex((Integer.parseInt(elements[0])));
                if (elements.length == 1) {
                    g.addVertex(v1);
                    continue;
                }
                for (int i = 1; i < elements.length; i++) {
                    Vertex v2 = g.getCreateAddVertex((Integer.parseInt(elements[i])));
                    Edge edge = new Edge(v1, v2);
                    g.addEdge(edge);
                    //String s = g.printS();
                    //System.out.println(s);
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
        return g;
    }
    
    /**
     * Read a simple graph in from an adjacency list.
     * 
     * @param fileName The file contains the adjacency list representation of a
     *            simple undirected graph. There are 200 vertices labeled 1 to
     *            200. The first column in the file represents the vertex label,
     *            and the particular row (other entries except the first column)
     *            tells all the vertices that the vertex is adjacent to. So for
     *            example, the 6th row looks like :
     *            "6   155 56  52  120 ......". This just means that the vertex
     *            with label 6 is adjacent to (i.e., shares an edge with) the
     *            vertices with labels 155,56,52,120,......,etc
     * 
     * @return The degree of each vertex in the file.
     */
    public static Map<Integer,Integer> loadSimpleGraphFromAdjacencyList(String fileName, Graph g) {
        Map<Integer,Integer> vertexDegree = new HashMap<Integer, Integer>();
        Set<String> connectivitySet = new TreeSet<String>();
        int edgeKey = 0;
        BufferedReader br = null;
        try {
            FileReader fi = new FileReader(fileName);
            br = new BufferedReader(fi);
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] elements = line.split(DELIMITER);
                
                final int key1 = Integer.parseInt(elements[0]);
                Vertex v1 = g.getCreateAddVertex(key1);
                vertexDegree.put(key1, elements.length -1);
                if (elements.length == 1) {
                    g.addVertex(v1);
                    continue;
                }
                for (int i = 1; i < elements.length; i++) {
                    final int key2 = Integer.parseInt(elements[i]);
                    Vertex v2 = g.getCreateAddVertex(key2);
                    String key = Edge.getUndirectedConnectivityString(v1, v2);
                    if(key1 == key2)
                        System.out.println("self loop: " + key1);
                    if(!connectivitySet.contains(key) && (key1 != key2)) {
                        Edge edge = new Edge(edgeKey++, v1, v2);
                        edge.setIntKey(edgeKey++);
                        connectivitySet.add(key);
                        g.addEdge(edge);
                    }
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
        return vertexDegree;
    }
    
    public static void writeToAdjacencyList(Graph g, String fileName) {
        BufferedWriter br = null;
        try {
            br = new BufferedWriter(new FileWriter(fileName));
            // sort by vertex label
            SortedSet<Vertex> ss = new TreeSet<Vertex>(g.getVertices());
            for (Vertex vertex : ss) {
                br.write(vertex.getLocalCluster() + "\n");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    public static String toAdjacencyList(Graph g) {
        String s = "";
        SortedSet<Vertex> ss = new TreeSet<Vertex>(g.getVertices());
        for (Vertex vertex : ss) {
            s += vertex.getLocalCluster() + "\n";
        }
        return s;
    }
    
    
}
