package com.telecom.paris.maze.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoxMouseAdapter extends MouseAdapter {
	
	private BoxPanel boxPanel;

	public BoxMouseAdapter(	BoxPanel boxPanel ) {
		this.boxPanel = boxPanel;
	}
	
	private MazePanel getMazePanel() {
		return (MazePanel) boxPanel.getParent();
	}
	
	/**
	 * When the mouse enters a box, we want its border to turn to the color of the box type selected.
	 */
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		MazeModel.BoxType typeSelected = maze.getSelectedBoxType();
//		if(typeSelected == null) {
//			Border selectedBorder = new MatteBorder(1,1,1,1, Color.PINK);
//			boxPanel.setBorder(selectedBorder);
//		} 
//		else {
//			switch(typeSelected) {
//				case ARRIVAL : 
//					Border selectedBorderA = new MatteBorder(1,1,1,1, Color.GREEN);
//					boxPanel.setBorder(selectedBorderA);
//					
//					break;
//				case DEPARTURE :
//					Border selectedBorderD = new MatteBorder(1,1,1,1, Color.BLUE);
//					boxPanel.setBorder(selectedBorderD);
//					
//					break;
//				case WALL :  
//					Border selectedBorderW = new MatteBorder(1,1,1,1, Color.BLACK);
//					boxPanel.setBorder(selectedBorderW);
//					
//					if(mazeDrawingPanel.getIsMousePressed()) {
//						handleEOrWBox("W");
//					}
//				   
//					break;
//				case EMPTY :
//					Border selectedBorderE = new MatteBorder(1,1,1,1, Color.WHITE);
//					boxPanel.setBorder(selectedBorderE);
//					
//					if (mazeDrawingPanel.getIsMousePressed()) {
//						handleEOrWBox("E");
//					}
//					
//					break;
//			}
//		}
//	}
	
//	@Override
//	public void mouseExited(MouseEvent e) {
//		Border unSelectedBorder = new MatteBorder(1, 1, 1, 1, Color.GRAY);
//		boxPanel.setBorder(unSelectedBorder);
//	}
	
	/**
	 * We want to set a new type to the box that has been pressed on. 
	 */
	@Override
	public void mousePressed( final MouseEvent event ) {
		if ( event.getButton() == MouseEvent.BUTTON1 ) {
			final MazePanel mazePanel = getMazePanel();
			final AbstractBoxTypeRadioButton radioButton = mazePanel.getSelectedBoxTypeButton();
			
			if ( radioButton != null ) {
				radioButton.setBoxModelType( boxPanel.getMazeBox() );
			}
		}
	}
//	
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		if ( e.getButton() == MouseEvent.BUTTON1 ) {
//			mazeDrawingPanel.setMousePressed(false);
//	    }
//	}
}
