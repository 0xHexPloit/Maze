package com.telecom.maze.model.box;

import com.telecom.maze.model.Maze;

public class EmptyBox extends MazeBox implements AccessibleBox {
    public EmptyBox(Maze maze, int xPosition, int yPosition) {
        super(maze, xPosition, yPosition);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
