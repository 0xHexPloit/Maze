package com.telecom.maze.model.box;

import com.telecom.maze.model.BaseMaze;

public class WallBox extends MazeBox {
    public WallBox(BaseMaze maze, int xPosition, int yPosition) {
        super(maze, xPosition, yPosition);
    }

    @Override
    public boolean isWall() {
        return true;
    }
}
