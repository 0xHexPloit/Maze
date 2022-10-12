package com.telecom.maze.ui;

import com.telecom.maze.model.MazeBoxModel;

/**
 * If the user clicks a first type on the "EBoxButton", and then on a non-empty box, the box will turn to an empty box.
 * If he or she then click on an empty box, it will turn it into  new empty box.
 * If he or she click again on the "EBoxButton" and then on the maze, nothing will happen.
 * @author Idris DELSOL
 *
 */
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
