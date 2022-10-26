package com.telecom.paris.maze.model;

public class BaseMazeFactory implements MazeFactory {
    private static final BaseMazeFactory INSTANCE = new BaseMazeFactory();

    // Defining the constructor as private permits to implement the singleton pattern
    private BaseMazeFactory() {}


    @Override
    public MazeModel createMazeModel(int height, int width) {
        return new BaseMaze(height, width);
    }

    public static MazeFactory getInstance() {
        return INSTANCE;
    }
}
