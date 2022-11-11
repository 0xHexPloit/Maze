package com.telecom.paris.maze.ui;

import com.telecom.paris.maze.model.box.MazeBoxModel;

@SuppressWarnings("serial") 
public class WallBoxButton extends AbstractBoxTypeRadioButton {
	
	public WallBoxButton( MazePanel mazePanel ) {
		super("Wall Box", mazePanel );
	}

	@Override
	public void setBoxModelType( final MazeBoxModel boxModel ) {
		if ( !boxModel.isWall() ) {
			boxModel.setWall();
		
			setModified( true );
		}
	}
}
