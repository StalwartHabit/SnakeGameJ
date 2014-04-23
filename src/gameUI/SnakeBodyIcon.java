/**
 * 
 */
package gameUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;

/** Creates the icon that represents the SnakesBody.
 * @author chris
 *
 */
public class SnakeBodyIcon implements Icon {
	public static final int WIDTH_SPACING_RATIO = 15;
	public static final int HEIGHT_SPACING_RATIO = 15;
	public static final Color BACKGROUND_COLOUR = Color.WHITE;
	public static final Color SNAKE_COLOUR = Color.BLACK;
	
	private int width;
	private int height;
	
	public SnakeBodyIcon(int width, int height) {
		this.height = height;
		this.width = width;
	}
	
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2d = (Graphics2D) g;
		clearIcon(x, y, g2d);
		
		int widthSpacing = width / WIDTH_SPACING_RATIO;
		int heightSpacing = height / HEIGHT_SPACING_RATIO;
		
		int rectangleWidth = width - (widthSpacing * 2);
		int rectangleHeight = height - (heightSpacing * 2);
		
		drawRectangle(g2d, 
				widthSpacing,
				heightSpacing, 
				rectangleWidth, 
				rectangleHeight,
				SNAKE_COLOUR);
	}

	@Override
	public int getIconWidth() {
		return width;
	}

	@Override
	public int getIconHeight() {
		return height;
	}
	
	private void clearIcon(int x, int y, Graphics2D g2d) {
		g2d.setBackground(BACKGROUND_COLOUR);
		g2d.clearRect(x, y, getIconWidth(), getIconHeight());
	}
	private void drawRectangle (
			Graphics2D g2d,
			int x,
			int y,
			int width,
			int height,
			Color color) {
		g2d.setColor(color);
		Shape rectangle = new Rectangle2D.Double(x, y, width, height);
		g2d.fill(rectangle);
		g2d.draw(rectangle);
	}
}
