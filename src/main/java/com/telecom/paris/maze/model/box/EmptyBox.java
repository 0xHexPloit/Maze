package com.telecom.paris.maze.model.box;


import com.telecom.paris.maze.model.Maze;

public final class EmptyBox extends BaseMazeBox implements AccessibleBox {
    public EmptyBox(Maze maze, int xPosition, int yPosition) {
        super(maze, xPosition, yPosition);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
