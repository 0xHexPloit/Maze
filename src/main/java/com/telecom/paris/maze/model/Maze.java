package com.telecom.paris.maze.model;

import com.telecom.paris.maze.model.box.BaseMazeBox;
import com.telecom.paris.graph.Graph;

import java.io.Serializable;

public interface Maze extends Graph, MazeModel, Serializable {
    /**
     *  This method permits to change the type of a box in the maze at the specified position.
     * @param xPosition the x position of the box to change
     * @param yPosition the y position of the box to change
     * @param box the new type of the box
     */
    public void changeBoxAtPosition(int xPosition, int yPosition, final BaseMazeBox box);


    /**
     *  Given the fact that a shortest path has been computed between two boxes of the maze, this method permits
     *  to determine if another box is part of the shortest path or not.
     * @param box the box to check
     * @return true if the box is part of the shortest path, false otherwise
     */
    public boolean doesMazeBoxBelongsToShortestPath(final BaseMazeBox box);
}
