package com.telecom.graph;

public class Dikjstra {
    public static ShortestPaths dijkstra(
            Graph graph,
            Vertex startVertex,
            Vertex endVertex,
            ProcessedVertexesSet processedVertexesSet,
            MinDistance minDistance,
            Distance distance
    ) {
        processedVertexesSet.addVertex(startVertex);

        Vertex pivotVertex = startVertex;
        minDistance.setMinDistance(startVertex, 0);

        for (Vertex vertex: graph.getVertexes()) {
            if (!vertex.equals(startVertex)) {
                minDistance.setMinDistance(vertex, Integer.MAX_VALUE);
            }
        }

        ShortestPaths shortestPaths = null;

        while (!processedVertexesSet.contains(endVertex)) {
            int minDistancePivot = minDistance.getMinDistance(pivotVertex);

            for (Vertex successor: pivotVertex.getSuccessors()) {
                if (!processedVertexesSet.contains(successor)) {
                    int minDistanceSuccessor = minDistance.getMinDistance(successor);
                    int distancePivotSuccessor = distance.getDistanceBetween(pivotVertex, successor);

                    if (minDistancePivot + distancePivotSuccessor < minDistanceSuccessor) {
                        minDistance.setMinDistance(
                                successor,
                                minDistancePivot + distancePivotSuccessor
                        );
                        shortestPaths.setPreviousVertexFor(successor, pivotVertex);
                    }
                }
            }

            pivotVertex = minDistance.getMinDistanceVertex(processedVertexesSet, graph.getVertexes());
            processedVertexesSet.addVertex(pivotVertex);
        }

        return shortestPaths;
    }
}
