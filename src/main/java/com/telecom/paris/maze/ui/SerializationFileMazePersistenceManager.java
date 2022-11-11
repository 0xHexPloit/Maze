package com.telecom.paris.maze.ui;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

import com.telecom.paris.maze.model.MazeModel;
import com.telecom.paris.maze.model.ModelObserver;

public final class SerializationFileMazePersistenceManager extends FileMazePersistenceManager {
    @Override
    protected MazeModel doRead(String mazeId) throws IOException {
        try (
            final FileInputStream fis = new FileInputStream(mazeId);
            final ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            MazeModel model = (MazeModel) ois.readObject();
            Set<ModelObserver> observers = new HashSet<>();
            model.setObservers(observers);
            return model;
        } catch (ClassNotFoundException | IOException e) {
            throw new IOException("Unable to deserialize the maze. Error: " + e.getMessage());
        }
    }

    @Override
    protected void doPersist(MazeModel mazeModel) throws IOException {
        try (
            final FileOutputStream fis = new FileOutputStream(mazeModel.getId());
            final ObjectOutputStream oos = new ObjectOutputStream(fis)
        ) {
            oos.writeObject(mazeModel);
        } catch (FileNotFoundException e) {
            throw new IOException(e.getMessage());
        }
    }
}
