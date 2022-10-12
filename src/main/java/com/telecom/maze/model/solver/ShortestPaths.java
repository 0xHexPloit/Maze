package com.telecom.maze.model.solver;

import com.telecom.maze.model.graph.Vertex;

import java.util.List;

public interface ShortestPaths {
    /**
     * Cette méthode permet de retourner la liste des sommets qu'il faut parcourir pour relier 'startVertex' à
     * 'endVertex' le plus rapidement possible.
     *
     * @param src Le sommet de départ
     * @param dst Le sommet d'arrivée
     * @return Une liste de sommets.
     */
    List<Vertex> getShortestPathBetween(Vertex src, Vertex dst);

    /**
     * Cette méthode permet de définir le prédecesseur d'un sommet. Cela permettra de construire le plus court chemin
     * entre 'startVertex' et 'endVertex'.
     *
     * @param successor Un sommet du graphe.
     * @param predecessor Le dernier sommet permettant de relier 'startVertex' à 'vertex' dans une approche du plus
     *                    court chemin.
     */
    void setPreviousVertexFor(Vertex successor, Vertex predecessor);

    /**
     * Cette méthode permet de remettre à zéro les informations de plus court chemin. En d'autres termes,
     * pour chaque sommet du graphe, on retire son ancien prédecesseur.
     */
    void clear();
}
