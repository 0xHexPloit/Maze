package com.telecom.maze.ui;

import java.awt.BorderLayout;


import javax.swing.JPanel;

@SuppressWarnings("serial") 
public class RootPanel extends JPanel {
	
	private final ChooserButtonGroup chooserButtonGroup;
	private final MazePanel mazePanel;
	
	public RootPanel( MazeEditor mazeEditor ) {
		super();
		
		setLayout(new BorderLayout());
		mazePanel = new MazePanel(mazeEditor);
		add(mazePanel, BorderLayout.CENTER);
		
		chooserButtonGroup = new ChooserButtonGroup(mazeEditor, mazePanel);
		add(chooserButtonGroup, BorderLayout.EAST);
	}

	public final void notifyForUpdate() {
		mazePanel.notifyForUpdate();	
	}
}
