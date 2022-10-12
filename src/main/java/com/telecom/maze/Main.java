package com.telecom.maze;

import com.telecom.maze.model.BaseMazeFactory;
import com.telecom.maze.model.MazeModel;
import com.telecom.maze.ui.MazeEditor;

public class Main {
    public static void main(String[] args) {
        MazeModel mazeModel = BaseMazeFactory.getInstance().createMazeModel(5, 5);
        MazeEditor mazeEditor = new MazeEditor(mazeModel);
    }
}
