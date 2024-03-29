package com.aberenyi.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Edge implementation that allows exactly two end points.
 * 
 * @author aberenyi
 * 
 */
public class Edge implements Comparable<Edge> {

    public static final int DEFAULT_COST = 1;
    Vertex vertex1;
    Vertex vertex2;
    int cost;
    public static Comparator<Edge> comparator = new IntKeyComparator();
    Integer intKey;

    /**
     * Compare two edges purely based on their cost.
     */
    public static class EdgeCostComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            if (o1.cost < o2.cost)
                return -1;
            if (o1.cost > o2.cost)
                return 1;
            return 0;
        }
    }

    /**
     * Compare two Edges, if their end points are the same vertices they are
     * equal. Otherwise order them by hashcode for performance.
     */
    public static class IntKeyComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge e1, Edge e2) {
            if (e1 == e2)
                return 0;
            return e1.intKey.compareTo(e2.intKey);
        }
    }

    /**
     * @return the intKey
     */
    public Integer getIntKey() {
        return intKey;
    }

    /**
     * @param intKey the intKey to set
     */
    public void setIntKey(Integer intKey) {
        this.intKey = intKey;
    }

    /**
     * @return the vertex1
     */
    public Vertex getVertex1() {
        return vertex1;
    }

    /**
     * @param vertex1 the vertex1 to set
     */
    public void setVertex1(Vertex vertex1) {
        this.vertex1 = vertex1;
    }

    public Collection<Vertex> getVertices() {
        List<Vertex> l = new ArrayList<Vertex>();
        l.add(vertex1);
        l.add(vertex2);
        return l;
    }

    /**
     * @return the vertex2
     */
    public Vertex getVertex2() {
        return vertex2;
    }

    /**
     * @param vertex2 the vertex2 to set
     */
    public void setVertex2(Vertex vertex2) {
        this.vertex2 = vertex2;
    }

    /**
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Utility ctor that creates two vertices from the passed in int.
     * 
     * @param vertex1
     * @param vertex2
     * @param cost
     */
    public Edge(int vertex1, int vertex2) {
        this(vertex1, vertex2, DEFAULT_COST);
    }

    /**
     * Utility ctor that creates two vertices from the passed in int.
     * 
     * @param vertex1
     * @param vertex2
     * @param cost
     */
    public Edge(int vertex1, int vertex2, int cost) {
        this(new Vertex(vertex1), new Vertex(vertex2), cost);
    }

    /**
     * @param node1
     * @param node2
     */
    public Edge(Vertex vertex1, Vertex vertex2) {
        this(vertex1, vertex2, DEFAULT_COST);
    }

    /**
     * Create an Edge that connects two Vertices and add this Edge to the
     * vertices. Adds this edge to the two vertices.
     * 
     * @param vertex1
     * @param vertex2
     * @param cost Edge cost
     */
    public Edge(Vertex vertex1, Vertex vertex2, int cost) {
        this.cost = cost;
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        vertex1.addEdge(this);
        vertex2.addEdge(this);
    }
    
    /**
     * Create an Edge that connects two Vertices and add this Edge to the
     * vertices. Adds this edge to the two vertices.
     * 
     * @param vertex1
     * @param vertex2
     * @param cost Edge cost
     */
    public Edge(int intKey, Vertex vertex1, Vertex vertex2, int cost) {
        this.intKey = intKey;
        this.cost = cost;
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        vertex1.addEdge(this);
        vertex2.addEdge(this);
    }

    public Edge(int intKey, Vertex v1, Vertex v2) {
        this(intKey, v1, v2, DEFAULT_COST);
    }

    /**
     * Test if this Edge has the same to endpoints as the other, regardless of
     * direction.
     * 
     * @param o2
     * @return
     */
    public boolean isParallel(Edge o2) {
        if ((vertex1.equals(o2.vertex1) && vertex2.equals(o2.vertex2))
                || (vertex1.equals(o2.vertex2) && vertex2.equals(o2.vertex1))) {
            return true;
        }
        return false;
    }

    /**
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Edge [v1=");
        builder.append(vertex1.getLabel());
        builder.append(", v2=");
        builder.append(vertex2.getLabel());
        builder.append(", cost=");
        builder.append(cost);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int compareTo(Edge o) {
        return this.intKey.compareTo(o.intKey);
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return intKey;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return intKey.equals(((Edge) obj).intKey);
    }

    /**
     * Get a label that indicates the connection between the two endpoints.
     * 
     * @return The vertex labels concatenated with a colon. The label with the
     *         smaller numerical value comes first.
     */
    public String getUndirectedConnectivityString() {
        return getUndirectedConnectivityString(vertex1, vertex2);
    }

    public static String getUndirectedConnectivityString(Vertex vertex1,
            Vertex vertex2) {
        String key;
        if (vertex1.getLabel() < vertex2.getLabel()) {
            key = "" + vertex1.getLabel() + ":" + vertex2.getLabel();
        } else {
            key = "" + vertex2.getLabel() + ":" + vertex1.getLabel();
        }
        return key;
    }

    public boolean isSelfLoop() {
        if(vertex1 == null || vertex2 != null)
            return false;
        if(vertex1 != null || vertex2 == null)
            return false;
        if(vertex1 == null && vertex2 == null)
            return true; // connecting null vertex
        return vertex1.equals(vertex2);
    }

}
