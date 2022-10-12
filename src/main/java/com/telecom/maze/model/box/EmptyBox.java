package com.telecom.maze.model.box;

import com.telecom.maze.model.BaseMaze;

public class EmptyBox extends MazeBox implements AccessibleBox {
    public EmptyBox(BaseMaze maze, int xPosition, int yPosition) {
        super(maze, xPosition, yPosition);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
