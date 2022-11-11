package com.telecom.paris.maze.ui;

import com.telecom.paris.maze.model.MazeModel;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

@SuppressWarnings("serial") 
public class MazePanel extends JPanel {
	
	private final MazeEditor mazeEditor;

	private BoxPanel[][] boxPanels;
	
	private AbstractBoxTypeRadioButton selectedBoxTypeButton;

	public MazePanel(MazeEditor mazeEditor) {
		super();

		this.mazeEditor = mazeEditor;
		final MazeModel maze = mazeEditor.getMaze();
		final int numberOfRows = maze.getHeight();
		final int numberOfColumns = maze.getWidth();
		boxPanels = new BoxPanel[ numberOfRows ][ numberOfColumns ];
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		setPreferredSize(new Dimension(300, 300));
		
		for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++ ) {
			for (int colIndex = 0; colIndex < numberOfColumns; colIndex++) {
				gbc.gridx = colIndex;
				gbc.gridy = rowIndex;
				
				final BoxPanel boxPanel = new BoxPanel( maze, rowIndex, colIndex );
				boxPanel.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));

				add(boxPanel, gbc);

				boxPanels[rowIndex][colIndex] = boxPanel;
			}
		}
		
		setEditing( false );
	}
	
	protected boolean isEditing() {
		return mazeEditor.isEditing();
	}
	
	protected void setEditing( final boolean editing ) {
		mazeEditor.setEditing( editing );
	}
	
	protected Dimension computePreferredBoxSize() {
		int mazeDrawingPanelWidth = getWidth();
		int mazeDrawingPanelHeight = getHeight();
		
		final MazeModel maze = mazeEditor.getMaze();
		int numberOfRows = maze.getHeight();
		int numberOfColumns = maze.getWidth();
		
		return new Dimension(mazeDrawingPanelWidth/numberOfColumns, mazeDrawingPanelHeight/numberOfRows);
	}
	
	public void setModified( final boolean modified ) {
		mazeEditor.setModified( modified );
	}
	
	public AbstractBoxTypeRadioButton getSelectedBoxTypeButton() {
		return selectedBoxTypeButton;
	}

	public void setSelectedBoxTypeButton(AbstractBoxTypeRadioButton selectedBoxTypeButton) {
		this.selectedBoxTypeButton = selectedBoxTypeButton;
		
		setEditing( true );
	}

	public final void notifyForUpdate() {
		for( int rowIndex = 0; rowIndex < boxPanels.length; rowIndex++) {
			for( BoxPanel boxPanel : boxPanels[rowIndex] ) {
				boxPanel.notifyForUpdate();
			}
		}
	}

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(50, 50);
	}
	
	public BoxPanel[][] getBoxPanels(){
		return boxPanels;
	}
	
	public BoxPanel getBox(int row, int column) {
		return boxPanels[row][column];
	}
}
