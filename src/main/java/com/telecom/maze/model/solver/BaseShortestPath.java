package com.telecom.maze.model.solver;

import com.telecom.maze.model.graph.Vertex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BaseShortestPath implements ShortestPaths {
    private final Map<Vertex, Vertex> predecessors = new HashMap<>();

    @Override
    public List<Vertex> getShortestPathBetween(final Vertex src, final Vertex dst) {
        List<Vertex> path = new LinkedList<>();
        path.add(0, dst);
        boolean srcFound = false;
        Vertex currentVertex = dst;

        while ((currentVertex = predecessors.get(currentVertex)) != null) {
            path.add(0, currentVertex);
            if (currentVertex.equals(src)) {
                srcFound = true;
                break;
            }
        }

        if (!srcFound) {
            path.clear();
        }

        return path;
    }

    @Override
    public void setPreviousVertexFor(final Vertex successor, final Vertex predecessor) {
        this.predecessors.put(successor, predecessor);
    }
}
