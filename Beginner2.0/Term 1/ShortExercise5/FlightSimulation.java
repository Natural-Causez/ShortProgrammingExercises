//Basic flight simulator that calculates position with cartesian coordinates as plane moves from one destination to another

public class FlightSimulation {

	public static void main(String[] args) {
		//Defined by assignment
		//Q2.1
		Coordinates dest1Coords = new Coordinates(100, 45);
		Destination destination1 = new Destination("Beijing", dest1Coords);

		//Q2.2
		Coordinates dest2Coords = new Coordinates(145, 120);
		Destination destination2 = new Destination("Istanbul", dest2Coords);

		//Q2.3
		Coordinates dest3Coords = new Coordinates(35, 90);
		Destination destination3 = new Destination("Dhaka", dest3Coords);

		//Q2.4
		Coordinates initialCoords = new Coordinates(100, 45);
		Aeroplane aeroplane = new Aeroplane("Airbus", initialCoords, 9, 0, 1600);

		//Q2.5

		//Q2.5.1
		System.out.println("Aeroplane name: " + aeroplane.getName());
		System.out.println("Initial Destination: " + destination1.getName());
		System.out.println("Final Destination: " + destination2.getName());
		aeroplane.singleFlight(destination2); //Go to destination2
		aeroplane.printCurrentCoordinates();

		//Q2.5.2
		System.out.println("Aeroplane name: " + aeroplane.getName());
		System.out.println("Start Destination: " + destination2.getName());
		System.out.println("Final Destination: " + destination3.getName());
		aeroplane.singleFlight(destination3); //Go to destination3
		aeroplane.printCurrentCoordinates();

		//Q2.5.3
		System.out.println("Aeroplane name: " + aeroplane.getName());
		System.out.println("Initial Destination: " + destination3.getName());
		System.out.println("Final Destination: " + destination2.getName());
		aeroplane.singleFlight(destination2); //Go back to destination2
		aeroplane.printCurrentCoordinates();

		//Q2.5.4
		System.out.println("Aeroplane name: " + aeroplane.getName());
		System.out.println("Initial Destination: " + destination2.getName());
		System.out.println("Final Destination: " + destination1.getName());
		aeroplane.singleFlight(destination1); //Finally return back to destination1
		aeroplane.printCurrentCoordinates();

	}//END main

}//END class FlightSimulation