package com.telecom.maze.ui;

import javax.swing.JMenuBar;
/**
 * The menu bar of the application
 * @author Idris DELSOL
 *
 */
@SuppressWarnings("serial") 
public class DrawingMenuBar extends JMenuBar {
	
	public DrawingMenuBar(MazeEditor drawingMaze) {
		super();
		
		add( new FileMenu(drawingMaze) );
		add( new MazeMenu(drawingMaze) );
	}
}
