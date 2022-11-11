package com.telecom.paris.maze.ui;

import com.telecom.paris.maze.model.box.MazeBoxModel;

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
