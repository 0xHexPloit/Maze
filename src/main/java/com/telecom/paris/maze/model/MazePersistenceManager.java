package com.telecom.paris.maze.model;

import com.telecom.paris.maze.model.MazeModel;

import java.io.IOException;

public interface MazePersistenceManager {
    /**
     * This method permits load a maze from a file.
     *
     * @param mazeId The id of the maze to load.
     * @return The maze loaded from the file.
     * @throws IOException An exception is thrown if an error occurs while loading the maze.
     */
    MazeModel read(String mazeId ) throws IOException;

    /**
     * This method permits to write a maze to a storage medium.
     *
     * @param mazeModel The maze to save.
     * @throws IOException This kind of exception can be thrown if the maze cannot be saved.
     */
    void persist( MazeModel mazeModel ) throws IOException;

    /**
     * This method permits to delete a maze from a storage system.
     *
     * @param mazeModel The maze to delete.
     * @return A boolean indicating if the maze has been deleted or not.
     * @throws IOException An exception can be thrown if the maze cannot be deleted.
     */
    public boolean delete(MazeModel mazeModel) throws IOException;
}
