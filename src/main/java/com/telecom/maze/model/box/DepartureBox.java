package com.telecom.maze.model.box;

import com.telecom.maze.model.Maze;

public class DepartureBox extends MazeBox implements AccessibleBox {
    public DepartureBox(Maze maze, int xPosition, int yPosition) {
        super(maze, xPosition, yPosition);
    }

    @Override
    public boolean isDeparture() {
        return true;
    }
}
