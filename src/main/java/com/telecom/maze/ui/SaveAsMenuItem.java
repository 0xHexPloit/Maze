package com.telecom.maze.ui;

import java.awt.event.ActionEvent;

@SuppressWarnings("serial") 
public class SaveAsMenuItem extends SaveMenuItemAbstract {
	
	public SaveAsMenuItem(MazeEditor mazeEditor) {
		super( "Save As...", mazeEditor );
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		mazeEditor.persistAsMaze();
	}
}
