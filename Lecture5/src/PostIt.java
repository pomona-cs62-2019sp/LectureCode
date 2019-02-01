import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/** 
 * Class to create an object that looks a bit like a PostIt
 * and can be dragged around the screen.
 * @author kim
 * @version 1.0
 */

public class PostIt {
	private Rectangle2D postItShape;    		// shape of postit object
	private boolean outlineOnly = true;		// draw outline or solid?
	private Color postItColor = Color.BLACK; // color of postIt
	
	/**
	 * Create new postit
	 * @param x	-- coords of upper left corner
	 * @param y
	 * @param width	-- width and height
	 * @param height
	 * post: new postit has been created at appropriate location and size
	 */
	public PostIt(double x, double y, double width, double height) {
		postItShape = new Rectangle2D.Double(x,y,width,height);
	}

	/**
	 * Move postit to new location
	 * @param x	-- new x and y coordinates of postIt
	 * @param y
	 * post:  PostIt is same size, but now at (x,y)
	 */
	public void moveTo(double x, double y) {
		postItShape.setFrame(x,y,postItShape.getWidth(),
				postItShape.getHeight());
	}
	
	/**
	 * Move postit by amount given
	 * @param dx	  -- amount to move in x and y directions
	 * @param dy	  
	 * post:  Postit is now dx farther to right and dy farther down, but same size
	 */
	public void move(double dx, double dy) {
		this.moveTo(postItShape.getX() + dx,
					postItShape.getY() + dy);
	}
	
	/**
	 * Change color of postit
	 * @param newColor -- color to paint postit
	 * post:  Postit now has color given by newColor
	 */
	public void setColor(Color newColor) {
		postItColor = newColor;
	}
	
	/**
	 * post: set postit to state where it is being moved
	 * Results in only outline being shown
	 */
	public void moving() {
		outlineOnly = true;
	}
	
	/**
	 * post: set postit to state when not being moved
	 * Results in full postit being drawn
	 */
	public void stopped() {
		outlineOnly = false;
	}

	/**
	 * Change size of postit
	 * @param width -- new width and height of postit
	 * @param height
	 * post: Postit now has height and width given by parameters
	 */
	public void setSize(double width, double height) {
		postItShape.setFrame(postItShape.getX(),postItShape.getY(),
							width,height);
	}

	/**
	 * Determine whether point is in postit
	 * @param x	-- coordinates to check whether in postit
	 * @param y
	 * @return whether (x,y) is inside postit
	 */
	public boolean contains(double x, double y) {
		return postItShape.contains(x,y);
	}

	/**
	 * returns current x-coordinate of the upper left corner of the postit
	 * @return x-coordinate of postit
	 */
	public double getX() {
		return postItShape.getX();
	}

	/**
	 * returns current y-coordinate of the upper left corner of the postit
	 * @return y-coordinate of postit
	 */
	public double getY() {
		return postItShape.getY();
	}

	/**
	 * returns current color of the postit
	 * @return current color of postit
	 */
	public Color getColor() {
		return postItColor;
	}
 
	/**
	 * returns whether the postit is being dragged
	 * and hence whether shown only in outline form
	 * @return whether postit is being dragged (and shown in outline form)
	 */
	public boolean isChanging() {
		return outlineOnly;
	}

	/**
	 * Returns the current shape of the postit
	 * @return shape of postit
	 */
	public Shape getShape() {
		return postItShape;
	}
}
