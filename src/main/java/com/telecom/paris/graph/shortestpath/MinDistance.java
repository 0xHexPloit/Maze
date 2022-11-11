package com.telecom.paris.graph.shortestpath;

import com.telecom.paris.graph.Vertex;

import java.util.Set;

public interface MinDistance {
    /**
     * This method permits to retrieve the minimum distance between the source vertex and the given vertex.
     *
     * @param vertex One of the vertices of the edge.
     * @return The minimum distance between the source vertex and the given vertex.
     */
    int getMinDistance(final Vertex vertex);

    /**
     * This method permits to set the minimum distance between the source vertex and the given vertex.
     *
     * @param vertex One of the vertices of the edge.
     * @param minimalDistance The minimum distance between the source vertex and the given vertex.
     */
    void setMinDistance(final Vertex vertex, int minimalDistance);

    /**
     * This method permits to retrieve the vertex that is the closest to the source vertex and
     * that has not been processed yet.
     *
     * @param processedVertexesSet The set of already processed vertices.
     * @param vertexes One of the vertices of the edge.
     *
     * @return The next vertex to process in the shortest path algorithm.
     */
    Vertex getMinDistanceVertex(
            final ProcessedVertexesSet processedVertexesSet,
            final Set<Vertex> vertexes
    );
}
