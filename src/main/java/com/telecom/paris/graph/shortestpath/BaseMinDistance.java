package com.telecom.paris.graph.shortestpath;

import com.telecom.paris.graph.Vertex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class BaseMinDistance implements MinDistance {
    private final Map<Vertex, Integer> minDistances = new HashMap<>();
    @Override
    public int getMinDistance(final Vertex vertex) {
        return this.minDistances.get(vertex);
    }

    @Override
    public void setMinDistance(final Vertex vertex, int minimalDistance) {
        this.minDistances.put(vertex, minimalDistance);
    }

    @Override
    public Vertex getMinDistanceVertex(final ProcessedVertexesSet processedVertexesSet,final Set<Vertex> vertexes) {
        // First of all, we should sort the vertexes by their distance to the start vertex
        List<Vertex> sortedVertexes = this.minDistances
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .toList();

        for (Vertex vertex: sortedVertexes) {
            if (!processedVertexesSet.contains(vertex)) {
                return vertex;
            }
        }

        return null;
    }
}
