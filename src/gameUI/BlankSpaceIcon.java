/**
 * 
 */
package gameUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;

/** Creates an icon for empty space
 * @author chris
 *
 */
public class BlankSpaceIcon implements Icon {
	public static final Color BACKGROUND_COLOUR = Color.WHITE;
	private int width;
	private int height;

	public BlankSpaceIcon(int width, int height) {
		this.height = height;
		this.width = width;
	}
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2d = (Graphics2D) g;
		clearIcon(x, y, g2d);
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
}
