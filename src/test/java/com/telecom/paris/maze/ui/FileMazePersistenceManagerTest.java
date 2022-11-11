package com.telecom.paris.maze.ui;

import com.telecom.paris.maze.model.BaseMaze;
import com.telecom.paris.maze.model.BaseMazeFactory;
import com.telecom.paris.maze.model.MazeFactory;
import com.telecom.paris.maze.model.MazeModel;
import com.telecom.paris.maze.model.box.EmptyBoxBase;

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
import java.util.logging.Logger;

import com.telecom.paris.maze.model.box.ArrivalBoxBase;
import com.telecom.paris.maze.model.box.DepartureBoxBase;
import com.telecom.paris.maze.model.box.WallBoxBase;


@DisplayName("FileMazePersistenceManager")
public class FileMazePersistenceManagerTest {
    private static FileMazePersistenceManager manager;
    private static MazeFactory factory;

    private static Path filesPath;

    @BeforeAll
    public static void init() {
        FileMazePersistenceManagerTest.manager = new FileMazePersistenceManager();
        FileMazePersistenceManagerTest.factory = BaseMazeFactory.getInstance();
        FileMazePersistenceManagerTest.filesPath = Paths.get("src/test/resources");
    }


    @Nested
    @DisplayName("Testing the read method")
    class ReadTest {
        @Test
        public void doReadCorrectly() {
            Path filePath = FileMazePersistenceManagerTest.filesPath.resolve("labyrinthe.maze");
            final int expectedRowsColumns = 10;

            assertDoesNotThrow(() -> {
                MazeModel maze = manager.doRead(filePath.toString());


                assertEquals(maze.getHeight(), expectedRowsColumns);
                assertEquals(maze.getWidth(), expectedRowsColumns);

                // Checking the type of some boxes
                assertEquals(maze.getMazeBox(0, 0).getClass(), EmptyBoxBase.class);
                assertEquals(maze.getMazeBox(1, 0).getClass(), ArrivalBoxBase.class);
                assertEquals(maze.getMazeBox(2, 4).getClass(), WallBoxBase.class);
                assertEquals(maze.getMazeBox(8,0).getClass(), DepartureBoxBase.class);
            });
        }

        @Test
        public void doReadInvalidFileEmpty() {
            Path filePath = FileMazePersistenceManagerTest.filesPath.resolve("labyrintheErreurVide.maze");

            assertThrows(IOException.class, () -> {
                manager.doRead(filePath.toString());
            });
        }

        @Test
        public void doReadInvalidNotSameColumnNumbers() {
            Path filePath = FileMazePersistenceManagerTest.filesPath.resolve("labyrintheErreurColonne.maze");

            assertThrows(IOException.class, () -> {
                manager.doRead(filePath.toString());
            });
        }

        @Test
        public void doReadInvalidSymbol() {
            Path filePath = FileMazePersistenceManagerTest.filesPath.resolve("labyrintheErreurSymboles.maze");

            assertThrows(IOException.class, () -> {
                manager.doRead(filePath.toString());
            });
        }

        @Test
        public void doReadInvalidFileNotExisting() {
            Path filePath = FileMazePersistenceManagerTest.filesPath.resolve("labyrintheErreurInexistant.maze");

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
            int numberRows = 3;
            int numberColumns = 3;

            Path filePath = FileMazePersistenceManagerTest.filesPath.resolve("labyrintheTest.maze");
            BaseMaze maze = (BaseMaze) factory.createMazeModel(numberRows, numberRows);

            maze.setId(filePath.toString());

            // Filling the maze with non-empty boxes
            int[] departureBoxPosition = {0, 0};
            int[] arrivalBoxPosition = {1, 1};
            int[] wallBoxPosition = {1, 0};
            int[] wallBoxTwoPosition = {2, 0};
            int[] emptyBoxPosition = {0, 1};

            maze.changeBoxAtPosition(
                    departureBoxPosition[0],
                    departureBoxPosition[1],
                    new DepartureBoxBase(maze, departureBoxPosition[0], departureBoxPosition[1])
            );
            maze.changeBoxAtPosition(
                    arrivalBoxPosition[0],
                    arrivalBoxPosition[1],
                    new ArrivalBoxBase(maze, arrivalBoxPosition[0], arrivalBoxPosition[1])
            );
            maze.changeBoxAtPosition(
                    wallBoxPosition[0],
                    wallBoxPosition[1],
                    new WallBoxBase(maze, wallBoxPosition[0], wallBoxPosition[1])
            );
            maze.changeBoxAtPosition(
                    wallBoxTwoPosition[0],
                    wallBoxTwoPosition[1],
                    new WallBoxBase(maze, wallBoxTwoPosition[0], wallBoxTwoPosition[1])
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
                        lines.get(departureBoxPosition[1]).charAt(departureBoxPosition[0]),
                        manager.getDepartureBoxRepresentation()
                );
                assertEquals(
                        lines.get(arrivalBoxPosition[1]).charAt(arrivalBoxPosition[0]),
                        manager.getArrivalBoxRepresentation()
                );
                assertEquals(
                        lines.get(wallBoxPosition[1]).charAt(wallBoxPosition[0]),
                        manager.getWallBoxRepresentation()
                );
                assertEquals(
                        lines.get(wallBoxTwoPosition[1]).charAt(wallBoxTwoPosition[0]),
                        manager.getWallBoxRepresentation()
                );
                assertEquals(
                        lines.get(emptyBoxPosition[1]).charAt(emptyBoxPosition[0]),
                        manager.getEmptyBoxRepresentation()
                );

                // Deleting the file
                Files.delete(filePath);
            });
        }

        @Test
        void doPersistInvalidSavingPath() {
            Path filePath = FileMazePersistenceManagerTest.filesPath.getParent().resolve("hugo/maze.maze");
            MazeModel maze = factory.createMazeModel(2, 2);
            maze.setId(filePath.toString());

            assertThrows(IOException.class, () -> {
                manager.persist(maze);
            });
        }
    }
}