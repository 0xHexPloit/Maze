package com.telecom.paris.maze.ui;

import javax.swing.*;

@SuppressWarnings("serial") 
public class FileMenu extends JMenu {
	
	private final SaveMenuItem saveMenuItem;

	private final SaveAsMenuItem saveAsMenuItem;

	public FileMenu( MazeEditor mazeEditor ) {
		super("File");

		add( new QuitMenuItem(mazeEditor));
		
		saveMenuItem = new SaveMenuItem(mazeEditor);
		add( saveMenuItem );
		
		saveAsMenuItem = new SaveAsMenuItem(mazeEditor);
		add( saveAsMenuItem );
		
		add( new OpenFileMenuItem(mazeEditor));
	}

	public void notifyForUpdate() {
		saveMenuItem.repaint();
		saveAsMenuItem.repaint();
	}
}
