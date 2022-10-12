package com.telecom.maze.ui;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Menu item that shows useful informations on the current maze.
 * @author Idris DELSOL
 *
 */
@SuppressWarnings("serial") 
public class InformationMazeMenuItem extends JMenuItem implements ActionListener {
	
	private final MazeEditor mazeEditor;
	
	public InformationMazeMenuItem(MazeEditor mazeEditor) {
		super("Informations about the maze");
		
		this.mazeEditor = mazeEditor;
		
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		int numberOfRows = mazeEditor.getMaze().getHeigth();
		int numberOfColumns = mazeEditor.getMaze().getWidth();
		String messageDimension = "This maze has dimension : "+Integer.toString(numberOfRows)+" x "+Integer.toString(numberOfColumns);
		int numberOfBoxes = mazeEditor.getMaze().getNumberOfBoxes();
		String messageNumberOfBoxes = "This maze contains "+Integer.toString(numberOfBoxes)+" boxes with this distribution: "; 
		//int[] numberOfBoxesByType = drawingMaze.getMaze().getNumberOfBoxesByType();
//		String messageABox = Integer.toString(numberOfBoxesByType[0]) + " arrival boxe(s).";
//		String messageDBox = Integer.toString(numberOfBoxesByType[1]) + " departure boxe(s).";
//		String messageEBox = Integer.toString(numberOfBoxesByType[2]) + " empty boxe(s).";
//		String messageWBox = Integer.toString(numberOfBoxesByType[3]) + " wall boxe(s).";
		String[] messageArray = {messageDimension, messageNumberOfBoxes/*, messageABox, messageDBox, messageEBox, messageWBox*/};
		
		JOptionPane.showMessageDialog(mazeEditor, 
									messageArray, 
									"Informations about the maze", 
									JOptionPane.INFORMATION_MESSAGE);
	}
}
