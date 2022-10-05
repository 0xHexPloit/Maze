package com.telecom.graph;

public interface ProcessedVertexesSet {
    /**
     * Cette fonction permet de rajouter un sommet à l'ensemble des sommets déjà parcourus.
     * Attention, si le sommet est déjà présent,il ne sera pas rajouté.
     *
     * @param vertex Un sommet d'un graphe.
     */
    void addVertex(Vertex vertex);


    /**
     * Cette fonction permet de déterminer si un sommet à déjà été parcouru
     * @param vertex Un sommet d'un graphe
     * @return Un booléen indiquant si le sommet vertex a déjà été parcouru ou non
     */
    boolean contains(Vertex vertex);
}
