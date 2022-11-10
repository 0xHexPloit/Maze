package com.telecom.paris;

import com.telecom.paris.maze.model.BaseMazeFactory;
import com.telecom.paris.maze.ui.FileMazePersistenceManager;
import com.telecom.paris.maze.model.MazeModel;
import com.telecom.paris.maze.ui.MazeEditor;
import com.telecom.paris.maze.ui.SerializationFileMazePersistenceManager;

public class Main {
    public static void main(String[] args) {
        final int mazeHeight = 10;
        final int mazeWidth = 10;

        final MazeModel mazeModel = BaseMazeFactory.getInstance().createMazeModel(mazeHeight, mazeWidth);
        final FileMazePersistenceManager mazePersistenceManager = new SerializationFileMazePersistenceManager();
        final MazeEditor editor = new MazeEditor(mazeModel, mazePersistenceManager);
        mazePersistenceManager.setEditor(editor);
    }
}
