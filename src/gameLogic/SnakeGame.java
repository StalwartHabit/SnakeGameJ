/**
 * 
 */
package gameLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/** holds the snake and the apples location as well as manages
 *  the player turns
 * @author chris
 *
 */
public class SnakeGame implements Iterable<Coordinate> {
	public static final int HORIZONTAL_SIZE = 20;
	public static final int VERTICAL_SIZE = 20;
	
	private Snake snake;
	private Snake.Direction currentDirection = Snake.Direction.NONE;
	private Snake.Direction bodyDirection = Snake.Direction.SOUTH;
	
	private Coordinate apple; 
	
	private ArrayList<ChangeListener>  appleListeners = new ArrayList<ChangeListener>();
	
	public SnakeGame() {
		snake = new Snake(HORIZONTAL_SIZE, VERTICAL_SIZE);
		newAppleLocation();
	}
	public void gameTurn() {
		if (currentDirection != Snake.Direction.NONE){
			Snake.Direction moveDirection = currentDirection; 
			// copied over to prevent a race condition
			snake.moveSnake(moveDirection);
			setBodyDirection(moveDirection);
			boolean eatsApple = snake.checkCollision(apple);
			snake.growSnake(eatsApple);
			if (eatsApple) {
				newAppleLocation();
			}
		}
	}
	private void newAppleLocation() {
		ArrayList<Coordinate> freeLocations = new ArrayList<Coordinate>();
		for (int i = 0; i < VERTICAL_SIZE; i++) {
			for (int j = 0; j < HORIZONTAL_SIZE; j++) {
				freeLocations.add(new Coordinate(j,i));
			}
		}
		freeLocations.removeAll(snake.getSnakeBody());
		java.util.Collections.shuffle(freeLocations);
		apple = freeLocations.get(0);
		ChangeEvent appleEvent = new ChangeEvent(apple);
		for (ChangeListener listener : appleListeners) {
			listener.stateChanged(appleEvent);
		}
	}
	public void setCurrentDirection(Snake.Direction currentDirection) {
		if (currentDirection != bodyDirection){
			this.currentDirection = currentDirection;
		}
	}
	public Coordinate getApple() {
		return apple;
	}
	private void setBodyDirection(Snake.Direction currentDirection) {
		switch (currentDirection) {
		case NORTH: 
			bodyDirection = Snake.Direction.SOUTH;
			break;
		case SOUTH: 
			bodyDirection = Snake.Direction.NORTH;
			break;
		case EAST: 
			bodyDirection = Snake.Direction.WEST;
			break;
		case WEST: 
			bodyDirection = Snake.Direction.EAST;
			break;
		default: 
			assert false;
		}
	}
	
	
	@Override
	public Iterator<Coordinate> iterator() {
		return Collections.unmodifiableCollection(snake.getSnakeBody()).iterator();
	}
	
	public void addSnakeListener(ChangeListener listener) {
		snake.addSnakeListener(listener);
	}
	public void addRemovalListener(ChangeListener listener) {
		snake.addRemovalListener(listener);
	}
	public void addAppleListener(ChangeListener listener) {
		appleListeners.add(listener);
	}
}
