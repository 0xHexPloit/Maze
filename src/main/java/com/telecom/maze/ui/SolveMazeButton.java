package com.telecom.maze.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial") 
public class SolveMazeButton extends JButton implements ActionListener {
	
	private final MazeEditor mazeEditor;
	
	public SolveMazeButton(MazeEditor mazeEditor) {
		super("Solve Maze");
		
		this.mazeEditor = mazeEditor;
		
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		mazeEditor.solveMaze();
	}
}
