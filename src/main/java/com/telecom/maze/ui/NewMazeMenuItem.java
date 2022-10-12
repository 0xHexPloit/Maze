package com.telecom.maze.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

/**
 * This menu item shall enable the user to generate a new maze. 
 * The user will have to prompt the dimensions of the maze in a dialogue box
 * @author Idris DELSOL
 *
 */
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
