package com.telecom.maze.model.solver;

import com.telecom.maze.model.graph.Vertex;

import java.util.HashSet;
import java.util.Set;

public class BaseProcessedVertexesSet implements ProcessedVertexesSet {
    private final Set<Vertex> processedVertexes = new HashSet<>();

    @Override
    public void addVertex(Vertex vertex) {
        this.processedVertexes.add(vertex);
    }

    @Override
    public boolean contains(Vertex vertex) {
        return this.processedVertexes.contains(vertex);
    }
}
