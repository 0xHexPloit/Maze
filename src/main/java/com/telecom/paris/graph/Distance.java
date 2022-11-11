package com.telecom.paris.graph;

import com.telecom.paris.maze.model.exceptions.NotAdjacentVerticesException;

public interface Distance {
    /**
     * This method return the weight of the edge between two different vertices of the graph.
     *
     * @param vertexOne One of the vertices of the edge.
     * @param vertexTwo Another vertex of the edge.
     * @return The weight of the edge.
     *
     * @throws NotAdjacentVerticesException In case the two vertices are not adjacent an exception is thrown.
     */
    int getEdgeWeight(final Vertex vertexOne, final Vertex vertexTwo) throws NotAdjacentVerticesException;
}
