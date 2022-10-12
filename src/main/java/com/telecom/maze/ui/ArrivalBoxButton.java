package com.telecom.maze.ui;

import java.awt.event.ActionListener;

import com.telecom.maze.model.MazeBoxModel;

/**
 * If the user clicks a first type on the "ABoxButton", and then on a non-arrival box, the box will turn to an arrival box.
* If he or she then click on an arrival Box, it will turn to an empty box.
* If he or she click again on the "ABoxButton" and then on the maze, nothing will happen.
 * @author Idris DELSOL
 *
 */
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
