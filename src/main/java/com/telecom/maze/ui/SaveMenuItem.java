package com.telecom.maze.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

/**
 * This button allow the user to save the maze directly in the file from which it has been initialized, if such a file exists.
 * @author Idris DELSOL
 *
 */
@SuppressWarnings("serial") 
public class SaveMenuItem extends JMenuItem	implements ActionListener {
	
	private final MazeEditor mazeEditor;
	
	public SaveMenuItem(MazeEditor mazeEditor) {
		super("Save");
		
		this.mazeEditor = mazeEditor;
		
		addActionListener(this);
		
		// TODO
		setEnabled( false );
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
//		File file = drawingMaze.getMaze().getCurrentFile();
//		if(file == null) {
//			JOptionPane.showMessageDialog(drawingMaze,
//					"Please use the Save As... item instead, because this maze has no attached file by default",
//					"Error",
//					JOptionPane.ERROR_MESSAGE);
//		}
//		else {
//			try {
//				int response = 
//						JOptionPane.showInternalOptionDialog
//							(this,
//							"This action will overwrite your current backup. Continue ?",
//							"Save Maze", 
//							JOptionPane.YES_NO_CANCEL_OPTION,
//							JOptionPane.WARNING_MESSAGE, 
//							null, 
//							null, 
//							null);
//				switch(response) {
//				case JOptionPane.CANCEL_OPTION : 
//					return;
//				case JOptionPane.OK_OPTION : 
//					drawingMaze.getMaze().saveToTextFile(file);
//					break;
//				case JOptionPane.NO_OPTION :
//					return;
//				}
//				
//			}
//			catch(Exception e) {
//				JOptionPane.showMessageDialog(drawingMaze, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//			}
//			
//			JOptionPane.showMessageDialog(drawingMaze, 
//					"The maze has been successfully saved!",
//					"Maze saved",
//					JOptionPane.INFORMATION_MESSAGE);
//		}
	}
}
