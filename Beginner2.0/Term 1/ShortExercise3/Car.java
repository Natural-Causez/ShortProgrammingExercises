//Car object class with calculcations for wear and time to complete a lap

public class Car {
  private int id;
  private int fuel = 100;
  private int lowFuelBoost;
  private int highFuelSlowdown;
  private int fuelConsumptionPerLap;
  private int pitStopTime;
  private int rainSlowdown;

  private int totalTime = 0;

  //CONSTRUCTOR
  public Car (int i, int f, int lfb, int hfs, int fcpl, int pst, int rs) {
    id = i;
    fuel = f;
    lowFuelBoost = lfb;
    highFuelSlowdown = hfs;
    fuelConsumptionPerLap = fcpl;
    pitStopTime = pst;
    rainSlowdown = rs;
  }//END Constructor

  public int completeLap(int baseTime, Boolean rain, Car car) {
    int lapTime = baseTime;

    if (fuel > 50) {
      lapTime += highFuelSlowdown;
    }
    else {
      lapTime -= lowFuelBoost;
    }//END if

    if (rain) {
      //System.out.println("It's raining.");
      lapTime -= rainSlowdown;
    }//END if

    lapTime -= fuelConsumptionPerLap;

    if (fuelConsumptionPerLap > fuel) {
      pitStop(car);
    }//END if

    car.totalTime += lapTime;

    return car.totalTime;
  }//END completeLap

  //Adds the time for a pitStop, then refuels car to full (100)
  public void pitStop(Car car) {
    car.totalTime += pitStopTime;
    car.fuel = 100;
  }//END pitStop

  public int getTotalTime() {
    return totalTime;
  }//END getTotalTime

}//END class Car
