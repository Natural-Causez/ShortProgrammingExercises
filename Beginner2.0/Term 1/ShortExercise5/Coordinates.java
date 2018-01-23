//Coordinate object class that stores a cartesian coordinate with integer values for x and y
//Used in the Destination object class

public class Coordinates {
	private int xCoord;
	private int yCoord;

	public Coordinates(int x, int y) {
		xCoord = x;
		yCoord = y;
	}//END constructor

	public void setXCoord(int x) {
		xCoord = x;
	}//END setXCoord

	public void setYCoord(int y) {
		yCoord = y;
	}//END setYCoord

	public int getXCoord() {
		return xCoord;
	}//END getXCoord

	public int getYCoord() {
		return yCoord;
	}//END getYCoord
}//END class Coordinates