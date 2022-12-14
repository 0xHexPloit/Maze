package com.telecom.paris.maze.model.box;

import com.telecom.paris.maze.model.Maze;

public final class ArrivalBox extends BaseMazeBox implements AccessibleBox {
    public ArrivalBox(Maze maze, int xPosition, int yPosition) {
        super(maze, xPosition, yPosition);
    }

    @Override
    public boolean isArrival() {
        return true;
    }
}
