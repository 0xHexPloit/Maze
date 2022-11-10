package com.telecom.paris.maze.ui;

import com.telecom.paris.maze.model.MazeModel;
import com.telecom.paris.maze.model.ModelObserver;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class SerializationFileMazePersistenceManager extends FileMazePersistenceManager {
    @Override
    protected MazeModel doRead(String mazeId) throws IOException {
        try (
            FileInputStream fis = new FileInputStream(mazeId);
            ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            MazeModel model = (MazeModel) ois.readObject();
            Set<ModelObserver> observers = new HashSet<>();
            model.setObservers(observers);
            return model;
        } catch (FileNotFoundException | ClassNotFoundException e) {
            throw new IOException(e);
        }
    }

    @Override
    protected void doPersist(MazeModel mazeModel) throws IOException {
        try (
            FileOutputStream fis = new FileOutputStream(mazeModel.getId());
            ObjectOutputStream oos = new ObjectOutputStream(fis);
        ) {
            oos.writeObject(mazeModel);
        } catch (FileNotFoundException e) {
            throw new IOException(e.getMessage());
        }
    }
}
