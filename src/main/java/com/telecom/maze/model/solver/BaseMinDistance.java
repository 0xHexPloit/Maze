package com.telecom.maze.model.solver;

import com.telecom.maze.model.graph.Vertex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BaseMinDistance implements MinDistance {
    private final Map<Vertex, Integer> minDistances = new HashMap<>();
    @Override
    public int getMinDistance(Vertex vertex) {
        return this.minDistances.get(vertex);
    }

    @Override
    public void setMinDistance(Vertex vertex, int minimalDistance) {
        this.minDistances.put(vertex, minimalDistance);
    }

    @Override
    public Vertex getMinDistanceVertex(ProcessedVertexesSet processedVertexesSet, Set<Vertex> vertexes) {
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