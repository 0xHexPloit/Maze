package com.telecom.maze.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import com.telecom.maze.model.MazeBoxModel;

@SuppressWarnings("serial") 
public abstract class AbstractBoxTypeRadioButton extends JRadioButton implements ActionListener {
	
	private final MazePanel mazePanel;
	
	public AbstractBoxTypeRadioButton( 	final String text,
										final MazePanel mazePanel ) {
		super( text );
		
		this.mazePanel = mazePanel;
		
		addActionListener(this);
	}
	
	@Override
	public final void actionPerformed(ActionEvent evt) {
		mazePanel.setSelectedBoxTypeButton( this );
	}

	public abstract void setBoxModelType( final MazeBoxModel boxModel );
	
	protected void setModified( final boolean modified ) {
		mazePanel.setModified( modified);
	}
}
