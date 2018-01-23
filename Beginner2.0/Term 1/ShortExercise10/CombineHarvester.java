//Harvester object class - subclass of Harvester with a 'harvesting capacity'

public class CombineHarvester extends Harvester {
	private int length;
	private int harvestingCapacity;

	public CombineHarvester(int fuelTankSize, int topSpeed, int length) {
		super(fuelTankSize, topSpeed);

		this.length = length;
		this.harvestingCapacity = (fuelTankSize + topSpeed) * length;
	}//END Constructor

	public int getCapacity() {
		return harvestingCapacity;
	}//END getCapacity

}//END class CombineHarvester