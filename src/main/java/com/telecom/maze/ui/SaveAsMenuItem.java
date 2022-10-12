package com.telecom.maze.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

@SuppressWarnings("serial") 
public class SaveAsMenuItem extends JMenuItem implements ActionListener {
	
	private final MazeEditor mazeEditor;
	
	public SaveAsMenuItem(MazeEditor mazeEditor) {
		super("Save As...");
		this.mazeEditor = mazeEditor;
		
		addActionListener(this);
		
		// TODO
		setEnabled( false );
	}
	
	
	public void actionPerformed(ActionEvent evt) {
//		JFileChooser chooser = new JFileChooser();
//	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
//	        "text only", "txt");
//	    chooser.setFileFilter(filter);
//	    chooser.setDialogType(JFileChooser.SAVE_DIALOG);
//	    int returnVal = chooser.showSaveDialog(drawingMaze);
//	    if(returnVal == JFileChooser.APPROVE_OPTION) {
//	    	File file = chooser.getSelectedFile();
//	        System.out.println("You chose to open this file: " + file.getName());
//	        try {
//				drawingMaze.getMaze().saveToTextFile(file);
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(drawingMaze, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//				e.printStackTrace();
//			}
//	    }	
	}
}
