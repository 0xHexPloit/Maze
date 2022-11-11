package com.telecom.paris.graph;

import java.util.Set;

public interface Vertex {
    /**
     * This method returns the label of the vertex.
     *
     * @return The label of the vertex.
     */
    String getLabel();

    /**
     * This method returns all the vertices that are adjacent to the given vertex.
     *
     * @return A set of all the vertices that are adjacent to the given vertex.
     */
    Set<Vertex> getSuccessors();
}
