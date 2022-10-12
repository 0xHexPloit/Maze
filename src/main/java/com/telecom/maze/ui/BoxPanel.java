package com.telecom.maze.ui;


import javax.swing.JPanel;

import com.telecom.maze.model.MazeBoxModel;
import com.telecom.maze.model.MazeModel;

import java.awt.Color;
import java.awt.Dimension;

/**
 * Each box have its own JPanel to control them easily
 * @author Idris DELSOL
 *
 */
@SuppressWarnings("serial")
public class BoxPanel extends JPanel {

    private final MazeModel mazeModel;
    private final int rowIdx;
    private final int colIdx;
	private MazeBoxModel boxModel;

	public BoxPanel(
            final MazeModel mazeModel,
            final MazeBoxModel boxModel,
            int rowIdx,
            int colIdx
    ) {
		this.boxModel= boxModel;
        this.mazeModel = mazeModel;
        this.rowIdx = rowIdx;
        this.colIdx = colIdx;

		setBackground( getBoxColor() );
		addMouseListener( new BoxMouseAdapter( this ) );
	}

	protected MazePanel getMazePanel() {
		return (MazePanel) getParent();
	}

	private Color getBoxColor() {
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
		return boxModel;
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
        // Ensuring that the mazeBox used is uptodate
        this.boxModel = this.mazeModel.getMazeBox(rowIdx, colIdx);
		repaint();
	}

	@Override
    public void repaint() {
		setBackground( getBoxColor() );

		super.repaint();
	}
}
