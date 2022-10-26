package com.telecom.paris;

import com.telecom.paris.maze.model.BaseMazeFactory;
import com.telecom.paris.maze.model.FileMazePersistenceManager;
import com.telecom.paris.maze.model.MazeModel;
import com.telecom.paris.maze.ui.MazeEditor;

public class Main {
    public static void main(String[] args) {
        final int mazeHeight = 10;
        final int mazeWidth = 10;

        final MazeModel mazeModel = BaseMazeFactory.getInstance().createMazeModel(mazeHeight, mazeWidth);
        final FileMazePersistenceManager mazePersistenceManager = new FileMazePersistenceManager();
        final MazeEditor editor = new MazeEditor(mazeModel, mazePersistenceManager);
        mazePersistenceManager.setEditor(editor);
    }
}
