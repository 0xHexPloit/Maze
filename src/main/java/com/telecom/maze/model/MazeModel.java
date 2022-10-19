package com.telecom.maze.model;

import java.util.List;

/**
 * Interface providing various methods required for displaying a maze in a maze editor.  
 * 
 * @author Dominique Blouin
 *
 */
public interface MazeModel {
	
	/**
	 * Returns a <code>MazeFactory</code> object responsible for instantiating maze models. This is required when the dimensions of the 
	 * maze should be changed so that the maze editor can create a new maze.
	 * 
	 * @return A <code>MazeFactory</code> object
	 */
	MazeFactory getMazeFactory();
	
	/**
	 * Adds an observer (typically a maze editor) to this maze model so that it can be notified when this maze changes to refresh the editor display.
	 * 
	 * @param observer A <code>ModelObserver</code> object
	 */
	void addObserver( ModelObserver observer );

	/**
	 * Removes an observer (typically a maze editor) to this maze model. This observer will no longer be notified when this maze changes.
	 * 
	 * @param observer A <code>ModelObserver</code> object
	 * 
	 * @return <code>true</code> if this observer was actually removed, false otherwise.
	 */
	boolean removeObserver( ModelObserver observer );
	
	/**
	 * Returns this maze's width.
	 * 
	 * @return The number of columns of this maze.
	 */
	int getWidth();

	/**
	 * Returns this maze's height.
	 * 
	 * @return The number of rows of this maze.
	 */
	int getHeigth();
	
	/**
	 * Returns the maze box located at the specified coordinates. 
	 * 
	 * @param rowIndex The index of the row of required maze box. The the index starts at 0 located on the top of the maze and must be less than the maze's height.
	 * @param colIndex The index of the column of required maze box. The the index starts at 0 located on the left of the maze and must be less than the maze's width.
	 * 
	 * @return The required maze box model. <code>null</code> if the specified coordinates are invalid.
	 */
	MazeBoxModel getMazeBox( 	int rowIndex,
								int colIndex );
	
	/**
	 * Returns the total number of boxes of this maze.
	 * 
	 * @return The heigth * width value
	 */
	int getNumberOfBoxes();
	
	/**
	 * Turns all the boxes of this maze to empty boxes.
	 */
	void clearMaze();
	
	/**
	 * Erases the maze's shortest path as computed when method <code>solve</code> was invoked on this maze.
	 * 
	 */
	void clearShortestPath();
	
	/**
	 * Computes the shortest path of this maze and stores that information within this maze.
	 * 
	 * @return <code>true</code> if a solution path was found for this maze, <code>false</code> otherwise.
	 */
	boolean solve();
	
	/**
	 * Check if this maze is valid for being solved; there should be only one departure and arrival box.
	 * 
	 * @return A list of error messages for each invalid property of this maze. <code>null</code> or an empty list if this maze is valid.
	 */
	List<String> validate();

	/**
	 * Cette méthode permet d'obtenir l'identifient du labyrinthe
	 * @return L'identifiant du labyrinthe.
	 */
	String getId();

	/**
	 * Cette méthode permet de rédéfinir l'identifiant du labyrinthe.
	 * @param mazeId Le nouveau identifiant du labyrinthe.
	 */
	void setId(String mazeId);
}
