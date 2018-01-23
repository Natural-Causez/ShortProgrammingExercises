//Simulation class to enforce restrictions on the limits of the simulation.

public class Simulation {

	private final int runwayWidth = 10;
	private final int runwayLength = 100;

	public void run(Airplane a, Display d) {
		int secondsCounter = 0, takeoffElevation = a.getTakeoffElevation(), iteration = 0;
		boolean isElevation = false; //indicates whether plane is fast enough to gain elevation
		d.resetSimulator();
		//Runs while Y position is less than runway length
		//runwayLength - speed ensures that the airplane does not over shoot
		//Elevation is less than required value (5)
		//And reset button has not been pressed
		while ((a.getY() <= (runwayLength - a.getSpeed())) && a.getElevation() < 5 && !d.isPressed()) {
			a.update(d.getXSlider(), d.getYSlider(), isElevation);
			if(a.getSpeed() == 10){ //plane must be going at speed 10 for 5 seconds to gain elevation
				iteration++;
				if(iteration == 5){
					isElevation = true;
				}
			}else{
				iteration = 0; 
			}
			if(isElevation && a.getSpeed() != 10){ //checks that plane is not losing speed after gaining elevation
				a.setElevation(a.getElevation()-1);
			}
			d.setString("Seconds: " + secondsCounter++ +"\n");
			d.setString("X: " + a.getX() + " Y: " + a.getY() + " Speed: " + a.getSpeed() + " Elevation: " + a.getElevation() + "\n");
			sleep(1000);

		} // END while
		if(a.getElevation() >= 5 && a.getX() == 5){ //condition for successful takeoff
			d.setString("Takeoff successful!\n");
			sleep(1000); //sleep to delay print statement for readability
		}else{ //condition for unsuccessful takeoff
			d.setString("Takeoff unsuccessful\n");
			sleep(1000); //sleep to delay print statement for readability
		}
	}// END run
	
	public void sleep(int time) {
		try {
			Thread.sleep(time);
		}//END try 
		catch (InterruptedException e) {

		}//END catch
	}//END sleep

}// END class
