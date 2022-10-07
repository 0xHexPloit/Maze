package com.telecom.maze.box;

import com.telecom.maze.Maze;

public class EmptyBox extends MazeBox implements AccessibleBox {
    public EmptyBox(Maze maze, int xPosition, int yPosition) {
        super(maze, xPosition, yPosition);
    }
}
