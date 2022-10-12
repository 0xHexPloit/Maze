package com.telecom.maze.model;

public class BaseMazeFactory implements MazeFactory {
    private static final BaseMazeFactory INSTANCE = new BaseMazeFactory();

    // Defining the constructor as private permits to implement the singleton pattern
    private BaseMazeFactory() {}


    @Override
    public MazeModel createMazeModel(int height, int width) {
        return new Maze(height, width);
    }

    public static MazeFactory getInstance() {
        return INSTANCE;
    }
}
