package com.telecom.paris.maze.model.box;

import com.telecom.paris.maze.model.Maze;

public final class DepartureBox extends MazeBox implements AccessibleBox {
    public DepartureBox(Maze maze, int xPosition, int yPosition) {
        super(maze, xPosition, yPosition);
    }

    @Override
    public boolean isDeparture() {
        return true;
    }
}
