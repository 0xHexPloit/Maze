package com.telecom.paris.maze.ui;

import com.telecom.paris.maze.model.box.MazeBoxModel;

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
