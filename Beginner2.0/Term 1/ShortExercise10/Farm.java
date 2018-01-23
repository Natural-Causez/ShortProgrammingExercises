//Farm object class to store arrays of fields of crops and harvesters
//Keeps track of current profit given fields and crop type

import java.util.ArrayList;

public class Farm {
	private ArrayList<Field> fields = new ArrayList<Field>();
	private ArrayList<Harvester> harvesters = new ArrayList<Harvester>();

	private int profit = 0;

	public void addHarvester(Harvester harvesterToAdd) {
		harvesters.add(harvesterToAdd);
	}//END addHarvester

	public void addField(Field fieldToAdd) {
		fields.add(fieldToAdd);
	}//END addField

	public int getProfit() {
		return profit;
	}//END getProfit

	public void harvest() {
		int harvestingCapacity = 0;

		for (Harvester h : harvesters) {
			harvestingCapacity += h.getCapacity();
		}//END for-each

		for (int i = 0; i < fields.size() && i < harvestingCapacity; i++) {
			profit += fields.get(i).harvest();
		}//END for
	}//END harvest

}//END class Farm