package com.telecom.paris.maze.ui;

import com.telecom.paris.maze.model.box.MazeBoxModel;
import com.telecom.paris.maze.model.MazeModel;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial") 
public class BoxPanel extends JPanel {

	private final MazeModel mazeModel;
	private final int rowIndex;
	private final int colIndex;
	
	public BoxPanel( 	final MazeModel mazeModel,
						final int rowIndex,
						final int colIndex ) {
		this.mazeModel = mazeModel;
		this.rowIndex= rowIndex; 
		this.colIndex= colIndex; 
		setBackground( getBoxColor() );
		
		addMouseListener( new BoxMouseAdapter( this ) );
	}
	
	protected MazePanel getMazePanel() {
		return (MazePanel) getParent();
	}

	private Color getBoxColor() {
		final MazeBoxModel boxModel = getMazeBox();
		
		if ( boxModel == null ) {
			return Color.WHITE;
		}
		
		if ( boxModel.isWall() ) {
			return Color.BLACK;
		}
		
		if ( boxModel.isDeparture() ) {
			return Color.GREEN;
		}

		if ( boxModel.isArrival() ) {
			return Color.RED;
		}
		
		if ( boxModel.isEmpty() ) {
			final MazePanel mazePanel = getMazePanel();
			
			if ( mazePanel == null || mazePanel.isEditing() || !boxModel.belongsToShortestPath() ) {
				return Color.WHITE;
			}

			if ( boxModel.belongsToShortestPath() ) {
				return Color.BLUE;
			}
		}

		throw new IllegalStateException( "Unable to determine color for box '" + boxModel + "'!" );
	}
	
	public MazeBoxModel getMazeBox() {
		if ( mazeModel == null ) {
			return null;
		}
		
		return mazeModel.getMazeBox(rowIndex, colIndex);
	}
	
	@Override
	public Dimension getPreferredSize() {
		final MazePanel mazePanel = getMazePanel();

		return mazePanel.computePreferredBoxSize();
	}

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(10, 10);
	}
	
	@Override 
	public Dimension getMaximumSize() {
		return new Dimension(100, 100);
	}
	
	public void notifyForUpdate() {
		repaint();
	}

	@Override
    public void repaint() {
		setBackground( getBoxColor() );
		
		super.repaint();
	}
}
