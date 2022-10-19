package com.telecom.maze.ui;

import com.telecom.maze.model.MazeBoxModel;

import java.awt.event.ActionListener;

@SuppressWarnings("serial") 
public class ArrivalBoxButton extends AbstractBoxTypeRadioButton implements ActionListener {
	
	public ArrivalBoxButton( final MazePanel mazePanel ) {
		super( "Arrival Box", mazePanel );
	}

	@Override
	public void setBoxModelType( final MazeBoxModel boxModel ) {
		if ( !boxModel.isArrival() ) {
			boxModel.setArrival();
		
			setModified( true );
		}
	}
}
