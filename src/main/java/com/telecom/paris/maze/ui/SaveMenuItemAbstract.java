package com.telecom.paris.maze.ui;

import javax.swing.*;
import java.awt.event.ActionListener;

@SuppressWarnings("serial") 
public abstract class SaveMenuItemAbstract extends JMenuItem implements ActionListener {
	
	protected final MazeEditor mazeEditor;
	
	protected SaveMenuItemAbstract( final String text,
									final MazeEditor mazeEditor ) {
		super( text );
		
		this.mazeEditor = mazeEditor;
		
		setEnabled( mazeEditor.isModified() );

		addActionListener( this);
	}
	
	@Override
	public void repaint() {
		final boolean enabled;
		
		if ( mazeEditor == null ) {
			enabled = false;
		}
		else {
			enabled = mazeEditor.isModified();
		}
		
		setEnabled( enabled );

		super.repaint();
	}
}
