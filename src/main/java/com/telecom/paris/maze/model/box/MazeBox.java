package com.telecom.paris.maze.model.box;

import com.telecom.paris.graph.Vertex;

import java.io.Serializable;

public interface MazeBox extends Vertex, MazeBoxModel, Serializable {
    /**
     * This method gives the x position of the box in the maze.
     *
     * @return The x position of the box in the maze.
     */
    public int getHorizontalPosition();

    /**
     * This method gives the y position of the box in the maze.
     *
     * @return The y position of the box in the maze.
     */
    public int getVerticalPosition();

    /**
     * This method permits to test if a box is adjacent to another box.
     * @param box The box for which we want to know if it is adjacent to the current box.
     * @return A boolean indicating if the box is adjacent to the current box or not.
     */
    public boolean isNeighbourOf(final MazeBox box);
}
