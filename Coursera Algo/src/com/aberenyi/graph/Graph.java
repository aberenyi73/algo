package com.aberenyi.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Graph implements Cloneable {

    static Random RANDOM = new Random();

    /** label to Vertex */
    protected Map<Integer, Vertex> vertexMap;

    /** key to Edge */
    protected List<Edge> edgeList;

    /** key to Edge */
    protected Map<Edge, Edge> edgeMap;

    private final boolean isMapBacked = true;

    public Graph() {
        vertexMap = new TreeMap<Integer, Vertex>();
        if (isMapBacked)
            edgeMap = new HashMap<Edge, Edge>();
        else
            edgeList = new ArrayList<Edge>();

    }

    public void reset() {
        vertexMap.clear();
        if (isMapBacked)
            edgeMap.clear();
        else
            edgeList.clear();
        // RANDOM = new Random();
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
        return vertexMap.put(vertex.getLabel(), vertex);
    }

    public Vertex getVertex(Integer label) {
        return vertexMap.get(label);
    }

    public Vertex getVertex(Vertex vertex) {
        return vertexMap.get(vertex.getLabel());
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

    /**
     * Remove the vertex from the Graph, and all incident edges to it.
     * 
     * @param vertex
     */
    public void removeVertex(Vertex vertex) {
        Vertex b = vertexMap.remove(vertex.getLabel());
        /*
         * if (b != null) { Collection<Edge> es = vertex.getEdges(); for (Edge
         * edge : es) { removeEdge(edge); } }
         */
    }

    public Vertex getCreateAddVertex(Integer label) {
        Vertex v = getVertex(label);
        if (v == null) {
            v = new Vertex(label);
            addVertex(v);
        }
        return v;
    }

    public boolean containsVertex(Vertex vertex) {
        return vertexMap.containsValue(vertex);
    }

    public boolean containsVertex(Integer vertex) {
        return vertexMap.containsKey(vertex);
    }

    /**
     * Adds the Edge to the index position that the Edge's key has.
     * 
     * @param edge
     * @return the previous value associated with key, or null if there was no
     *         mapping for key.
     */
    public void addEdge(Edge edge) {
        if (isMapBacked)
            edgeMap.put(edge, edge);
        else
            edgeList.add(edge);
    }

    public Collection<Edge> getEdges() {
        if (isMapBacked)
            return edgeMap.values();
        else
            return edgeList;
    }

    public int getEdgeCount() {
        if (isMapBacked)
            return edgeMap.size();
        else
            return edgeList.size();
    }

    public Edge getRandomEdge() {
        final int edgeCount = getEdgeCount();
        if (edgeCount <= 0)
            return null;
        int r = RANDOM.nextInt(edgeCount);
        if (isMapBacked)
            return (Edge) edgeMap.values().toArray()[r];
        else
            return edgeList.get(r);
    }

    /**
     * Collapse the two vertices at the two end points of this Edge. Keep one of
     * the Edges (at vertex1) and re-wire the Edges incident to that Edge to
     * Vertex2. Remove edge from Graph and both vertices. Side note, it will
     * move self loops to the new vertex.
     * 
     * @param edge
     * @return the remaining Vertex.
     * @throws GraphException
     */
    public Vertex collapseEdge(Edge edge) {
        Vertex v1 = edge.getVertex1();
        Vertex v2 = edge.getVertex2();
        // avoid creating self-loop
        v1.removeEdge(edge);
        v2.removeEdge(edge);
        removeEdge(edge);

        // rewire v2's edges to point to v1
        Iterator<Edge> v2Iter = v2.getEdges().iterator();
        while (v2Iter.hasNext()) {
            Edge v2IncidentEdge = null;
            v2IncidentEdge = v2Iter.next();

            if (v2IncidentEdge.getVertex1().equals(v2)) {
                v2IncidentEdge.setVertex1(v1);
                if (!v2IncidentEdge.isSelfLoop())
                    v1.addEdge(v2IncidentEdge);
                v2Iter.remove();
                continue;
            }
            if (v2IncidentEdge.getVertex2().equals(v2)) {
                v2IncidentEdge.setVertex2(v1);
                if (!v2IncidentEdge.isSelfLoop())
                    v1.addEdge(v2IncidentEdge);
                v2Iter.remove();
                continue;
            }
        }
        removeVertex(v2);
        return v1;
    }

    private void removeEdge(Edge edge) {
        if (isMapBacked)
            edgeMap.remove(edge);
        else
            edgeList.remove(edge);
    }

    /**
     * Remove all edges from this vertex whose two end point both point to this
     * Vertex.
     * 
     * @param v
     */
    public void removeSelfLoops(Vertex v) {
        Iterator<Edge> ei = v.getEdges().iterator();
        while (ei.hasNext()) {
            Edge edge = ei.next();
            if (edge.getVertex1().equals(v) && edge.getVertex2().equals(v)) {
                ei.remove();
                removeEdge(edge);
            }
        }
    }

    public String printE() {
        String s = "E=";
        for (Edge edge : getEdges()) {
            s += " {" + edge.vertex1.getLabel() + "," + edge.vertex2.getLabel()
                    + "}";
        }
        return s;
    }

}
