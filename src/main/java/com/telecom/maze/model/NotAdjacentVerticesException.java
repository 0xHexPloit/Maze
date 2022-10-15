package com.telecom.maze.model;

import com.telecom.maze.model.graph.Vertex;

public class NotAdjacentVerticesException extends Exception{
    public NotAdjacentVerticesException(Vertex v1, Vertex v2) {
        super(String.format("The vertices %s and %s are not adjacent", v1.getLabel(), v2.getLabel()));
    }
}