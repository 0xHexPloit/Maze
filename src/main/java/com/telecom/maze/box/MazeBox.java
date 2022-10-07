package com.telecom.maze.box;

import com.telecom.graph.Vertex;
import com.telecom.maze.Maze;

import java.util.Set;

public abstract class MazeBox implements Vertex {
    private final Maze maze;
    private final int xPosition;
    private final int yPosition;

    public MazeBox(Maze maze, int xPosition, int yPosition) {
        this.maze = maze;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public Maze getMaze() {
        return maze;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    @Override
    public String getLabel() {
        return String.format("(%d,%d)", this.xPosition, this.yPosition);
    }

    @Override
    public Set<Vertex> getSuccessors() {
        return this.getMaze().getSuccessors(this);
    }
}
