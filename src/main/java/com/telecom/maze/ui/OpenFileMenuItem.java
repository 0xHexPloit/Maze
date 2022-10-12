package com.telecom.maze.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial") 
public class OpenFileMenuItem extends JMenuItem	implements ActionListener {

	private final MazeEditor mazeEditor;
	
	public OpenFileMenuItem(MazeEditor drawingMaze) {
		super("Open from file");
		
		this.mazeEditor = drawingMaze;
		
		addActionListener(this);
		
		// TODO
		setEnabled( false );
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		JFileChooser chooser = new JFileChooser(); //This class enable us to open a file explorer for a more ergonomic design. 
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "text only", "txt");
	    chooser.setFileFilter(filter);
	    chooser.setDialogType(JFileChooser.OPEN_DIALOG);
	    int returnVal = chooser.showOpenDialog(mazeEditor);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	File file = chooser.getSelectedFile();
	        System.out.println("You chose to open this file: " + file.getName());
//	        try {
//				int[] numberOfRowsAndColumnsInFile = Maze.numberOfRowsAndColumnsOfFile(file);
//				int numberOfRows = numberOfRowsAndColumnsInFile[0];
//				int numberOfColumns = numberOfRowsAndColumnsInFile[1];
//				Maze newMaze = new Maze(numberOfRows, numberOfColumns, drawingMaze);
//				newMaze.initFromTextFile(file);
				
				//drawingMaze.setMaze(newMaze);
//			} 
//	    	catch(Exception e) {
//				JOptionPane.showMessageDialog(drawingMaze, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//			}
		}
	}	
}
