package com.telecom.paris.maze.model;

import java.io.Serial;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Logger;

import com.telecom.paris.maze.model.box.*;
import com.telecom.paris.graph.shortestpath.Dijkstra;
import com.telecom.paris.graph.shortestpath.ShortestPaths;
import com.telecom.paris.graph.Vertex;
import com.telecom.paris.maze.model.exceptions.NotAdjacentVerticesException;

public final class BaseMaze implements Maze {
    @Serial
    private static final long serialVersionUID = 202211101416L;

    private final int height;
    private final int width;
    private final BaseMazeBox[][] tiles;

    private transient Set<ModelObserver> observers;

    private transient ShortestPaths shortestPaths;

    private String id;

    public BaseMaze(int height, int width) {
        this.height = height;
        this.width = width;
        this.tiles = new BaseMazeBox[height][width];
        this.observers = new HashSet<>();
        this.shortestPaths = null;
        this.id = "";
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
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String mazeId) {
        Logger.getGlobal().info(String.format("Setting maze id to %s", mazeId));
        this.id = mazeId;
    }

    @Override
    public Set<Vertex> getSuccessors(final Vertex vertex) {
        // Check that vertex is not null
        if (vertex == null) throw new IllegalArgumentException("Vertex cannot be null");

        Set<Vertex> vertices = new HashSet<>();

        // Checking if the box has no neighbours
        if (!(vertex instanceof AccessibleBox)) return vertices;


        BaseMazeBox box = (BaseMazeBox) vertex;
        int boxXPosition = box.getHorizontalPosition();
        int boxYPosition = box.getVerticalPosition();

        if (boxXPosition > 0) {
            BaseMazeBox neighbourBox = this.tiles[boxYPosition][boxXPosition - 1];
            if (neighbourBox instanceof AccessibleBox) vertices.add(neighbourBox);
        }

        if (boxXPosition < width - 1) {
            BaseMazeBox neighbourBox = this.tiles[boxYPosition][boxXPosition + 1];
            if (neighbourBox instanceof AccessibleBox) vertices.add(neighbourBox);
        }

        if (boxYPosition > 0) {
            BaseMazeBox neighbourBox = this.tiles[boxYPosition - 1][boxXPosition];
            if (neighbourBox instanceof AccessibleBox) vertices.add(neighbourBox);
        }

        if (boxYPosition < height - 1) {
            BaseMazeBox neighbourBox = this.tiles[boxYPosition + 1][boxXPosition];
            if (neighbourBox instanceof AccessibleBox) vertices.add(neighbourBox);
        }

        return vertices;
    }

    @Override
    public Vertex getVertex(final String label) {
            Set<Vertex> vertices = this.getVertexes();
            for (Vertex vertex : vertices) {
                if (vertex.getLabel().equals(label)) return vertex;
            }
            return null;
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
    public void changeBoxAtPosition(int xPosition, int yPosition, final BaseMazeBox box) {
            if (xPosition < 0 || xPosition >= width) throw new IllegalArgumentException("xPosition is out of bounds");
            if (yPosition < 0 || yPosition >= height) throw new IllegalArgumentException("yPosition is out of bounds");

            Logger.getGlobal().info(String.format(
                    "Changing box at position (%d, %d) to %s",
                    xPosition,
                    yPosition,
                    box.getClass().getSimpleName()
            ));

            this.tiles[yPosition][xPosition] = box;
            this.notifyObservers();
    }

    @Override
    public int getEdgeWeight(final Vertex src, final Vertex dst) throws NotAdjacentVerticesException {
        // Checking that src and dst are not null
        if (src == null || dst == null) throw new IllegalArgumentException("src or dst is null");

        // Checking that dst is a neighbour of src
        BaseMazeBox srcBox = (BaseMazeBox) src;
        BaseMazeBox dstBox = (BaseMazeBox) dst;

        if (!srcBox.isNeighbourOf(dstBox)) throw new NotAdjacentVerticesException(src, dst);

        return 1;
    }

    private int countNumberBoxesVerifyingCondition(final Predicate<BaseMazeBox> condition) {
        return Arrays.stream(this.tiles)
                .mapToInt(row -> (int) Arrays.stream(row).filter(condition).count())
                .sum();
    }

    private BaseMazeBox getFirstBoxVerifyingCondition(final Predicate<BaseMazeBox> condition) {
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

    @Override
    public boolean doesMazeBoxBelongsToShortestPath(final BaseMazeBox box) {
        // Checking if the maze has been solved
        if (this.shortestPaths == null) return false;

        // Checking that the box is not null
        if (box == null) throw new IllegalArgumentException("box cannot be null");

        BaseMazeBox startBox = this.getFirstBoxVerifyingCondition(BaseMazeBox::isDeparture);
        BaseMazeBox endBox = this.getFirstBoxVerifyingCondition(BaseMazeBox::isArrival);

        return this.shortestPaths.getShortestPathBetween(startBox, endBox).contains(box);
    }

    @Override
    public void setObservers(Set<ModelObserver> observers) {
        if (observers == null) throw new IllegalArgumentException("observers cannot be null");
        this.observers = observers;
    }


    @Override
    public MazeFactory getMazeFactory() {
        return BaseMazeFactory.getInstance();
    }

    @Override
    public void addObserver(ModelObserver observer) {
        if (observer == null) throw new IllegalArgumentException("observer cannot be null");
        this.observers.add(observer);
    }

    @Override
    public boolean removeObserver(ModelObserver observer) {
        if (observer == null) throw new IllegalArgumentException("observer cannot be null");
        return this.observers.remove(observer);
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public MazeBoxModel getMazeBox(int rowIndex, int colIndex) {
        if (rowIndex < 0 || rowIndex >= this.height) throw new IllegalArgumentException("rowIndex is out of bounds");
        if (colIndex < 0 || colIndex >= this.width) throw new IllegalArgumentException("colIndex is out of bounds");

        return this.tiles[rowIndex][colIndex];
    }

    @Override
    public int getNumberOfBoxes() {
        return this.height * this.width;
    }

    @Override
    public void clearMaze() {
        Logger.getGlobal().info("Clearing maze");
        this.fillTilesWithEmptyBoxes();
        this.notifyObservers();
    }

    @Override
    public void clearShortestPath() {
        Logger.getGlobal().info("Clearing shortest path");
        this.shortestPaths = null;
        this.notifyObservers();
    }

    @Override
    public boolean solve() {
        Logger.getGlobal().info("Solving maze");
        BaseMazeBox startBox = this.getFirstBoxVerifyingCondition(box -> box instanceof DepartureBox);
        BaseMazeBox endBox = this.getFirstBoxVerifyingCondition(box -> box instanceof ArrivalBox);

        this.shortestPaths = Dijkstra.dijkstra(
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

        // Checking that the maze contains only one start box
        int numberOfDepartureBoxes = this.countNumberBoxesVerifyingCondition(BaseMazeBox::isDeparture);

        if (numberOfDepartureBoxes != 1) errors.add("The number of departure boxes is not equal to 1\n");

        // Checking that the maze contains only one arrival box
        int numberOfArrivalBoxes = this.countNumberBoxesVerifyingCondition(BaseMazeBox::isArrival);

        if (numberOfArrivalBoxes != 1) errors.add("The number of arrival boxes is not equal to 1\n");

        return errors;
    }
}
