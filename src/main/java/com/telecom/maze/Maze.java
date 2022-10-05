package com.telecom.maze;

import com.telecom.graph.Graph;
import com.telecom.graph.Vertex;
import com.telecom.maze.box.EmptyBox;
import com.telecom.maze.box.MazeBox;
import com.telecom.maze.box.WallBox;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Maze implements Graph {
    private final int height;
    private final int width;
    private final MazeBox[][] tiles;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        this.tiles = new MazeBox[height][width];
        this.fillTiles();
    }

    private void fillTiles() {
        for (int rowIndex = 0; rowIndex < height; rowIndex++) {
            for (int colIndex = 0; colIndex < width; colIndex++) {
                this.tiles[rowIndex][colIndex] = new EmptyBox(this, colIndex, rowIndex);
            }
        }
    }

    @Override
    public Set<Vertex> getNeighboursForVertex(Vertex vertex) {
        Set<Vertex> vertices = new HashSet<>();

        // Checking if vertex is a wall box
        if (vertex instanceof WallBox) return vertices;


        MazeBox box = (MazeBox) vertex;
        int boxXPosition = box.getxPosition();
        int boxYPosition = box.getyPosition();

        if (boxXPosition > 0) {
            MazeBox neighbourBox = this.tiles[boxYPosition][boxXPosition - 1];
            if (!(neighbourBox instanceof WallBox)) vertices.add(neighbourBox);
        }

        if (boxXPosition < width - 1) {
            MazeBox neighbourBox = this.tiles[boxYPosition][boxXPosition + 1];
            if (!(neighbourBox instanceof WallBox)) vertices.add(neighbourBox);
        }

        if (boxYPosition > 0) {
            MazeBox neighbourBox = this.tiles[boxYPosition - 1][boxXPosition];
            if (!(neighbourBox instanceof WallBox)) vertices.add(neighbourBox);
        }

        if (boxYPosition < height - 1) {
            MazeBox neighbourBox = this.tiles[boxYPosition + 1][boxXPosition];
            if (!(neighbourBox instanceof WallBox)) vertices.add(neighbourBox);
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
    public int getWeight(Vertex src, Vertex dst) {
        // Checking that dst is a neighbour of src
        MazeBox srcBox = (MazeBox) src;
        MazeBox dstBox = (MazeBox) dst;

        int srcXPosition = srcBox.getxPosition();
        int srcYPosition = srcBox.getyPosition();

        int dstXPosition = dstBox.getxPosition();
        int dstYPosition = dstBox.getyPosition();

        // Checking that src and dst are neighbours.
        if (Math.abs(srcXPosition - dstXPosition) != 1 || Math.abs(srcYPosition - dstYPosition) != 1) {
            throw new IllegalArgumentException("src and dst are not neighbours");
        }

        return 1;
    }
}
