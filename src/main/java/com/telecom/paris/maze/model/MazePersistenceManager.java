package com.telecom.paris.maze.model;

import com.telecom.paris.maze.model.MazeModel;

import java.io.IOException;

public interface MazePersistenceManager {
    /**
     * Cette méthode permet de lire un labyrinthe depuis un support de stockage à partir de son identifiant.
     *
     * @param mazeId L'identifiant du labyrinthe à charger
     * @return Une instance de labyrinthe
     * @throws IOException Une exception peut être levée si l'instance de labyrinthe n'a pas pu être créée correctement.
     */
    MazeModel read(String mazeId ) throws IOException;

    /**
     * Cette méthode permet de sauvegarder une instance de Maze dans un un support de stockage quelconque
     * @param mazeModel L'instance du labyrinthe à sauvegarder
     * @throws IOException Cette méthode peut lever une exception si on n'est pas capable de sauvegarder l'instance
     * de labyrinthe dans le support de stockage.
     */
    void persist( MazeModel mazeModel ) throws IOException;

    /**
     * Cette méthode permet de supprimer un labyrinthe contenu dans un support de stockage.
     *
     * @param mazeModel L'instance de labyrinthe à supprimer.
     * @return Un booléen indiquant si la suppression c'est faite avec succès.
     * @throws IOException Une exception peut être levée si un événement inattendu intervient lors de la tentative
     * de suppression du labyrinthe.
     */
    public boolean delete(MazeModel mazeModel) throws IOException;
}
