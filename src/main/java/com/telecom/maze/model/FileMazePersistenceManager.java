package com.telecom.maze.model;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.telecom.maze.model.MazeModel;
import com.telecom.maze.model.MazePersistenceManager;
import com.telecom.maze.model.box.ArrivalBox;
import com.telecom.maze.model.box.DepartureBox;
import com.telecom.maze.model.box.WallBox;

public class FileMazePersistenceManager implements MazePersistenceManager {
	
	private final char BOX_DEPARTURE = 'D';
	private final char BOX_ARRIVAL = 'A';
	private final char BOX_WALL = 'W';
	private final char BOX_EMPTY = 'E';
	
	private Component editor;
	
    private final FileNameExtensionFilter filter;
	
	public FileMazePersistenceManager() {
		this.editor = null;
		filter = new FileNameExtensionFilter( "Maze files only (*.maze)", "maze" );
	}
	
	public void setEditor( Component editor ) {
		this.editor = editor;
	}
	
	@Override
	public MazeModel read( String mazeId ) 
	throws IOException {
		if ( mazeId == null || mazeId.isEmpty() ) {
			final JFileChooser chooser = new JFileChooser(); //This class enable us to open a file explorer for a more ergonomic design. 
		    chooser.setFileFilter(filter);
		    chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		   
		    final int returnVal = chooser.showOpenDialog( editor );
		    
		    if ( returnVal == JFileChooser.APPROVE_OPTION ) {
		    	final File file = chooser.getSelectedFile();
		    	mazeId = file.getPath();
			}
		    else {
		    	throw new IOException( "No file selected!" );
		    }
		}
		
		return doRead( mazeId );
	}
	
	protected MazeModel doRead( final String mazeId ) throws IOException {
		Path path = Paths.get(mazeId);
		List<String> lines = Files.readAllLines(path);

		int numberRows = lines.size();

		// First we need to check that the file is not empty
		if (numberRows == 0) throw new MazeReadingException("Pas de contenu à lire", mazeId, 0);

		// Creating new maze
		int numberColumns = lines.get(0).length();

		Maze maze = (Maze) BaseMazeFactory.getInstance().createMazeModel(
				numberRows,
				numberColumns
		);

		for (int rowIndex = 0; rowIndex < numberRows; rowIndex++) {
			String line = lines.get(rowIndex);

			// Checking that the length of the line respects the number of columns computed
			if (line.length() != numberColumns) throw new MazeReadingException(
					"Nombre de colonnes invalide",
					mazeId,
					rowIndex
			);

			char[] columnsArr = line.toCharArray();

			for (int columnIndex = 0; columnIndex < columnsArr.length; columnIndex++) {
				char column = columnsArr[columnIndex];
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
					case BOX_EMPTY -> {} // Nothing to do, maze is by default filled with empty boxes.
					case BOX_WALL -> maze.changeBoxAtPosition(
							columnIndex,
							rowIndex,
							new WallBox(maze, columnIndex, rowIndex)
					);
					default ->
						throw new MazeReadingException("Unable to read column", mazeId, rowIndex);

				}
			}
 		}

		return maze;
	}

	@Override
	public void persist( final MazeModel mazeModel )
	throws IOException {
		String mazeId = mazeModel.getId();
		
		if ( mazeId == null || mazeId.isEmpty() ) {
			mazeId = newMazeId();
			
			if (mazeId == null || mazeId.isEmpty() ) {
				throw new IOException("No file path was choosen!" );
			}
			
			mazeModel.setId( mazeId );
		}
		
		doPersist( mazeModel );
	}
		
	protected void doPersist( final MazeModel mazeModel )
	throws IOException {
		// TODO: Implement
	}

	@Override
	public boolean delete(MazeModel mazeModel)  throws IOException {
		return new File( mazeModel.getId() ).delete();
	}

	private String newMazeId() {
		final JFileChooser chooser = new JFileChooser();
	    chooser.setFileFilter( filter );
	    chooser.setDialogType( JFileChooser.SAVE_DIALOG );
	    final int returnVal = chooser.showSaveDialog( editor );
	    
	    if ( returnVal == JFileChooser.APPROVE_OPTION ) {
	    	final File file = chooser.getSelectedFile();

	    	return file.getPath();
	    }
	    
	    return null;
	}
}
