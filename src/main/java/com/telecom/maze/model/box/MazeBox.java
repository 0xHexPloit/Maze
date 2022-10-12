package com.telecom.maze.model.box;

import com.telecom.maze.model.BaseMaze;
import com.telecom.maze.model.MazeBoxModel;
import com.telecom.maze.model.graph.Vertex;

import java.util.Set;

public abstract class MazeBox implements Vertex, MazeBoxModel {
    private final BaseMaze maze;
    private final int xPosition;
    private final int yPosition;

    public MazeBox(BaseMaze maze, int xPosition, int yPosition) {
        this.maze = maze;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public BaseMaze getMaze() {
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

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void setEmpty() {
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
    public void setWall() {
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
    public void setDeparture() {
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
    public void setArrival() {
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
    public boolean belongsToShortestPath() {
        return this.getMaze().doesMazeBoxBelongsToShortestPath(this);
    }
}
