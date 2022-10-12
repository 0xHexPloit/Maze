package com.telecom.maze.model;

/**
 * Interface providing a method to create a new <code>MazeModel</code>. Required by the maze editor when the maze dimensions must be changed.
 * 
 * @author Dominique Blouin
 *
 */
public interface MazeFactory {
	
	/**
	 * Create a new maze model object of the given height and width.
	 * 
	 * @param height The number of rows of the new maze.
	 * @param width The number of columns of the new maze.
	 * 
	 * @return The newly created maze model object. <code>null</code> if any of the parameters is less than 1.
	 */
	MazeModel createMazeModel( 	int height,
								int width );
}
