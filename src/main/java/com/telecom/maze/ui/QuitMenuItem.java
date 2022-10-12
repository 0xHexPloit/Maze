package com.telecom.maze.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

@SuppressWarnings("serial") 
public class QuitMenuItem extends JMenuItem implements ActionListener {
	
	private final MazeEditor mazeEditor;
	
	public QuitMenuItem(MazeEditor drawingMaze) {
		super("Quit");
		
		this.mazeEditor = drawingMaze;
		
		addActionListener(this);
	}
	
	@Override
	public final void actionPerformed(ActionEvent evt) {
		mazeEditor.close();
	}
}
