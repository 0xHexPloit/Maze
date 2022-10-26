package com.telecom.paris.maze.ui;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial") 
public class ChooserButtonGroup extends JPanel {
	
	public ChooserButtonGroup(	MazeEditor mazeEditor, 
								MazePanel mazeDrawingPanel ) {
		super();
		
		final ButtonGroup buttonGroup = new ButtonGroup(); //only one of the four buttons to choose the type of box will be toggled at a time.
		
		JRadioButton button = new DepartureBoxButton( mazeDrawingPanel );
		add( button );
		add(Box.createRigidArea(new Dimension(0, 5)));
		buttonGroup.add( button );
		
		button = new ArrivalBoxButton( mazeDrawingPanel ) ;
		add( button );
		add(Box.createRigidArea(new Dimension(0, 5)));
		buttonGroup.add( button );
		
		button = new WallBoxButton( mazeDrawingPanel ) ;
		add(button);
		add(Box.createRigidArea(new Dimension(0, 5)));
		buttonGroup.add( button );

		button = new EmptyBoxButton( mazeDrawingPanel ) ;
		add(button);
		add(Box.createRigidArea(new Dimension(0, 10)));
		buttonGroup.add( button );

		add( new SolveMazeButton(mazeEditor));
		add(Box.createRigidArea(new Dimension(0, 10)));
		add( new ClearShortestPathButton( mazeEditor ));

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
}
