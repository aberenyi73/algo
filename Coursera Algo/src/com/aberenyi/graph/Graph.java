package com.aberenyi.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Graph {

    /** label to Vertex */
    protected Map<Integer, Vertex> vertexMap;

    /** HashCode to Edge */
    protected Map<Edge, Edge> edgeMap;
    protected EdgeComparator edgeComparator;

    /**
     * Used to manipulate graph behavior at loading time. For example,
     * if the comparator evaluates two edges as parallel then it won't
     * be added to the HashMap.
     * 
     * @author aberenyi
     *
     */
    public static class EdgeComparator implements Comparator<Edge> {

        private Comparator<Edge> myComparator;

        /**
         * @param myComparator the myComparator to set
         */
        public void setMyComparator(Comparator<Edge> myComparator) {
            this.myComparator = myComparator;
        }

        @Override
        public int compare(Edge o1, Edge o2) {
            return myComparator.compare(o1, o2);
        }

    }

    public Graph() {
        this(true);
    }

    public void setSimple(boolean isSimple) {
        if (isSimple) {
            // edgeComparator.myComparator = new Edge.ParallelEdgeComparator();
            edgeComparator.myComparator = new Edge.KeyComparator();
        } else
            edgeComparator.myComparator = new Edge.HashCodeComparator();
        Edge.comparator = edgeComparator.myComparator;
    }

    /**
     * 
     */
    public Graph(boolean isSimple) {
        edgeComparator = new EdgeComparator();
        setSimple(isSimple);

        vertexMap = Collections.synchronizedMap(new TreeMap<Integer, Vertex>());
        edgeMap = Collections.synchronizedMap(new TreeMap<Edge, Edge>(
                edgeComparator));
    }

    public Vertex createVertex(Integer label) {
        return new Vertex(label, edgeComparator);
    }

    public Vertex getVertex(Integer label) {
        return vertexMap.get(label);
    }

    public Vertex getCreateAddVertex(Integer label) {
        Vertex v = getVertex(label);
        if (v == null) {
            v = new Vertex(label, edgeComparator);
            addVertex(v);
        }
        return v;
    }

    public Vertex getVertex(Vertex label) {
        return vertexMap.get(label.getLabel());
    }

    public boolean containsVertex(Vertex vertex) {
        return vertexMap.containsValue(vertex);
    }

    public boolean containsVertex(Integer vertex) {
        return vertexMap.containsKey(vertex);
    }

    /**
     * Adds the specified vertex to this set if it is not already present. More
     * formally, adds the specified element <tt>e</tt> to this set if the set
     * contains no element <tt>e2</tt> such that
     * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equals(e2))</tt>. If this
     * set already contains the element, the call leaves the set unchanged and
     * returns <tt>false</tt>.
     * 
     * @param vertex
     * @return
     */
    public Vertex addVertex(Vertex vertex) {
        if (vertexMap.containsKey(vertex.getLabel())) {
            return vertex;
        } else {
            vertexMap.put(vertex.getLabel(), vertex);
            return null;
        }
    }

    /**
     * Remove the vertex from the Graph, and all incident edges to it.
     * 
     * @param vertex
     */
    public void removeVertex(Vertex vertex) {
        Vertex b = vertexMap.remove(vertex.getLabel());
        if (b != null) {
            Collection<Edge> es = vertex.getEdges();
            for (Edge edge : es) {
                removeEdge(edge);
            }
        }
    }

    public boolean containsEdge(Edge edge) {
        return edgeMap.containsKey(edge);
    }
    
    
    public boolean containsEdgeConnecting(Vertex v1, Vertex v2) {
        Vertex v_1 = vertexMap.get(v1.getLabel());
        if(v_1 == null)
            return false;
        Vertex v_2 = vertexMap.get(v1.getLabel());
        if(v_2 == null) {
            return false;
        }
        if(v_1.isConnected(v_2))
            return true;
        return false;
    }
    
    
    /**
     * 
     * @param edge
     * @see Map#put()
     * @return the previous value associated with key, or null if there was no
     *         mapping for key.
     */
    public Edge addEdge(Edge edge) {
        Edge e = edgeMap.get(edge);
        if (e != null)
            return e;
        else {
            edgeMap.put(edge, edge);
            return null;
        }
    }

    public void removeEdge(Edge edge) {
        edgeMap.remove(edge);
    }

    public int getEdgeCount() {
        return edgeMap.size();
    }

    public Collection<Edge> getEdges() {
        return edgeMap.values();
    }

    /**
     * @return the vertexSet
     */
    public Collection<Vertex> getVertices() {
        return vertexMap.values();
    }

    public int getVertexCount() {
        return vertexMap.size();
    }

    public String printS() {
        String s = "E=";
        for (Edge edge : getEdges()) {
            s += " {" + edge.vertex1.getLabel() + "," + edge.vertex2.getLabel()
                    + "}";
        }
        return s;
    }

}
