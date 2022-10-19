package com.telecom.maze.ui;

import javax.swing.*;

@SuppressWarnings("serial") 
public class DrawingMenuBar extends JMenuBar {
	
	private final FileMenu fileMenu;
	
	public DrawingMenuBar( MazeEditor mazeEditor) {
		super();
		
		fileMenu = new FileMenu( mazeEditor );
		add( fileMenu );
		
		add( new MazeMenu( mazeEditor ) );
	}
	
	public void notifyForUpdate() {
		fileMenu.notifyForUpdate();
	}
}
