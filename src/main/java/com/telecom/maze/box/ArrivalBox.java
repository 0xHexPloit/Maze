package com.telecom.maze.box;

import com.telecom.maze.Maze;

public class ArrivalBox extends MazeBox implements AccessibleBox {
    public ArrivalBox(Maze maze, int xPosition, int yPosition) {
        super(maze, xPosition, yPosition);
    }
}
