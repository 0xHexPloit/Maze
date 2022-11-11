package com.telecom.paris.maze.model.box;

import com.telecom.paris.graph.Vertex;

import java.io.Serializable;

public interface MazeBox extends Vertex, MazeBoxModel, Serializable {
    /**
     * Cette méthode permet d'obtenir la position de la case sur l'axe des abscisses.
     *
     * @return la position de la case sur l'axe des abscisses.
     */
    public int getHorizontalPosition();

    /**
     * Cette méthode permet d'obtenir la position de la case sur l'axe des ordonnées.
     *
     * @return la position de la case sur l'axe des ordonnées.
     */
    public int getVerticalPosition();

    /**
     * Cette méthode permet de savoir si deux cases sont voisines.
     * @param box la case à tester.
     * @return un booléen indiquant si les deux cases sont voisines.
     */
    public boolean isNeighbourOf(final MazeBox box);
}
