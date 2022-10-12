package com.telecom.maze.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.telecom.maze.model.MazeModel;
import com.telecom.maze.model.ModelObserver;

@SuppressWarnings("serial") 
public class MazeEditor extends JFrame implements ModelObserver {
	
	private static final String Iterator = null;
	private MazeModel maze;
	private RootPanel rootPanel;
	
	private boolean modified;
	
	private boolean editing;

	public MazeEditor(final MazeModel maze ) {
		super("Maze Editor");
		
		//Window menu bar creation
		setJMenuBar(new DrawingMenuBar(this));
		
		addWindowListener( new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				if ( !handleModified() ) {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		} );
		
		//By default, the maze drawn when the user access the app will have a 10*10 grid
		setMaze( maze );
		setModified( false );
		setEditing( false );
		
		setLocationRelativeTo(null);
		
		setVisible(true);
	}	
	
	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}
	
	protected boolean isEditing() {
		return editing;
	}
	
	protected void setEditing( final boolean editing ) {
		this.editing = editing;
	}
	
	@Override
	public final void modelStateChanged() {
		rootPanel.notifyForUpdate();
	}

	public final void newMaze() {
		int numberOfRows = Integer.parseInt(JOptionPane.showInputDialog("Number of rows?"));
		int numberOfColumns = Integer.parseInt(JOptionPane.showInputDialog("Number of columns?"));

		setMaze( getMaze().getMazeFactory().createMazeModel( numberOfRows, numberOfColumns ) );
		
		setModified( true );
	}
	
	private void setMaze( MazeModel maze ) {
		if ( this.maze != null ) {
			this.maze.removeObserver( this );
		}
		
		this.maze = maze;
		this.maze.addObserver( this );

		rootPanel = new RootPanel(this);
		setContentPane(rootPanel);
		
		pack();
		repaint();
	}
	
	public final MazeModel getMaze() {
		return maze;
	}

	public RootPanel getRootPanel() {
		return rootPanel;
	}
	
	private boolean handleModified() {
		if ( isModified() ) {
			final int response = JOptionPane.showOptionDialog(	this, 
																"Maze is not saved. Save it?",
																"Quit Application",
																JOptionPane.YES_NO_CANCEL_OPTION,
																JOptionPane.WARNING_MESSAGE, 
																null, 
																null, 
																null);
			switch (response) {
				case JOptionPane.CANCEL_OPTION :
					return false;
			}
		}
		
		return true;
	}
	
	protected void close() {
		if ( handleModified() ) {
			setVisible( false );
			dispose();
		}
	}
	
	protected void solveMaze() {
		final MazeModel model = getMaze();
		
		final List<String> errors = model.validate();
		
		if ( errors != null && !errors.isEmpty() ) {
			final StringBuilder errMessages = new StringBuilder();
			
			for ( final String message : errors ) {
				errMessages.append( message );
			}
			
			JOptionPane.showMessageDialog(	this, 
											errMessages,
 											"Error",
 											JOptionPane.ERROR_MESSAGE);
		}
		else {
			setEditing( false );

			try {
				if ( model.solve() ) {
					setModified( true );
				} 
				else {
					JOptionPane.showMessageDialog( 	this,
													"There is no path from the departure box to the arrival box.",
													"Error",
													JOptionPane.ERROR_MESSAGE );
				}
			}
			catch ( final Exception ex  ) {
				
			}
		}
	}
}
