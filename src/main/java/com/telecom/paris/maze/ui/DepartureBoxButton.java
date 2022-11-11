package com.telecom.paris.maze.ui;

import com.telecom.paris.maze.model.box.MazeBoxModel;

@SuppressWarnings("serial") 
public class DepartureBoxButton extends AbstractBoxTypeRadioButton {
	
	public DepartureBoxButton( MazePanel mazeDrawingPanel ) {
		super( "Departure Box", mazeDrawingPanel );
	}

	@Override
	public void setBoxModelType( final MazeBoxModel boxModel ) {
		if ( !boxModel.isDeparture() ) {
			boxModel.setDeparture();
		
			setModified( true );
		}
	}
}
