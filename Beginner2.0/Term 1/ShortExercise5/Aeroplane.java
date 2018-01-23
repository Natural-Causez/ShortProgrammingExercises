//Aeroplane object class storing current position, speed and name
//Also include properties such as time to repair and how far it has flown since initialisation

public class Aeroplane {
	private String name;
	private Coordinates currentXY;
	private int speed;

	private int totalxDistance = 0;
	private int totalyDistance = 0;
	private int totalDistance = 0;
	private int repairDistance = 0;

	public Aeroplane(String n, Coordinates coords, int spd, int initialDistance, int initialRepairDistance) {
		name = n;
		currentXY = coords;
		speed = spd;
		totalDistance = initialDistance;
		repairDistance = initialRepairDistance;
	}//END constructor

	public int singleFlight(Destination getTo) {
		Coordinates coords = getTo.getCoords();
		int xCoord = coords.getXCoord();
		int yCoord = coords.getYCoord();

		int xHour = 0;
		int yHour = 0;
		int xPosition = 0;
		int yPosition = 0;

		/////////////////////READ ME///////////////////////////////////////
		//Each while loop checks that the movement of the aeroplane is less
		//than the difference in distances between the two coordinates to 
		//prevent the aeroplane from moving past the coordinate
		///////////////////////////////////////////////////////////////////

		//For x-Coordinate

		//If destination xCoordinate is greater than current position
		if (xCoord > currentXY.getXCoord()) {
			int posDifference = xCoord - currentXY.getXCoord();

			while (speed <= posDifference) {
				xPosition = currentXY.getXCoord();
				xPosition += speed;
				currentXY.setXCoord(xPosition);

				xHour += speed;
				posDifference = xCoord - currentXY.getXCoord();
			}//END while

			//For adding the final distance where difference < speed
			if (speed < posDifference) {
				int a = 0;
			}//END if

			currentXY.setXCoord(xPosition);

			totalxDistance += xHour + posDifference;
		}

		//If destination xCoordinate is smaller than current position
		else if (xCoord < currentXY.getXCoord()) {
			int negDifference = currentXY.getXCoord() - xCoord;
			while (speed >= negDifference) {
				xPosition = currentXY.getXCoord();
				xPosition -= speed;
				currentXY.setXCoord(xPosition);

			//For adding the final distance where difference < speed
			if (speed > negDifference) {
				yPosition += negDifference;
			}//END if

				xHour += speed;
				negDifference = currentXY.getXCoord() - xCoord;
			}//END while
			currentXY.setXCoord(xPosition);
			totalxDistance += xHour + negDifference;
		}
		else {
			System.out.println("Error!");
		}//END if
		//////////////////////////////////////////////////////////////


		//For y-Coordinate

		//If destination yCoordinate is greater than current positon
		if (yCoord > currentXY.getYCoord()) {
			int posDifference = yCoord - currentXY.getYCoord();
			while (speed <= posDifference) {
				yPosition = currentXY.getYCoord();
				yPosition += speed;
				currentXY.setYCoord(yPosition);

				yHour += speed;
				posDifference = yCoord - currentXY.getYCoord();
			}//END while

			//For adding the final distance where difference < speed
			if (speed > posDifference) {
				yPosition += posDifference;
			}//END if

			currentXY.setYCoord(yPosition);
			totalyDistance += yHour + posDifference;
		}

		//If destination yCoordinate is smaller than current position
		else if (yCoord < currentXY.getYCoord()) {
			int negDifference = currentXY.getYCoord() - yCoord;
			while (speed >= negDifference) {
				yPosition = currentXY.getYCoord();
				yPosition -= speed;
				currentXY.setYCoord(yPosition);

				yHour += speed;
				negDifference = currentXY.getYCoord() - yCoord;
			}//END while

			//For adding the final distance where difference < speed
			if (speed > negDifference) {
				yPosition += negDifference;
			}//END if

			currentXY.setYCoord(yPosition);
			totalyDistance += yHour + negDifference;
		}
		else {
			System.out.println("Error!");
		}//END if
		//////////////////////////////////////////////////////////////

		totalDistance = totalxDistance + totalyDistance;
		System.out.println("Journey distance: " + totalDistance);
		return totalDistance;
	}//END singleFlight

	public String getName() {
		return name;
	}//END getName

	public void printCurrentCoordinates() {
		System.out.println("Current Coordinates: (" + currentXY.getXCoord() + ", " + currentXY.getYCoord() + ")");
	}//END printCurrentCoordinates

	public void calculateRepairDistance() {
		int a = 0;
	}//END calculateRepairDistance

	public void calculateTotalDistance() {
		int b = 0;
	}//END calculateTotalDistance

}//END class Aeroplane