package com.telecom.paris.maze.model.box;

import java.io.Serial;
import java.util.Set;

import com.telecom.paris.maze.model.Maze;
import com.telecom.paris.graph.Vertex;

public abstract class BaseMazeBox implements MazeBox  {
    @Serial
    private static final long serialVersionUID = 202211101416L;

    private final Maze maze;
    private final int xPosition;
    private final int yPosition;

    private final String label;

    public BaseMazeBox(final Maze maze, int xPosition, int yPosition) {
        this.maze = maze;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.label = String.format("(%d, %d)", xPosition, yPosition);
    }

    @Override
    public int getHorizontalPosition() {
        return this.xPosition;
    }

    @Override
    public int getVerticalPosition() {
        return this.yPosition;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public Set<Vertex> getSuccessors() {
        return this.maze.getSuccessors(this);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public final void setEmpty() {
        this.maze.changeBoxAtPosition(
                this.getHorizontalPosition(),
                this.getVerticalPosition(),
                new EmptyBox(
                        this.maze,
                        this.getHorizontalPosition(),
                        this.getVerticalPosition()
                )
        );
    }

    @Override
    public boolean isWall() {
        return false;
    }

    @Override
    public final void setWall() {
        this.maze.changeBoxAtPosition(
                this.getHorizontalPosition(),
                this.getVerticalPosition(),
                new WallBox(
                        this.maze,
                        this.getHorizontalPosition(),
                        this.getVerticalPosition()
                )
        );
    }

    @Override
    public boolean isDeparture() {
        return false;
    }

    @Override
    public final void setDeparture() {
        this.maze.changeBoxAtPosition(
                this.getHorizontalPosition(),
                this.getVerticalPosition(),
                new DepartureBox(
                        this.maze,
                        this.getHorizontalPosition(),
                        this.getVerticalPosition()
                )
        );
    }

    @Override
    public boolean isArrival() {
        return false;
    }

    @Override
    public final void setArrival() {
        this.maze.changeBoxAtPosition(
                this.getHorizontalPosition(),
                this.getVerticalPosition(),
                new ArrivalBox(
                        this.maze,
                        this.getHorizontalPosition(),
                        this.getVerticalPosition()
                )
        );
    }

    @Override
    public final boolean belongsToShortestPath() {
        return this.maze.doesMazeBoxBelongsToShortestPath(this);
    }

    @Override
    public boolean isNeighbourOf(final MazeBox box) {
        // Checking that both vertexes are accessible
        if (!(this instanceof AccessibleBox)) return false;
        if (!(box instanceof AccessibleBox)) return false;

        int verticalDistance = Math.abs(this.getVerticalPosition() - box.getVerticalPosition());
        int horizontalDistance = Math.abs(this.getHorizontalPosition() - box.getHorizontalPosition());
        return verticalDistance <= 1 && horizontalDistance <= 1;
    }
}
