//Destination object class storing the name and coordinates of the given destination

public class Destination {
	private String name;
	private Coordinates coords;

	public Destination(String location, Coordinates xy) {
		name = location;
		coords = xy;
	}//END constructor

	public String getName() {
		return name;
	}//END getName

	public Coordinates getCoords() {
		return coords;
	}//END getCoords
}//END class Destination