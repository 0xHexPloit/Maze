package com.telecom.paris.maze.model.box;

import com.telecom.paris.maze.model.Maze;

public final class DepartureBoxBase extends BaseMazeBox implements AccessibleBox {
    public DepartureBoxBase(Maze maze, int xPosition, int yPosition) {
        super(maze, xPosition, yPosition);
    }

    @Override
    public boolean isDeparture() {
        return true;
    }
}
