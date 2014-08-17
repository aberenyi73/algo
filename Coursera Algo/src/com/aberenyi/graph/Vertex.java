package com.aberenyi.graph;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import com.aberenyi.graph.Graph.EdgeComparator;

/**
 * A.K.A Node that can be used in an undirected Graph. It has an integer label
 * and incident Edges upon it.
 * 
 * @author aberenyi
 * 
 */
public class Vertex implements Comparable<Vertex> {

    private EdgeComparator edgeComparator;
    private Integer label;

    /**
     * @param edgeComparator the edgeComparator to set
     */
    public void setEdgeComparator(EdgeComparator edgeComparator) {
        this.edgeComparator = edgeComparator;
    }

    /**
     * List of incident edges.
     */
    private Map<Edge, Edge> edges;

    /**
     * @return the label
     */
    public Integer getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(Integer label) {
        this.label = label;
    }

    /**
     * @return the edges
     */
    public Collection<Edge> getEdges() {
        return edges.values();
    }

    /**
     * @param edges the edges to set
     */
    public void setEdges(Collection<Edge> edges) {
        this.edges = new TreeMap<>(edgeComparator);
        for (Edge edge : edges) {
            this.edges.put(edge, edge);
        }
    }

    public void addEdge(Edge edge) {
        if (edges == null)
            edges = new TreeMap<>(edgeComparator);
        edges.put(edge, edge);
    }

    /**
     * @param label
     */
    public Vertex(Integer label) {
        this.label = label;
    }

    /**
     * @param label
     */
    public Vertex(Integer label, EdgeComparator edgeComparator) {
        this.label = label;
        this.edgeComparator = edgeComparator;
    }

    /**
     * Hash code generated based on the unique vertex label.
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return (label == null) ? 0 : label.hashCode();
    }

    /**
     * Check if two vertices have the same label.
     * 
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
        Vertex other = (Vertex) obj;
        if (label == null) {
            if (other.label != null)
                return false;
        } else if (!label.equals(other.label))
            return false;
        return true;
    }

    /**
     * Print out the label.
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Vertex [label=");
        builder.append(label);
        builder.append("]");
        return builder.toString();
    }

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Vertex o) {
        return label.compareTo(o.getLabel());
    }

    public int getEdgeCount() {
        if (edges == null)
            return 0;
        return edges.size();
    }

    public boolean hasEdges() {
        return getEdgeCount() > 0;
    }

    /**
     * Check if an edge connects this Vertex to the other. If the other is this
     * then its connected.
     * 
     * @param v
     * @return
     */
    public boolean isConnected(Vertex v) {
        if (edges == null)
            return false;
        for (Edge edge : edges.values()) {
            if ((edge.vertex1.label.equals(v.label) || edge.vertex2.label
                    .equals(v.label))) {
                return true;
            }
        }
        return false;
    }

}
