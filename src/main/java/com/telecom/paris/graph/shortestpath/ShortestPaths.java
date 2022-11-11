package com.telecom.paris.graph.shortestpath;

import com.telecom.paris.graph.Vertex;

import java.util.List;

public interface ShortestPaths {
    /**
     * This method computes the shortest path between two vertices of the graph.
     *
     * @param src The source vertex.
     * @param dst The destination vertex.
     * @return A list of vertices representing the shortest path between the two given vertices.
     */
    List<Vertex> getShortestPathBetween(final Vertex src, final Vertex dst);

    /**
     * This method permits to define the predecessor of a vertex in the shortest path tree.
     *
     * @param successor One of the vertices of the edge.
     * @param predecessor The vertex that will precede the successor in the shortest path tree.
     */
    void setPreviousVertexFor(final Vertex successor, final Vertex predecessor);
}
