package com.telecom.paris.graph.shortestpath;

import com.telecom.paris.maze.model.NotAdjacentVerticesException;
import com.telecom.paris.graph.Distance;
import com.telecom.paris.graph.Graph;
import com.telecom.paris.graph.Vertex;

public final class Dijkstra {
    public static ShortestPaths dijkstra(
            final Graph graph,
            final Vertex startVertex,
            final Vertex endVertex,
            final Distance distance
    ) {
        final ProcessedVertexesSet processedVertexesSet = new BaseProcessedVertexesSet();
        final MinDistance minDistance = new BaseMinDistance();

        Vertex pivotVertex = startVertex;
        minDistance.setMinDistance(startVertex, 0);

        for (Vertex vertex: graph.getVertexes()) {
            if (!vertex.equals(startVertex)) {
                minDistance.setMinDistance(vertex, Integer.MAX_VALUE);
            }
        }

        final ShortestPaths shortestPaths = new BaseShortestPath();

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
