package com.telecom.maze.model;

/**
 * Interface providing various methods required for displaying a maze box in a maze editor.
 * 
 * @author Dominique Blouin
 *
 */
public interface MazeBoxModel {
	
	/**
	 * Returns a <code>boolean</code> stating if this box is empty (i.e. it can be traversed to be part of a path).
	 * 
	 * @return <code>true</code> if this box is empty, false otherwise.
	 */
	boolean isEmpty();
	
	/**
	 * Makes this maze box an empty box (i.e. it can be traversed to be part of a path).
	 */
	void setEmpty();

	/**
	 * Returns a <code>boolean</code> stating if this box is a wall (i.e. it can not be traversed and not be a part of a path).
	 * 
	 * @return <code>true</code> if this box is a wall, false otherwise.
	 */
	boolean isWall();

	/**
	 * Makes this maze box a wall box (i.e. it can not be traversed cannot be part of a path).
	 */
	void setWall();

	/**
	 * Returns a <code>boolean</code> stating if this box is a departure box (i.e. it is the beginning box of of a path).
	 * 
	 * @return <code>true</code> if this box is a departure box, false otherwise.
	 */
	boolean isDeparture();
	
	/**
	 * Makes this maze a departure box (i.e. it is the beginning box of a path).
	 */
	void setDeparture();

	/**
	 * Returns a <code>boolean</code> stating if this box is an arrival box (i.e. it is the end of a path).
	 * 
	 * @return <code>true</code> if this box is an arrival box, false otherwise.
	 */
	boolean isArrival();

	/**
	 * Makes this maze an arrival box (i.e. it is the end of a path).
	 */
	void setArrival();
	
	/**
	 * Returns a <code>boolean</code> stating if this box belongs to the shortest path solving the maze.
	 * 
	 * @return <code>true</code> if this box belongs to the shortest path, false otherwise.
	 */
	boolean belongsToShortestPath();
}
