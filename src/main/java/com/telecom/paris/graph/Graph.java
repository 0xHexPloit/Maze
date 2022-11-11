package com.telecom.paris.graph;

import java.util.Set;

public interface Graph extends Distance {
    /**
     * This method returns all the vertices that are adjacent to the given vertex.
     *
     * @param vertex One of the vertices of the edge.
     * @return A set of all the vertices that are adjacent to the given vertex.
     */
    Set<Vertex> getSuccessors(final Vertex vertex);

    /**
     * This method permits to retrieve one of the vertices of the graph based on its label.
     *
     * @param label The label of the vertex to retrieve.
     * @return A vertex with the given label or null if no vertex with the given label exists.
     */
    Vertex getVertex( String label );


    /**
     * This method returns all the vertices of the graph.
     *
     * @return A set of all the vertices of the graph.
     */
    Set<Vertex> getVertexes();
}
