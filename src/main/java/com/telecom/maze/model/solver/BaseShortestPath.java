package com.telecom.maze.model.solver;

import com.telecom.maze.model.graph.Vertex;

import java.util.*;

public class BaseShortestPath implements ShortestPaths {
    private final Map<Vertex, Vertex> predecessors = new HashMap<>();

    @Override
    public List<Vertex> getShortestPathBetween(Vertex src, Vertex dst) {
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
    public void setPreviousVertexFor(Vertex successor, Vertex predecessor) {
        this.predecessors.put(successor, predecessor);
    }
}
