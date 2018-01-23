//Crop object class storing a type (String) and monetary value

public class Crop {
	private String type; //Corn, Wheat, etc
	private int value;

	public Crop(String type, int value) {
		this.type = type;
		this.value = value;
	}//END Constructor

	public int getValue() {
		return value;
	}//END getValue
}//END class Crop