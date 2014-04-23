/**
 * 
 */
package gameUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gameLogic.*;
import gameLogic.Snake.Direction;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/** Creates a panel that represents the game to the user.
 * @author chris
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	public static final int GRID_BLOCK_SIZE = 30;
	
	private static final String[] KEYS = {"UP", "DOWN", "LEFT", "RIGHT"};
	
	private final int DELAY = 200;
	
	private SnakeGame snakeGame;
	private JLabel[][] gridContents;
	
	private Timer timer;
	
	
	public GamePanel (SnakeGame snakeGame) {
		super();
		this.snakeGame = snakeGame;
		
		snakeGame.addSnakeListener(snakeListener());
		snakeGame.addRemovalListener(removalListener());
		snakeGame.addAppleListener(appleListener());
		
		int horizontalSize = SnakeGame.HORIZONTAL_SIZE;
		int verticalSize = SnakeGame.VERTICAL_SIZE;
		
		gridContents = new JLabel[horizontalSize][verticalSize];
		setLayout(new GridLayout(verticalSize, horizontalSize));
		
		for (int row = 0; row < verticalSize; row++) {
			for (int col = 0; col < horizontalSize; col++) {
				addIconLabel(row, col);
			}
		}
		drawSnake();
		setIcon(snakeGame.getApple(), 
				new AppleIcon(GRID_BLOCK_SIZE, GRID_BLOCK_SIZE));
		
		registerKeyPresses();
		timer = createTimer();
		
	}
	private void addIconLabel(int row, int col) {
		BlankSpaceIcon icon = new BlankSpaceIcon(GRID_BLOCK_SIZE, GRID_BLOCK_SIZE);
		JLabel iconLabel = new JLabel();
		iconLabel.setIcon(icon);
		gridContents[col][row] = iconLabel;
		add(iconLabel);
	}
	private void drawSnake() {
		for (Coordinate coordinate : snakeGame) {
			setIcon(coordinate, new SnakeBodyIcon(GRID_BLOCK_SIZE, GRID_BLOCK_SIZE));
		}
	}
	private void setIcon(Coordinate coordinate, Icon icon) {
		int x = coordinate.getX();
		int y = coordinate.getY();
		gridContents[x][y].setIcon(icon);
	}
	public void registerKeyPresses() {
		for (int i = 0; i < KEYS.length; i++) {
			String key = KEYS[i];
			this.getInputMap().put(KeyStroke.getKeyStroke(key), key);
			this.getActionMap().put(key, getKeyListener(key));
		}
	}

	public AbstractAction getKeyListener(final String move) {
		return new AbstractAction() {
			public void actionPerformed(ActionEvent evt) {
				switch (move) {
				case "UP": 
					snakeGame.setCurrentDirection(Direction.NORTH);
					timer.start();
					break;
				case "DOWN": 
					snakeGame.setCurrentDirection(Direction.SOUTH);
					timer.start();
					break;
				case "LEFT": 
					snakeGame.setCurrentDirection(Direction.WEST);
					timer.start();
					break;
				case "RIGHT": 
					snakeGame.setCurrentDirection(Direction.EAST);
					timer.start();
					break;
				default:
					break;
				}
			}
		};
	}
	
	private ChangeListener snakeListener() {
		return new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				Object source = e.getSource();
				if (source instanceof Coordinate) {
					Coordinate coordinate = (Coordinate) source;
					setIcon(coordinate, new SnakeBodyIcon(GRID_BLOCK_SIZE, GRID_BLOCK_SIZE));
				} else {
					assert false;
				}
			}
		};
	}
	private ChangeListener removalListener() {
		return new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				Object source = e.getSource();
				if (source instanceof Coordinate) {
					Coordinate coordinate = (Coordinate) source;
					setIcon(coordinate, new BlankSpaceIcon(GRID_BLOCK_SIZE, GRID_BLOCK_SIZE));
				} else {
					assert false;
				}
			}
		};
	}
	private ChangeListener appleListener() {
		return new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				setIcon(snakeGame.getApple(), 
						new AppleIcon(GRID_BLOCK_SIZE, GRID_BLOCK_SIZE));
			}
		};
		
	}
	private Timer createTimer() {
		ActionListener updateText = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				snakeGame.gameTurn();
			}
		};
		return new Timer(DELAY, updateText);
	}
}
