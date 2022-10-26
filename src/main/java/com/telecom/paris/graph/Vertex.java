package com.telecom.paris.graph;

import java.util.Set;

public interface Vertex {
    /**
     * Cette méthode renvoie le nom du sommet
     *
     * @return Le nom du sommet
     */
    String getLabel();

    /**
     * Cette méthode renvoie l'ensemble des sommets auxquels le sommet est connecté.
     *
     * @return L'ensemble des sommets auxquels le sommet est connecté.
     */
    Set<Vertex> getSuccessors();
}
