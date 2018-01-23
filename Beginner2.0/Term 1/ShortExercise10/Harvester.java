//Harvester object class to store properties of different harvesters

public class Harvester {
	private int fuelTankSize;
	private int topSpeed;
	private int harvestingCapacity;


	public Harvester(int fuelTankSize, int topSpeed) {
		this.fuelTankSize = fuelTankSize;
		this.topSpeed = topSpeed;

		harvestingCapacity = fuelTankSize + topSpeed;
	}//END Constructor

	public int getCapacity() {
		return harvestingCapacity;
	}//END getCapacity
}//END class Harvester