package com.telecom.maze.model;

import static org.junit.jupiter.api.Assertions.*;

import com.telecom.maze.model.box.ArrivalBox;
import com.telecom.maze.model.box.DepartureBox;
import com.telecom.maze.model.box.WallBox;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;



public class FileMazePersistenceManagerTest {
    private static FileMazePersistenceManager manager;

    @BeforeAll
    public static void init() {
        FileMazePersistenceManagerTest.manager = new FileMazePersistenceManager();
    }

    @Test
    public void doReadCorrectly()  {
        Path filePath = Paths.get("src", "test", "resources", "mazeCorrect.txt");

        assertDoesNotThrow(() -> {
            MazeModel maze = manager.doRead(filePath.toString());

            assertEquals(maze.getHeigth(), 4);
            assertEquals(maze.getWidth(), 5);

            // Checking number of wall box (should be 1)
            assertTrue(maze.getMazeBox(0, 1) instanceof DepartureBox);
            assertTrue(maze.getMazeBox(1, 2) instanceof WallBox);
            assertTrue(maze.getMazeBox(3, 4) instanceof ArrivalBox);
        });
    }

    @Test
    public void doReadInvalidFileEmpty() {
        Path filePath = Paths.get("src", "test", "resources", "mazeEmpty.txt");

        assertThrows(IOException.class, () -> {
            MazeModel maze = manager.doRead(filePath.toString());
        });
    }

    @Test
    public void doReadInvalidNotSameColumnNumbers() {
        Path filePath = Paths.get("src", "test", "resources", "mazeNotSameColumnNumbers.txt");

        assertThrows(IOException.class, () -> {
            MazeModel maze = manager.doRead(filePath.toString());
        });
    }

    @Test
    public void doReadInvalidColumnIncorrect() {
        Path filePath = Paths.get("src", "test", "resources", "mazeInvalidColumn.txt");

        assertThrows(IOException.class, () -> {
            MazeModel maze = manager.doRead(filePath.toString());
        });
    }

    @Test
    public void doReadInvalidFileNotExisting() {
        Path filePath = Paths.get("src", "test", "resources", "mazeNotExisting.txt");

        assertThrows(IOException.class, () -> {
            MazeModel maze = manager.doRead(filePath.toString());
        });
    }
}