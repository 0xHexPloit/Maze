package com.telecom.graph;

import java.util.Set;

public interface Graph extends Distance {
    /**
     * Cette méthode permet de retourner l'ensemble des sommets connectés au sommet 'vertex' (par l'intermédiaire d'une
     * arrête).
     *
     * @param vertex Un sommet du graphe.
     * @return Une liste de sommets connectés à 'vertex'.
     */
    Set<Vertex> getNeighboursForVertex(Vertex vertex);


    /**
     * Cette permet permet de retourner tous les sommets contenus dans le graphe.
     *
     * @return Les sommets du graphe.
     */
    Set<Vertex> getVertexes();
}
