package com.aberenyi.graph;

import java.util.TreeSet;

public class SimpleGraph extends Graph {
    
    /**
     * Construct a graph that ensures via an Edge.ParallelEdgeComparator
     * that no parallel edges are added to the Graph.
     * 
     * @see TreeSet
     */
    public SimpleGraph() {
        super(true);
    }
    
    
}
