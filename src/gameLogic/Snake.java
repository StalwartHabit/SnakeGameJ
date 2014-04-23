/**
 * 
 */
package gameLogic;

import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**This class holds the position of the snake and holds the methods 
 * for moving it and growing it
 * @author chris
 * 
 */
public class Snake {
	private static final String SNAKE_HITS_SELF_MESSAGE 
		= "Snake hit its own body, Game Over!";
	private static final String SNAKE_HITS_WALL_MESSAGE 
		= "Snake has hit a wall, Game Over!";
	public static enum Direction {NORTH, SOUTH, EAST, WEST, NONE};
	private static final int STARTING_SIZE = 5;
	
	private final int verticalSize;
	private final int horizontalSize;
	
	private int snakeSize;
	private ArrayList<Coordinate> snakeBody = new ArrayList<Coordinate>();
	
	private ArrayList<ChangeListener> snakeListeners = new ArrayList<ChangeListener>();
	private ArrayList<ChangeListener>  removalListeners = new ArrayList<ChangeListener>();
	
	public Snake(int verticalSize, int horizontalSize) {
		snakeSize = STARTING_SIZE;
		this.verticalSize = verticalSize;
		this.horizontalSize = horizontalSize;
		
		snakeStartingState();
	}
	private void snakeStartingState() {
		int tailX = horizontalSize / 2;
		int tailY = verticalSize - 2;
		for (int i = 0; i < 5; i++) {
			snakeBody.add(new Coordinate(tailX, tailY - i));
		}
	}
	private void removeTailLocation() {
		ChangeEvent removalEvent = new ChangeEvent(snakeBody.get(0));
		for (ChangeListener listener : removalListeners) {
			listener.stateChanged(removalEvent);
		}
		snakeBody.remove(0);
	}
	public void moveSnake(Direction direction) {
		Coordinate head = snakeBody.get(snakeSize - 1);
		switch (direction) {
		case NORTH:
			Coordinate northCoordinate =
				new Coordinate(head.getX(), head.getY() - 1);
			addHead(northCoordinate);
			break;
		case SOUTH: 
			Coordinate southCoordinate = 
				new Coordinate(head.getX(), head.getY() + 1);
			addHead(southCoordinate);
			break;
		case EAST:
			Coordinate eastCoordinate = 
				new Coordinate(head.getX() + 1, head.getY());
			addHead(eastCoordinate);
			break;
		case WEST:
			Coordinate westCoordinate = 
				new Coordinate(head.getX() - 1, head.getY());
			addHead(westCoordinate);
			break;
		default:
			assert false;
		}
	}
	private void addHead(Coordinate coordinate) {
		checkBodyCollision(coordinate);
		checkWallCollision(coordinate);
		snakeBody.add(coordinate);
		ChangeEvent snakeEvent = new ChangeEvent(coordinate);
		for (ChangeListener listener : snakeListeners) {
			listener.stateChanged(snakeEvent);
		}
	}
	public Coordinate getHead() {
		return snakeBody.get(snakeSize - 1);
	}
	public void growSnake(boolean isGrown) {
		if (isGrown) {
			snakeSize++;
		} else {
			removeTailLocation();
		}
	}
	private void checkBodyCollision(Coordinate head) {
		if (snakeBody.contains(head)) {
			throw new GameOverException(SNAKE_HITS_SELF_MESSAGE);
		}
	}
	private void checkWallCollision(Coordinate head) {
		int x = head.getX();
		int y = head.getY();
		boolean isOutBoundsVerticaly = y >= verticalSize || y < 0;
		boolean isOutBoundsHorizontaly = x >= horizontalSize || x < 0;
		if (isOutBoundsVerticaly || isOutBoundsHorizontaly) {
			throw new GameOverException(SNAKE_HITS_WALL_MESSAGE);
		}
	}
	public boolean checkCollision(Coordinate coordinate) {
		return snakeBody.contains(coordinate);
	}
	public ArrayList<Coordinate> getSnakeBody() {
		return snakeBody;
	}
	public void addSnakeListener(ChangeListener listener) {
		snakeListeners.add(listener);
	}
	public void addRemovalListener(ChangeListener listener) {
		removalListeners.add(listener);
	}
}
