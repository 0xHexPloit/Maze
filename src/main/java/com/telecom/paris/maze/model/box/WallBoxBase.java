package com.telecom.paris.maze.model.box;

import com.telecom.paris.maze.model.Maze;

public final class WallBoxBase extends BaseMazeBox {
    public WallBoxBase(Maze maze, int xPosition, int yPosition) {
        super(maze, xPosition, yPosition);
    }

    @Override
    public boolean isWall() {
        return true;
    }
}
