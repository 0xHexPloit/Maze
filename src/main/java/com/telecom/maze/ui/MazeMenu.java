package com.telecom.maze.ui;

import javax.swing.JMenu
;

@SuppressWarnings("serial") 
public class MazeMenu extends JMenu {

	public MazeMenu(MazeEditor drawingMaze) {
		super("Maze");
		
		add( new NewMazeMenuItem(drawingMaze));
		add( new ClearMazeMenuItem(drawingMaze));
		add( new InformationMazeMenuItem(drawingMaze));
	}
}
