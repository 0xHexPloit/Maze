package com.telecom.maze.ui;

import javax.swing.JMenu;

/**
 * This menu manages everything that has to do with saving the maze or initializing it from a file.
 * @author Idris DELSOL
 *
 */
@SuppressWarnings("serial") 
public class FileMenu extends JMenu {

	public FileMenu(MazeEditor drawingMaze) {
		super("File");

		add( new QuitMenuItem(drawingMaze));
		add( new SaveMenuItem(drawingMaze));
		add( new SaveAsMenuItem(drawingMaze));
		add( new OpenFileMenuItem(drawingMaze));
	}
}
