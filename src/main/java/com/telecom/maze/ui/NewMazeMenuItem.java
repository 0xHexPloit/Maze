package com.telecom.maze.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial") 
public class NewMazeMenuItem extends JMenuItem implements ActionListener {

	private final MazeEditor mazeEditor;
	
	public NewMazeMenuItem(MazeEditor drawingMaze) {
		super("Create a new maze");
		
		this.mazeEditor = drawingMaze;
		
		addActionListener(this);														  
	}
	
	@Override
	public final void actionPerformed(ActionEvent e) {
		mazeEditor.newMaze();
	}
}
