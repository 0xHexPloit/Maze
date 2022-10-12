package com.telecom.maze.model;

import com.telecom.maze.model.box.ArrivalBox;
import com.telecom.maze.model.box.DepartureBox;
import com.telecom.maze.model.box.EmptyBox;
import com.telecom.maze.model.box.MazeBox;
import com.telecom.maze.model.box.AccessibleBox;
import com.telecom.maze.model.graph.Graph;
import com.telecom.maze.model.graph.Vertex;
import com.telecom.maze.model.solver.Dikjstra;
import com.telecom.maze.model.solver.ShortestPaths;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Maze implements Graph, MazeModel {
    private final int height;
    private final int width;
    private final MazeBox[][] tiles;

    private final Set<ModelObserver> observers;

    private ShortestPaths shortestPaths;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        this.tiles = new MazeBox[height][width];
        this.observers = new HashSet<>();
        this.shortestPaths = null;
        this.fillTilesWithEmptyBoxes();
    }

    private void fillTilesWithEmptyBoxes() {
        for (int rowIndex = 0; rowIndex < height; rowIndex++) {
            for (int colIndex = 0; colIndex < width; colIndex++) {
                this.tiles[rowIndex][colIndex] = new EmptyBox(this, colIndex, rowIndex);
            }
        }
        // Notify observers that the maze has changed.
        this.notifyObservers();
    }

    @Override
    public Set<Vertex> getSuccessors(Vertex vertex) {
        Set<Vertex> vertices = new HashSet<>();

        // Checking if the box has no neighbours
        if (!(vertex instanceof AccessibleBox)) return vertices;


        MazeBox box = (MazeBox) vertex;
        int boxXPosition = box.getxPosition();
        int boxYPosition = box.getyPosition();

        if (boxXPosition > 0) {
            MazeBox neighbourBox = this.tiles[boxYPosition][boxXPosition - 1];
            if (neighbourBox instanceof AccessibleBox) vertices.add(neighbourBox);
        }

        if (boxXPosition < width - 1) {
            MazeBox neighbourBox = this.tiles[boxYPosition][boxXPosition + 1];
            if (neighbourBox instanceof AccessibleBox) vertices.add(neighbourBox);
        }

        if (boxYPosition > 0) {
            MazeBox neighbourBox = this.tiles[boxYPosition - 1][boxXPosition];
            if (neighbourBox instanceof AccessibleBox) vertices.add(neighbourBox);
        }

        if (boxYPosition < height - 1) {
            MazeBox neighbourBox = this.tiles[boxYPosition + 1][boxXPosition];
            if (neighbourBox instanceof AccessibleBox) vertices.add(neighbourBox);
        }

        return vertices;
    }

    @Override
    public Set<Vertex> getVertexes() {
        Set<Vertex> vertices = new HashSet<>();

        for (int rowIndex = 0; rowIndex < height; rowIndex++) {
            vertices.addAll(Arrays.asList(tiles[rowIndex]).subList(0, width));
        }

        return vertices;
    }


    @Override
    public int getEdgeWeight(Vertex src, Vertex dst) {
        // Checking that both vertexes are accessible
        if (!(src instanceof AccessibleBox)) throw new IllegalArgumentException("src is not an AccessibleBox");
        if (!(dst instanceof AccessibleBox)) throw new IllegalArgumentException("dst is not an AccessibleBox");

        // Checking that dst is a neighbour of src
        MazeBox srcBox = (MazeBox) src;
        MazeBox dstBox = (MazeBox) dst;

        int srcXPosition = srcBox.getxPosition();
        int srcYPosition = srcBox.getyPosition();

        int dstXPosition = dstBox.getxPosition();
        int dstYPosition = dstBox.getyPosition();

        // Checking that src and dst are neighbours.
        if (Math.abs(srcXPosition - dstXPosition) > 1 || Math.abs(srcYPosition - dstYPosition) > 1) {
            throw new IllegalArgumentException("src and dst are not neighbours");
        }

        return 1;
    }

    private void iterateOverTiles(Consumer<MazeBox> consumer) {
        for (int rowIndex = 0; rowIndex < height; rowIndex++) {
            for (int colIndex = 0; colIndex < width; colIndex++) {
                consumer.accept(this.tiles[rowIndex][colIndex]);
            }
        }
    }

    private int countingNumberBoxesVerifyingCondition(Predicate<MazeBox> condition) {
        return Arrays.stream(this.tiles)
                .mapToInt(row -> (int) Arrays.stream(row).filter(condition).count())
                .sum();
    }

    private MazeBox getFirstBoxVerifyingCondition(Predicate<MazeBox> condition) {
        return Arrays.stream(this.tiles)
                .flatMap(Arrays::stream)
                .filter(condition)
                .findFirst()
                .orElse(null);
    }


    private void notifyObservers() {
        for (ModelObserver observer : observers) {
            observer.modelStateChanged();
        }
    }

    public void changeBoxAtPosition(int xPosition, int yPosition, MazeBox box) {
        if (xPosition < 0 || xPosition >= width) throw new IllegalArgumentException("xPosition is out of bounds");
        if (yPosition < 0 || yPosition >= height) throw new IllegalArgumentException("yPosition is out of bounds");

        this.tiles[yPosition][xPosition] = box;
        this.notifyObservers();
    }

    public boolean doesMazeBoxBelongsToShortestPath(MazeBox box) {
        if (this.shortestPaths == null) return false;

        MazeBox startBox = this.getFirstBoxVerifyingCondition(MazeBox::isDeparture);
        MazeBox endBox = this.getFirstBoxVerifyingCondition(MazeBox::isArrival);

        return this.shortestPaths.getShortestPathBetween(startBox, endBox).contains(box);
    }


    @Override
    public MazeFactory getMazeFactory() {
        return BaseMazeFactory.getInstance();
    }

    @Override
    public void addObserver(ModelObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public boolean removeObserver(ModelObserver observer) {
        return this.observers.remove(observer);
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeigth() {
        return this.height;
    }

    @Override
    public MazeBoxModel getMazeBox(int rowIndex, int colIndex) {
        return (MazeBoxModel) this.tiles[rowIndex][colIndex];
    }

    @Override
    public int getNumberOfBoxes() {
        return this.height * this.width;
    }

    @Override
    public void clearMaze() {
        this.fillTilesWithEmptyBoxes();
        this.notifyObservers();
    }

    @Override
    public void clearShortestPath() {
        this.shortestPaths.clear();
        this.notifyObservers();
    }

    @Override
    public boolean solve() {
        MazeBox startBox = this.getFirstBoxVerifyingCondition(box -> box instanceof DepartureBox);
        MazeBox endBox = this.getFirstBoxVerifyingCondition(box -> box instanceof ArrivalBox);

        this.shortestPaths = Dikjstra.dijkstra(
                this,
                startBox,
                endBox,
                this
        );
        this.notifyObservers();
        return this.shortestPaths.getShortestPathBetween(startBox, endBox).size() > 0;
    }

    @Override
    public List<String> validate() {
        List<String> errors = new ArrayList<>();

        // Getting the number of departure boxes
        int numberOfDepartureBoxes = this.countingNumberBoxesVerifyingCondition(MazeBox::isDeparture);

        if (numberOfDepartureBoxes != 1) {
            errors.add("The number of departure boxes is not equal to 1\n");
        }

        // Getting the number of arrival boxes
        int numberOfArrivalBoxes = this.countingNumberBoxesVerifyingCondition(MazeBox::isArrival);

        if (numberOfArrivalBoxes != 1) {
            errors.add("The number of arrival boxes is not equal to 1\n");
        }


        return errors;
    }
}
