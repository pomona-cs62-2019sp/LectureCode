
/**
 * Post-It program for Java. Lets user put rectangles on the screen, move
 * them, and remove them. 
 * @author Kim Bruce. 
 * @version Revised 1/23/2011.
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class PostItApplication extends JFrame implements ActionListener {
	private static final int NUM_CONTROLS = 3; // number of Buttons & other
												// controls
	private static final int HGAP = 10; // gap between buttons
	private static final int VGAP = 40;
	private JButton addJButton; // JButton to add window
	private JButton deleteJButton; // JButton to delete window
	private JComboBox<String> colorSelect; // combo box to select color
	// ArrayList of objects on canvas, top-most object is last in List
	private List<PostIt> postItList = new ArrayList<PostIt>(); 

	private boolean adding = false; // Was "add" JButton pushed?
	private boolean deleting = false; // Was "delete" JButton pushed?
	private boolean moving = false; // Was in middle of moving postits?

	private double lastX, lastY; // last mouse position
	private PostIt postIt; // rectangle being drawn or moved

	private Color currentColor = Color.BLACK; // color for drawing
	private DrawingCanvas canvas;  // where drawing takes place

	/**
	 * Create drawing controls and add to window
	 */
	public PostItApplication() {
		
		// sets title on window
		super("PostIt Application");
		
		// Set up add JButton
		addJButton = new JButton("Add");
		addJButton.addActionListener(this);
		
		// Set up delete JButton
		deleteJButton = new JButton("Delete");
		deleteJButton.addActionListener(this);
		
		// Set up color choice
		colorSelect = new JComboBox<String>();
		colorSelect.addItem("Black");
		colorSelect.addItem("Red");
		colorSelect.addItem("Blue");
		colorSelect.addItem("Green");
		colorSelect.addItem("Cyan");
		colorSelect.addActionListener(this);
		
		// Set up panel to hold JButtons
		JPanel JButtonPanel = new JPanel(); // Panel to hold JButtons
		JButtonPanel.setLayout(new GridLayout(1, NUM_CONTROLS, HGAP, VGAP));
		JButtonPanel.add(addJButton);
		JButtonPanel.add(deleteJButton);
		JButtonPanel.add(colorSelect);
		
		// add JButtons and canvas panels
		add(JButtonPanel, BorderLayout.SOUTH);
		canvas = new DrawingCanvas();
		add(canvas, BorderLayout.CENTER);
		validate();
	}

	/**
	 * @param e  the event that triggered the event -- a button or 
	 *           combobox action             
	 * post: If clicked "add" button then next mouse press & drag
	 *       will create new postit If clicked delete, then next mouse
	 *       press will delete a postit under the mouse. If chose new color,
	 *       then remember so new postits will have that color
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addJButton) {
			adding = true;
			deleting = false;
		} else if (e.getSource() == deleteJButton) {
			if (postItList.isEmpty()) {
				System.out.println("Nothing to delete!");
			} else {
				deleting = true;
				adding = false;
			}
		} else if (e.getSource() == colorSelect) {
			String colorName = (String) colorSelect.getSelectedItem();
			if (colorName.equals("Black")) {
				currentColor = Color.BLACK;
			} else if (colorName.equals("Red")) {
				currentColor = Color.RED;
			} else if (colorName.equals("Blue")) {
				currentColor = Color.BLUE;
			} else if (colorName.equals("Green")) {
				currentColor = Color.GREEN;
			} else if (colorName.equals("Cyan")) {
				currentColor = Color.CYAN;
			}
		}
	}

	/**
	 * @param x, y
	 *            location where looking for postit
	 * @return index of top-most postit containing pt or -1 
	 *         if pt is in no postit
	 */
	private int findWindowIndex(double x, double y) {
		int windowIndex = postItList.size() - 1;
		while (windowIndex >= 0
				&& !(postItList.get(windowIndex)).contains(x, y)) {
			windowIndex--;
		}
		return windowIndex;
	}

	/**
	 * pre: 0 <= windowIndex < size()
	 * 
	 * @param postItIndex
	 *            index of postit to be moved 
	 * post: postit corresponding to postItIndex is 
	 *       moved to first element of list. 
	 *       postIt is reset to that postit.
	 */
	private void moveToTop(int postItIndex) {
		assert 0 <= postItIndex && postItIndex < postItList.size() : "Internal error, index too big";
		postIt = postItList.remove(postItIndex);
		postItList.add(postIt);
	}

	/**
	 * pre: 0 <= windowIndex < size()
	 * 
	 * @param postItIndex
	 *        index of postit to be removed 
	 * post: postit corresponding to
	 *       postItIndex is removed from list
	 */
	private void removeWindow(int postItIndex) {
		assert 0 <= postItIndex && postItIndex < postItList.size() : 
						"Internal error, index too big";
		postItList.remove(postItIndex);
	}

	// main method to run program as application 
	public static void main(String[] args) {
		PostItApplication myApp = new PostItApplication(); // define window
		myApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myApp.setSize(400, 400);
		myApp.setVisible(true); // usual step to make frame visible

	} // end main

	/**
	 * Extension of JPanel for drawing postits
	 * @author kim
	 *
	 */
	class DrawingCanvas extends JPanel {  // inner class of PostItApplication
		// Create drawing canvas and add mouse listeners
		public DrawingCanvas() {
			addMouseListener(new DrawingMouseListener());
			addMouseMotionListener(new DrawingMouseMotionListener());
		}
		
		/**
		 * Update screen with new locations of all postits
		 * @param g
		 * 		"Graphics context" -- where writing is done
		 */
		public void paint(Graphics g) {
			super.paint(g);
			Graphics2D g2 = (Graphics2D) g;
			for (PostIt note : postItList) {
				g2.setPaint(note.getColor());
				if (note.isChanging()) {
					g2.draw(note.getShape());
				} else {
					g2.fill(note.getShape());
				}
			}
		}


	}

	/** 
	 * class with methods to handle mouse presses and releases
	 * @author kim
	 *
	 */
	class DrawingMouseListener extends MouseAdapter {
		/**
		 * @param evt  the event triggered by mouse press
		 * post: if adding then create new postit at current mouse position 
		 *       if deleting then find topmost postit clicked on and remove 
		 *       if moving then find topmost postit clicked on and change 
		 *       to outline form
		 */
		public void mousePressed(MouseEvent evt) {
			if (adding) {
				postIt = new PostIt(evt.getX(), evt.getY(), 2, 2);
				postIt.setColor(currentColor);
				postItList.add(postIt);
			} else if (deleting) {
				// find topmost postit under mouse
				int windowIndex = findWindowIndex(evt.getX(), evt.getY()); 
				if (windowIndex != -1) { // there is a postit under mouse
					removeWindow(windowIndex);
				}
			} else { // defaults is moving if didn't just click a button
				// find topmost postit under mouse
				int windowIndex = findWindowIndex(evt.getX(), evt.getY()); 
				if (windowIndex != -1) { // there is a postit under mouse
					moving = true;
					moveToTop(windowIndex); // move found postit to top and
											// postIt to that elt
					postIt.moving(); // shift to frame-only view
					lastX = evt.getX();
					lastY = evt.getY();
				}
			}
			canvas.repaint();
		}

		/**
		 * @param pos
		 *            current mouse postion post: if adding then add rectangle
		 *            to list if moving then show filled rectangle
		 */
		public void mouseReleased(MouseEvent evt) {
			if (adding) {
				adding = false;
				postIt.stopped(); // change to filled version
			} else if (moving) {
				postIt.stopped();
				moving = false;
			} else {
				deleting = false;
			}
			canvas.repaint();
		}
	}
	
	class DrawingMouseMotionListener extends MouseMotionAdapter {
		/**
		 * @param pos
		 *            current mouse location post: if adding then resize
		 *            rectangle to reach to new mouse posn (if below and to
		 *            right of starting position) if moving then move by amount
		 *            of mouse motion
		 */
		public void mouseDragged(MouseEvent evt) {
			double x = evt.getX(); // coords of mouse
			double y = evt.getY();
			if (adding) { // reset bounds of rect
				double newWidth = Math.max(0, x - postIt.getX());
				double newHeight = Math.max(0, y - postIt.getY());
				postIt.setSize(newWidth, newHeight);
			} else if (moving) { // move by amount of last mouse motion
				postIt.move(x - lastX, y - lastY);
				lastX = x; // remember this mouse position
				lastY = y;
			}
			canvas.repaint();
		}
	
	}

}