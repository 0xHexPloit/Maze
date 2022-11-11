package com.telecom.paris.graph.shortestpath;

import com.telecom.paris.graph.Vertex;

public interface ProcessedVertexesSet {
    /**
     * This method permits to add a vertex to the set of already processed vertices.
     *
     * @param vertex One of the vertices of the edge.
     */
    void addVertex(final Vertex vertex);


    /**
     * This methods permits to check if a vertex has already been processed.
     *
     * @param vertex One of the vertices of the edge.
     *
     * @return A boolean indicating if the vertex has already been processed.
     */
    boolean contains(final Vertex vertex);
}
