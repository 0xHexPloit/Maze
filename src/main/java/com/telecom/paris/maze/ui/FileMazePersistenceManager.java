package com.telecom.paris.maze.ui;

import java.awt.Component;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.telecom.paris.maze.model.*;
import com.telecom.paris.maze.model.box.ArrivalBox;
import com.telecom.paris.maze.model.box.DepartureBox;
import com.telecom.paris.maze.model.box.MazeBoxModel;
import com.telecom.paris.maze.model.box.WallBox;
import com.telecom.paris.maze.model.exceptions.MazeReadingException;

public class FileMazePersistenceManager implements MazePersistenceManager {

	private final char BOX_DEPARTURE = 'D';
	private final char BOX_ARRIVAL = 'A';
	private final char BOX_WALL = 'W';
	private final char BOX_EMPTY = 'E';

	private Component editor;

	private final FileNameExtensionFilter filter;

	public FileMazePersistenceManager() {
		this.editor = null;
		filter = new FileNameExtensionFilter("Maze files only (*.maze)", "maze");
	}

	public char getDepartureBoxRepresentation() {
		return BOX_DEPARTURE;
	}

	public char getArrivalBoxRepresentation() {
		return BOX_ARRIVAL;
	}

	public char getWallBoxRepresentation() {
		return BOX_WALL;
	}

	public char getEmptyBoxRepresentation() {
		return BOX_EMPTY;
	}

	public void setEditor(Component editor) {
		this.editor = editor;
	}

	@Override
	public MazeModel read(String mazeId)
			throws IOException {
		if (mazeId == null || mazeId.isEmpty()) {
			final JFileChooser chooser = new JFileChooser(); //This class enable us to open a file explorer for a more ergonomic design.
			chooser.setFileFilter(filter);
			chooser.setDialogType(JFileChooser.OPEN_DIALOG);

			final int returnVal = chooser.showOpenDialog(editor);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				final File file = chooser.getSelectedFile();
				mazeId = file.getPath();
			} else {
				throw new IOException("No file selected!");
			}
		}

		return doRead(mazeId);
	}

	protected MazeModel doRead(final String mazeId)
			throws IOException {
		Logger.getGlobal().info(String.format("Reading maze from file %s", mazeId));

		try {
			final Path path = Paths.get(mazeId);
			final List<String> lines = Files.readAllLines(path);

			final int numberRows = lines.size();

			// First we need to check that the file is not empty
			if (numberRows == 0) throw new MazeReadingException("The file is empty", mazeId, 0);

			// Then we need to check that all the lines have the same number of characters
			final int numberDistinctColumns = (int) lines.stream().mapToInt(String::length).distinct().count();
			if (numberDistinctColumns != 1) throw new MazeReadingException(
					"The number of columns is not the same for all the lines",
					mazeId,
					0
			);

			final int numberColumns = lines.get(0).length();

			// Creating new maze
			Maze maze = (Maze) BaseMazeFactory.getInstance().createMazeModel(
					numberRows,
					numberColumns
			);
			maze.setId(mazeId);

			for (int rowIndex = 0; rowIndex < numberRows; rowIndex++) {
				String line = lines.get(rowIndex);

				final char[] columnsArr = line.toCharArray();

				for (int columnIndex = 0; columnIndex < columnsArr.length; columnIndex++) {
					final char column = columnsArr[columnIndex];
					switch (column) {
						case BOX_DEPARTURE -> {
							maze.changeBoxAtPosition(
									columnIndex,
									rowIndex,
									new DepartureBox(maze, columnIndex, rowIndex)
							);
						}
						case BOX_ARRIVAL -> {
							maze.changeBoxAtPosition(
									columnIndex,
									rowIndex,
									new ArrivalBox(maze, columnIndex, rowIndex)
							);
						}
						case BOX_EMPTY -> {
						} // Nothing to do, maze is by default filled with empty boxes.
						case BOX_WALL -> maze.changeBoxAtPosition(
								columnIndex,
								rowIndex,
								new WallBox(maze, columnIndex, rowIndex)
						);
						default -> throw new MazeReadingException(
								String.format("Invalid symbol: %s", column),
								mazeId,
								rowIndex
						);

					}
				}
			}

			return maze;
		} catch (MazeReadingException e) {
			Logger.getGlobal().severe(e.getMessage());
			throw e;
		}
	}

	@Override
	public void persist( final MazeModel mazeModel )
	throws IOException {
		String mazeId = mazeModel.getId();

		if ( mazeId == null || mazeId.isEmpty() ) {
			mazeId = newMazeId();

			if (mazeId == null || mazeId.isEmpty() ) {
				throw new IOException("No file path was chosen!" );
			}

			mazeModel.setId( mazeId );
		}

		doPersist( mazeModel );
	}

	protected void doPersist( final MazeModel mazeModel )
	throws IOException {
		Logger.getGlobal().info(String.format("Saving maze to file %s", mazeModel.getId()));

		// Define the path where the file will be saved
		final Path path = Paths.get(mazeModel.getId());

		try (
				final FileWriter writer = new FileWriter(path.toFile(), StandardCharsets.UTF_8);
				final BufferedWriter buffWriter = new BufferedWriter(writer);
				final PrintWriter printWriter = new PrintWriter(buffWriter);
		) {
			for (int rowIndex = 0; rowIndex < mazeModel.getHeight(); rowIndex++) {
				for (int columnIndex = 0; columnIndex < mazeModel.getWidth(); columnIndex++) {
					MazeBoxModel box = mazeModel.getMazeBox(rowIndex, columnIndex);

					/**
					 * Note: The following code is working but as far as I'm concerned, it may be improved.
					 * As a matter of fact, in case we are adding a new box type, we will have to modify this code and
					 * the content of this class (attributes, BOX_DEPARTURE, BOX_ARRIVAL, BOX_EMPTY, BOX_WALL,etc).
					 *
					 * I think that we should add a method in the MazeBoxModel interface that returns
					 * a char representation of the box type. This way we could replace the following code by:
					 *
					 * printWriter.print(box.getCharRepresentation());
					 */
					if (box.isDeparture()) {
						printWriter.print(BOX_DEPARTURE);
					} else if (box.isArrival()) {
						printWriter.print(BOX_ARRIVAL);
					} else if (box.isWall()) {
						printWriter.print(BOX_WALL);
					} else {
						printWriter.print(BOX_EMPTY);
					}
				}
				printWriter.println();
			}
		} catch (IOException e) {
			Logger.getGlobal().severe(String.format("Error while saving maze to file %s", mazeModel.getId()));
			throw new IOException("Error while saving the maze", e);
		}
	}

	@Override
	public boolean delete(MazeModel mazeModel)
	throws IOException {
		return new File( mazeModel.getId() ).delete();
	}

	private String newMazeId() {
		final JFileChooser chooser = new JFileChooser();
	    chooser.setFileFilter( filter );
	    chooser.setDialogType( JFileChooser.SAVE_DIALOG );
	    final int returnVal = chooser.showSaveDialog( editor );

	    if ( returnVal == JFileChooser.APPROVE_OPTION ) {
	    	final File file = chooser.getSelectedFile();
			String filePath = file.getPath();

			// Add the extension if it is missing or not correct
			final String fileExtension = String.format(".%s", this.filter.getExtensions()[0]);
			if ( !filePath.endsWith( fileExtension ) ) {
				filePath += fileExtension;
			}
			return filePath;
	    }

	    return null;
	}
}
