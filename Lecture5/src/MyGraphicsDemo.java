import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * Simple graphics example using Graphics2D.
 * Written by Kim Bruce, 1/21/2007
 * Adapted by Rett Bull, 1/26/2009
 * @author dave
 * @date Further adapted on 1/25/2010
 */
public class MyGraphicsDemo extends JFrame {
	// Height and width of the window as well as the
	// width for each of 4 objects
	private static final int WINDOW_HEIGHT = 120;
	private static final int WINDOW_WIDTH = 900;
	private static final int GRID_WIDTH = WINDOW_WIDTH / 4;

	// The initial starting positions for the objects
	private static final int START_Y = 22; //<- Why is this not zero?
	private static final int START_X = 5;
	private static final int STRING_Y = WINDOW_HEIGHT - 3;

	// Normal color for drawing
	private static final Color FOREGROUND_COLOR = Color.BLACK;

	private Line2D.Double line;
	private Ellipse2D.Double ellipse;
	private Rectangle2D.Double rectangle;

	// Create a window with title "MyGraphicsDemo" and make it visible.
	// The quit button on the menu terminates the program.
	public static void main(String[] s) {
		MyGraphicsDemo f = new MyGraphicsDemo("MyGraphicsDemo");
		//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		f.setVisible(true);
	}

	/**
	 * Create the window and the basic graphic objects that we'll be using
	 *
	 * @param title
	 * 			the text to be shown on the title bar
	 */
	public MyGraphicsDemo(String title){
		super(title);

		// positions for first drawing
		int x = START_X;
		int y = START_Y;

		// width and height of rectangle to be drawn
		int rectWidth = GRID_WIDTH - 2 * x;
		int rectHeight = STRING_Y - y - 20;

		// draw Line2D.Double
		line = new Line2D.Double(x, y + rectHeight - 1, x + rectWidth, y);
		System.out.println("Line starts at "+x+", "+(y + rectHeight - 1));

		// update the x coordinate
		x = x + GRID_WIDTH;

		// draw Ellipse2D.Double
		ellipse = new Ellipse2D.Double(x, y, rectWidth,
				rectHeight);

		// again, update the x coordinate
		x = x + GRID_WIDTH;

		// draw Rectangle2D.Double
		rectangle = new Rectangle2D.Double(x, y, rectWidth,
				rectHeight);

		repaint();
	}

	/**
	 * Draw figures on the window that has graphics g
	 * Called by repaint() or whenever the screen needs to be
	 * refreshed
	 * @param g the graphics context of the current window
	 */
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		System.out.println("Called paint");

		// draw the line and text
		g2.draw(line);
		g2.drawString("Line2D", (int)line.getX1(), STRING_Y);
		
		// draw ellipse and text
		g2.draw(ellipse);
		g2.drawString("Ellipse2D", (int)ellipse.getX(), STRING_Y);

		// draw the rectangle and text
		g2.draw(rectangle);
		g2.drawString("Rectangle2D", (int)rectangle.getX(), STRING_Y);

		int oldX = (int) rectangle.getX();
		// move the rectangle, make it red, and outline it in blue
		int x = oldX + GRID_WIDTH;

		rectangle.setFrame(x, START_Y,
							rectangle.getWidth(), rectangle.getHeight());

		g2.setPaint(Color.RED);
		g2.fill(rectangle);
		g2.setPaint(Color.BLUE);
		g2.draw(rectangle);

		g2.setPaint(FOREGROUND_COLOR); // set the color back to the foreground color before
									   // drawing the final text (otherwise, it would be BLUE
		g2.drawString("Filled Rectangle2D", x , STRING_Y);

		rectangle.setFrame(oldX, START_Y,
				rectangle.getWidth(), rectangle.getHeight());

	}
}
