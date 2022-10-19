package com.telecom.maze.ui;

import com.telecom.maze.model.MazeBoxModel;

@SuppressWarnings("serial") 
public class EmptyBoxButton extends AbstractBoxTypeRadioButton {
	
	public EmptyBoxButton( MazePanel mazeEditor ) {
		super("Empty Box", mazeEditor );
	}

	@Override
	public void setBoxModelType( final MazeBoxModel boxModel ) {
		if ( !boxModel.isEmpty() ) {
			boxModel.setEmpty();
			
			setModified( true );
		}
	}
}
