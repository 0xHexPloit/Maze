package com.telecom.maze.model.solver;

import com.telecom.maze.model.graph.Vertex;

import java.util.Set;

public interface MinDistance {
    /**
     * Cette méthode retourne la distance minimale entre le sommet 'startVertex' et le sommet 'vertex'.
     * @param vertex Un sommet du graphe
     * @return La distance minimale entre le sommet 'startVertex' et le sommet 'vertex'
     */
    int getMinDistance(final Vertex vertex);

    /**
     * Cette méthode permet de définir la distance minimale entre 'startVertex' et 'vertex'.
     *
     * @param vertex Un sommet du graphe
     * @param minimalDistance La distance minimale reliant 'startVertex' et 'vertex'.
     */
    void setMinDistance(final Vertex vertex, int minimalDistance);

    /**
     * Cette méthode permet de récupérer le vertex avec la distance minimale qui n'a pas encore été traité.
     *
     * @param processedVertexesSet L'ensemble des sommets qui ont été
     * @param vertexes L'ensemble des sommets du graphe
     *
     * @return Le prochain pivot
     */
    Vertex getMinDistanceVertex(
            final ProcessedVertexesSet processedVertexesSet,
            final Set<Vertex> vertexes
    );
}
