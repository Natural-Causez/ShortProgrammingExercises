//Harvest class to initialise a farm and add appropriate fields and harvesters
//Calculates the profit and prints to the terminal.

public class Harvest {
	public static void main(String[] args) {
		Farm farm1 = new Farm();

		farm1.addHarvester(new Harvester(1, 1));
		farm1.addHarvester(new CombineHarvester(2, 2, 3));

		//20 Fields - All value of 20
		//5 Corn
		for (int i = 0; i < 5; i++) {
			farm1.addField(new Field("Corn", 20));
		}//END for

		//5 Wheat
		for (int i = 0; i < 5; i++) {
			farm1.addField(new Field("Wheat", 20));
		}//END for

		//5 Oats
		for (int i = 0; i < 5; i++) {
			farm1.addField(new Field("Oats", 20));
		}//END for

		//5 Barley
		for (int i = 0; i < 5; i++) {
			farm1.addField(new Field("Barley", 20));
		}//END for

		farm1.harvest();

		System.out.println(farm1.getProfit());

	}//END main
}//END class Harvest