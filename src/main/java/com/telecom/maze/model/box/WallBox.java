package com.telecom.maze.model.box;

import com.telecom.maze.model.Maze;

public class WallBox extends MazeBox {
    public WallBox(Maze maze, int xPosition, int yPosition) {
        super(maze, xPosition, yPosition);
    }

    @Override
    public boolean isWall() {
        return true;
    }
}
