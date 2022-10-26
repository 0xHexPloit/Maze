package com.telecom.paris.graph;

import java.util.Set;

public interface Graph extends Distance {
    /**
     * Cette méthode permet de retourner l'ensemble des sommets connectés au sommet 'vertex' (par l'intermédiaire d'une
     * arrête).
     *
     * @param vertex Un sommet du graphe.
     * @return Une liste de sommets connectés à 'vertex'.
     */
    Set<Vertex> getSuccessors(final Vertex vertex);

    /**
     * Cette méthode permet de retourner un des noeuds du graphe en se basant sur son identifiant.
     * @param label L'identifiant du noeud.
     * @return Un noeud du graphe.
     */
    Vertex getVertex( String label );


    /**
     * Cette permet permet de retourner tous les sommets contenus dans le graphe.
     *
     * @return Les sommets du graphe.
     */
    Set<Vertex> getVertexes();
}
