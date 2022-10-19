package com.telecom.maze.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.telecom.maze.model.box.ArrivalBox;
import com.telecom.maze.model.box.DepartureBox;
import com.telecom.maze.model.box.WallBox;


@DisplayName("FileMazePersistenceManager")
public class FileMazePersistenceManagerTest {
    private static FileMazePersistenceManager manager;
    private static MazeFactory factory;

    private static final String FILE_EXTENSION = ".maze";

    @BeforeAll
    public static void init() {

        FileMazePersistenceManagerTest.manager = new FileMazePersistenceManager();
        FileMazePersistenceManagerTest.factory = BaseMazeFactory.getInstance();
    }


    @Nested
    @DisplayName("Testing the read method")
    class ReadTest {
        @Test
        public void doReadCorrectly() {
            Path filePath = Paths.get("src", "test", "resources", "mazeCorrect" + FILE_EXTENSION);

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
            Path filePath = Paths.get("src", "test", "resources", "mazeEmpty" + FILE_EXTENSION);

            assertThrows(IOException.class, () -> {
                manager.doRead(filePath.toString());
            });
        }

        @Test
        public void doReadInvalidNotSameColumnNumbers() {
            Path filePath = Paths.get(
                    "src",
                    "test",
                    "resources",
                    "mazeNotSameColumnNumbers" + FILE_EXTENSION
            );

            assertThrows(IOException.class, () -> {
                manager.doRead(filePath.toString());
            });
        }

        @Test
        public void doReadInvalidColumnIncorrect() {
            Path filePath = Paths.get("src", "test", "resources", "mazeInvalidColumn" + FILE_EXTENSION);

            assertThrows(IOException.class, () -> {
                manager.doRead(filePath.toString());
            });
        }

        @Test
        public void doReadInvalidFileNotExisting() {
            Path filePath = Paths.get("src", "test", "resources", "mazeNotExisting" + FILE_EXTENSION);

            assertThrows(IOException.class, () -> {
                manager.doRead(filePath.toString());
            });
        }
    }

    @Nested
    @DisplayName("Testing the persist method")
    class PersistTest {
        @Test
        void doPersistValid() {
            int numberRows = 2;
            int numberColumns = 2;

            Path filePath = Paths.get("src", "test", "resources", "labyrinthe2.maze");
            BaseMaze maze = (BaseMaze) factory.createMazeModel(numberRows, numberRows);

            maze.setId(filePath.toString());

            // Filling the maze with non-empty boxes
            int[] departureBoxPosition = {0, 0};
            int[] arrivalBoxPosition = {1, 1};
            int[] wallBoxPosition = {1, 0};
            int[] emptyBoxPosition = {0, 1};

            maze.changeBoxAtPosition(
                    departureBoxPosition[0],
                    departureBoxPosition[1],
                    new DepartureBox(maze, departureBoxPosition[0], departureBoxPosition[1])
            );
            maze.changeBoxAtPosition(
                    arrivalBoxPosition[0],
                    arrivalBoxPosition[1],
                    new ArrivalBox(maze, arrivalBoxPosition[0], arrivalBoxPosition[1])
            );
            maze.changeBoxAtPosition(
                    wallBoxPosition[0],
                    wallBoxPosition[1],
                    new WallBox(maze, wallBoxPosition[0], wallBoxPosition[1])
            );

            assertDoesNotThrow(() -> {
                manager.doPersist(maze);

                // Checking that the file has been created
                assertTrue(filePath.toFile().exists());

                List<String> lines = Files.readAllLines(filePath);

                // Checking number rows and columns
                assertEquals(lines.size(), numberRows);
                assertEquals(lines.get(0).length(), numberColumns);
                assertEquals(lines.get(1).length(), numberColumns);

                // Checking that the boxes are correctly placed in the file
                assertEquals(
                        lines.get(departureBoxPosition[0]).charAt(departureBoxPosition[1]),
                        manager.getDepartureBoxRepresentation()
                );
                assertEquals(
                        lines.get(arrivalBoxPosition[0]).charAt(arrivalBoxPosition[1]),
                        manager.getArrivalBoxRepresentation()
                );
                assertEquals(
                        lines.get(wallBoxPosition[0]).charAt(wallBoxPosition[1]),
                        manager.getWallBoxRepresentation()
                );
                assertEquals(
                        lines.get(emptyBoxPosition[0]).charAt(emptyBoxPosition[1]),
                        manager.getEmptyBoxRepresentation()
                );

                // Deleting the file
                Files.delete(filePath);
            });
        }

        @Test
        void doPersistInvalidSavingPath() {
            Path filePath = Paths.get("src", "hugo", "labyrinthe2.maze");
            MazeModel maze = factory.createMazeModel(2, 2);
            maze.setId(filePath.toString());

            assertThrows(IOException.class, () -> {
                manager.persist(maze);
            });
        }
    }
}