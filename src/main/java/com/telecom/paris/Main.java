package com.telecom.paris;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import com.telecom.paris.maze.model.BaseMazeFactory;
import com.telecom.paris.maze.ui.FileMazePersistenceManager;
import com.telecom.paris.maze.model.MazeModel;
import com.telecom.paris.maze.ui.MazeEditor;
import com.telecom.paris.maze.ui.SerializationFileMazePersistenceManager;

public class Main {
    public static void main(String[] args) throws IOException {
        final String configPath = args.length == 1 ? args[0] : "src/main/resources/maze.properties";
        final Properties properties = new Properties();
        properties.load(
                Paths.get(configPath)
                        .toFile()
                        .toURI()
                        .toURL()
                        .openStream()
        );

        // Checking if we should save the logs in a file
        if (Boolean.parseBoolean(properties.getProperty("maze.logs.saveToFile"))) {
            final Path logsPath = Paths.get(properties.getProperty("maze.logs.path"));
            Logger.getGlobal().addHandler(new FileHandler(logsPath.toString()));
        }

        final int mazeHeight = Integer.parseInt(properties.getProperty("maze.height"));
        final int mazeWidth = Integer.parseInt(properties.getProperty("maze.width"));

        Logger.getGlobal().info(String.format("Creating a new maze of size %dx%d", mazeHeight, mazeWidth));

        final MazeModel mazeModel = BaseMazeFactory.getInstance().createMazeModel(
                mazeHeight,
                mazeWidth
        );
        final FileMazePersistenceManager mazePersistenceManager = Boolean
                .parseBoolean(properties.getProperty("maze.persistence.use_serialization")) ?
                new SerializationFileMazePersistenceManager() :
                new FileMazePersistenceManager();

        final MazeEditor editor = new MazeEditor(mazeModel, mazePersistenceManager);
        mazePersistenceManager.setEditor(editor);
        Logger.getGlobal().info("Closing the editor");
    }
}
