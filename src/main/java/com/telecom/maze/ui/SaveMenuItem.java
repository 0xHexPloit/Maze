package com.telecom.maze.ui;

import java.awt.event.ActionEvent;

@SuppressWarnings("serial") 
public class SaveMenuItem extends SaveMenuItemAbstract {
	
	public SaveMenuItem(MazeEditor mazeEditor) {
		super( "Save", mazeEditor);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		mazeEditor.persistMaze();
	}
}
