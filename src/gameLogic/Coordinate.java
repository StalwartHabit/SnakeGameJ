/**
 * 
 */
package gameLogic;

/**
 * @author chris
 *	Holds the x and y coordinates of a game object 
 *  and methods to return them
 */
public class Coordinate {
	private final int x;
	private final int y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	@Override
	public boolean equals(Object object) {
		if (object == null) return false;
		if (object == this) return true;
		
		Coordinate otherCoordinate = (Coordinate)object;
		boolean xIsEqual = this.getX() == otherCoordinate.getX();
		boolean yIsEqual = this.getY() == otherCoordinate.getY();
		return xIsEqual && yIsEqual;
	}
}
