package com.telecom.paris.maze.model.box;

import com.telecom.paris.maze.model.MazeBoxModel;
import com.telecom.paris.maze.model.Maze;
import com.telecom.paris.graph.Vertex;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

public abstract class MazeBox implements Vertex, MazeBoxModel, Serializable {
    @Serial
    private static final long serialVersionUID = 202211101416L;

    private final Maze maze;
    private final int xPosition;
    private final int yPosition;

    private final String label;

    public MazeBox(final Maze maze, int xPosition, int yPosition) {
        this.maze = maze;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.label = String.format("(%d, %d)", xPosition, yPosition);
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
        return this.label;
    }

    @Override
    public Set<Vertex> getSuccessors() {
        return this.getMaze().getSuccessors(this);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public final void setEmpty() {
        this.getMaze().changeBoxAtPosition(
                this.getxPosition(),
                this.getyPosition(),
                new EmptyBox(
                        this.getMaze(),
                        this.getxPosition(),
                        this.getyPosition()
                )
        );
    }

    @Override
    public boolean isWall() {
        return false;
    }

    @Override
    public final void setWall() {
        this.getMaze().changeBoxAtPosition(
                this.getxPosition(),
                this.getyPosition(),
                new WallBox(
                        this.getMaze(),
                        this.getxPosition(),
                        this.getyPosition()
                )
        );
    }

    @Override
    public boolean isDeparture() {
        return false;
    }

    @Override
    public final void setDeparture() {
        this.getMaze().changeBoxAtPosition(
                this.getxPosition(),
                this.getyPosition(),
                new DepartureBox(
                        this.getMaze(),
                        this.getxPosition(),
                        this.getyPosition()
                )
        );
    }

    @Override
    public boolean isArrival() {
        return false;
    }

    @Override
    public final void setArrival() {
        this.getMaze().changeBoxAtPosition(
                this.getxPosition(),
                this.getyPosition(),
                new ArrivalBox(
                        this.getMaze(),
                        this.getxPosition(),
                        this.getyPosition()
                )
        );
    }

    @Override
    public final boolean belongsToShortestPath() {
        return this.getMaze().doesMazeBoxBelongsToShortestPath(this);
    }
}
