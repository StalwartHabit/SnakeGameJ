/**
 * 
 */
package gameUI;

import java.awt.BorderLayout;

import gameLogic.SnakeGame;

import javax.swing.JPanel;

/**
 * @author chris
 *
 */
@SuppressWarnings("serial")
public class MainPanel extends JPanel {
	public MainPanel() {
		super();
		SnakeGame snakeGame = new SnakeGame();
		setLayout(new BorderLayout());
		GamePanel gamePanel = new GamePanel(snakeGame);
		add(gamePanel, BorderLayout.CENTER);
	}
}
