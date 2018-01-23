//Field object class storing an array of crops

public class Field {
	 Crop[] setCrops = new Crop[10]; //Maximum number of crops any field can contain

	public Field(String type, int value) {
		for (int i = 0; i < 10; i++) {
			setCrops[i] = plant(type, value);
		}//END for
	}//END Constructor
	
	public Crop plant(String type, int value) {
		return new Crop(type, value);
	}//END plant

	public int harvest() {
		int totalValue = setCrops[0].getValue() * 10;
		setCrops = new Crop[setCrops.length];

		return totalValue;
	}//END harvest
}//END class Field
