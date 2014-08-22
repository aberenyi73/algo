package com.aberenyi.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * A.K.A Node that can be used in an undirected Graph. It has an integer label
 * and incident Edges upon it.
 * 
 * @author aberenyi
 * 
 */
public class Vertex implements Comparable<Vertex>, Cloneable {

    private Integer label;

    /**
     * List of incident edges.
     */
    private Collection<Edge> edges;

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
        return edges;
    }
    
    /**
     * @param label
     */
    public Vertex(Integer label) {
        this.label = label;
    }

    public Collection<Vertex> getNeighboringVertices() {
        Set<Vertex> v = new TreeSet<Vertex>();
        for (Edge edge : getEdges()) {
            if (edge.getVertex1() != this)
                v.add(edge.getVertex1());
            if (edge.getVertex2() != this)
                v.add(edge.getVertex2());
        }
        return v;
    }

    /**
     * Get the sorted order of neighboring vertices, excluding this vertex.
     * 
     * @return
     */
    public String getNeighboringVerticesAsString() {
        String answer = "";
        for (Vertex v : getNeighboringVertices()) {
            answer += v.getLabel() + " ";
        }
        int i = answer.lastIndexOf(' ');
        if (i > 0)
            answer = answer.substring(0, answer.length() - 1);
        return answer;
    }
    
    /**
     * Same as getNeighboringVerticesAsString() but includes this vertex's 
     * label as the first item.
     * 
     * @return
     */
    public String getLocalCluster() {
        return "" + getLabel() + " " + getNeighboringVerticesAsString();
    }

    public void addEdge(Edge edge) {
        if (edges == null)
            edges = new ArrayList<Edge>();
        edges.add(edge);
    }
    
    public boolean removeEdge(Edge edge) {
        return getEdges().remove(edge);        
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
     * @param otherVertex
     * @return
     */
    public boolean isConnected(Vertex otherVertex) {
        if (edges == null)
            return false;
        for (Edge edge : edges) {
            if ((edge.vertex1.equals(otherVertex) || edge.vertex2
                    .equals(otherVertex))) {
                return true;
            }
        }
        return false;
    }

    /**
     * @see java.lang.Object#clone()
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    
}
