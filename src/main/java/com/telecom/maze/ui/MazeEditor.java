package com.telecom.maze.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.telecom.maze.model.MazeModel;
import com.telecom.maze.model.MazePersistenceManager;
import com.telecom.maze.model.ModelObserver;

@SuppressWarnings("serial") 
public class MazeEditor extends JFrame implements ModelObserver {
	
	private MazeModel maze;
	
	private final MazePersistenceManager persistenceManager;

	private RootPanel rootPanel;
	
	private boolean modified;
	
	private boolean editing;

	public MazeEditor(	final MazeModel maze,
						final MazePersistenceManager persistenceManager ) {
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
		this.persistenceManager = persistenceManager;
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
		
		getJMenuBar().notifyForUpdate();
	}
	
	@Override
    public DrawingMenuBar getJMenuBar() {
		return (DrawingMenuBar) super.getJMenuBar();
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
	
	public final MazePersistenceManager getPersistenceManager() {
		return persistenceManager;
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
				case JOptionPane.YES_OPTION:
					persistMaze();
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
	
	protected void persistAsMaze() {
		final MazeModel maze = getMaze();
		final String currentId = maze.getId();
		
		maze.setId( null );
		
		try {
			doPersistMaze();
		}
		catch ( final IOException ex ) {
			handleException( ex );

			maze.setId( currentId );
		}
	}
	
	private void handleException( final Exception ex ) {
		JOptionPane.showMessageDialog( this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE );
	}

	protected void readMaze() {
		try {
			setMaze( getPersistenceManager().read( null ) );
		}
		catch ( final IOException ex ) {
			handleException( ex );
		}
	}
	
	protected void persistMaze() {
		try {
			doPersistMaze();
		}
		catch ( final IOException ex ) {
			handleException( ex );
		}
	}

	private void doPersistMaze() 
	throws IOException {
		final MazePersistenceManager persistenceManager = getPersistenceManager();
		
		persistenceManager.persist( getMaze() );
	}
}
