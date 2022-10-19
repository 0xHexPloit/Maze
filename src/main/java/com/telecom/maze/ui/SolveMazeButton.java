package com.telecom.maze.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial") 
public class SolveMazeButton extends JButton implements ActionListener {
	
	private MazeEditor mazeEditor;
	
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
