//Airplane object class that keeps track of and updates certain properties.

public class Airplane {
	private int y, x, elevation, speed, takeoffElevation;

	public Airplane(int x, int y){
		this.y = y;
		this.x = x;
		this.speed = 0;
		this.elevation = 0;
		this.takeoffElevation = 5;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getSpeed(){
		return speed;
	}
	public int getElevation(){
		return elevation;
	}
	public int getTakeoffElevation(){
		return takeoffElevation;
	}
	public void setElevation(int elevation){
		this.elevation = elevation;
	}
	//update method calculates and updates for correct airplane fields
	public void update(int newX, int newY, boolean isElevation){
		this.speed = newY;
		this.x = newX;
		this.y += this.speed;
		if(isElevation)
			this.elevation++;
	}
}

