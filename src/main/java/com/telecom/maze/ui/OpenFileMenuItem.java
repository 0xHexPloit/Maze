package com.telecom.maze.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial") 
public class OpenFileMenuItem extends JMenuItem	implements ActionListener {

	private final MazeEditor mazeEditor;
	
	public OpenFileMenuItem(MazeEditor mazeEditor) {
		super("Open from file");
		
		this.mazeEditor = mazeEditor;
		
		addActionListener(this);
		
		setEnabled( true );
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		mazeEditor.readMaze();
	}	
}
