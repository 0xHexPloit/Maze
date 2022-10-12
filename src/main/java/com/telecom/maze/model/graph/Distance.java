package com.telecom.maze.model.graph;

public interface Distance {
    /**
     * Cette méthode permet de retourner le poids de l'arrête reliant 'vertexOne' à 'vertexTwo' (ou 'vertex two' à
     * 'vertexOne');
     *
     * @param vertexOne Un sommet du graphe
     * @param vertexTwo Un sommet du graphe différent de 'vertex one'
     * @return Le poids de l'arrête reliant les deux sommets du graphe.
     */
    int getEdgeWeight(final Vertex vertexOne, final Vertex vertexTwo);
}
