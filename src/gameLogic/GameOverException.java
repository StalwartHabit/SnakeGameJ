/**
 * 
 */
package gameLogic;

/** an unchecked exception that is thrown when the snake hits itself or 
 *  a wall
 * @author chris
 *
 */
@SuppressWarnings("serial")
public class GameOverException extends RuntimeException {
	public GameOverException() {
		super();
	}
	public GameOverException(String msg) {
		super(msg);
	}
}
