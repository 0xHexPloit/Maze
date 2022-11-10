package com.telecom.paris.graph.shortestpath;

import com.telecom.paris.graph.Vertex;

import java.util.HashSet;
import java.util.Set;

public final class BaseProcessedVertexesSet implements ProcessedVertexesSet {
    private final Set<Vertex> processedVertexes = new HashSet<>();

    @Override
    public void addVertex(final Vertex vertex) {
        this.processedVertexes.add(vertex);
    }

    @Override
    public boolean contains(final Vertex vertex) {
        return this.processedVertexes.contains(vertex);
    }
}
