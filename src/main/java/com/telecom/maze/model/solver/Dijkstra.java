package com.telecom.maze.model.solver;

import com.telecom.maze.model.NotAdjacentVerticesException;
import com.telecom.maze.model.graph.Distance;
import com.telecom.maze.model.graph.Graph;
import com.telecom.maze.model.graph.Vertex;

public class Dijkstra {
    public static ShortestPaths dijkstra(
            final Graph graph,
            final Vertex startVertex,
            final Vertex endVertex,
            final Distance distance
    ) {
        ProcessedVertexesSet processedVertexesSet = new BaseProcessedVertexesSet();
        MinDistance minDistance = new BaseMinDistance();

        Vertex pivotVertex = startVertex;
        minDistance.setMinDistance(startVertex, 0);

        for (Vertex vertex: graph.getVertexes()) {
            if (!vertex.equals(startVertex)) {
                minDistance.setMinDistance(vertex, Integer.MAX_VALUE);
            }
        }

        ShortestPaths shortestPaths = new BaseShortestPath();

        while (!processedVertexesSet.contains(endVertex)) {
            int minDistancePivot = minDistance.getMinDistance(pivotVertex);

            for (Vertex successor: pivotVertex.getSuccessors()) {
                if (!processedVertexesSet.contains(successor)) {
                    int minDistanceSuccessor = minDistance.getMinDistance(successor);
                    try{
                        int distancePivotSuccessor = distance.getEdgeWeight(pivotVertex, successor);
                        if (minDistancePivot + distancePivotSuccessor < minDistanceSuccessor) {
                            minDistance.setMinDistance(
                                    successor,
                                    minDistancePivot + distancePivotSuccessor
                            );
                            shortestPaths.setPreviousVertexFor(successor, pivotVertex);
                        }
                    } catch (NotAdjacentVerticesException e) {
                        // This kind of exception should never happen.
                        System.out.println(e.getMessage());
                        System.exit(1);
                    }
                }
            }
            processedVertexesSet.addVertex(pivotVertex);
            pivotVertex = minDistance.getMinDistanceVertex(processedVertexesSet, graph.getVertexes());

        }

        return shortestPaths;
    }
}
