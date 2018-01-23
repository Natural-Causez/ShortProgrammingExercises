//Basic race simulator with Car objects that have different properties and produce different lap times depending
//on each property

public class RaceSimulator {

  //All objects are created and manipulated here
  public static void main(String[] args) {

    //Define racetrack (Assignment requirements)
    RaceTrack silverstone = new RaceTrack(112, false);
    //silverstone.setgetLapTime()(112);
    //silverstone.setcheckIsRaining(false);

    //Define 3 cars
    Car car1 = new Car(1, 79, 6, 5, 19, 25, 15); //
    Car car2 = new Car(2, 67, 8, 4, 29, 16, 11); // Defined by assignment
    Car car3 = new Car(3, 41, 7, 6, 31, 18, 13); //

    int car1LapTime = 0;
    int car2LapTime = 0;
    int car3LapTime = 0;

    ////////// TEST DATA //////////////
    //car1.setVariables(1, 41, 1, 20, 28, 30, 45);
    //car2.setVariables(2, 100, 1, 0, 1, 2, 5);
    //car3.setVariables(3, 100, 1, 0, 1, 3, 4);
    ///////////////////////////////////
    

    int currentLeader;

    //Assignment requires 2 laps with id of leader printed in between each lap
    car1LapTime += car1.completeLap(silverstone.getLapTime(), silverstone.checkIsRaining(), car1);
    car2LapTime += car2.completeLap(silverstone.getLapTime(), silverstone.checkIsRaining(), car2);
    car3LapTime += car3.completeLap(silverstone.getLapTime(), silverstone.checkIsRaining(), car3);
    System.out.println("\nLap 1:");
    currentLeader = silverstone.determineLeader(car1.getTotalTime(), car2.getTotalTime(), car3.getTotalTime());

    car1LapTime += car1.completeLap(silverstone.getLapTime(), silverstone.checkIsRaining(), car1);
    car2LapTime += car2.completeLap(silverstone.getLapTime(), silverstone.checkIsRaining(), car2);
    car3LapTime += car3.completeLap(silverstone.getLapTime(), silverstone.checkIsRaining(), car3);
    System.out.println("\nLap 2:");
    currentLeader = silverstone.determineLeader(car1.getTotalTime(), car2.getTotalTime(), car3.getTotalTime());

    ///// RAIN RAIN RAIN RAIN RAIN RAIN RAIN RAIN RAIN RAIN
    silverstone.setIsRaining(true);
    System.out.println("\nRaining on track.");

    //FINAL LAP
    car1LapTime += car1.completeLap(silverstone.getLapTime(), silverstone.checkIsRaining(), car1);
    car2LapTime += car2.completeLap(silverstone.getLapTime(), silverstone.checkIsRaining(), car2);
    car3LapTime += car3.completeLap(silverstone.getLapTime(), silverstone.checkIsRaining(), car3);
    System.out.println("Lap 3:");

    //AND THE WINNER IS???!!
    currentLeader = silverstone.determineLeader(car1.getTotalTime(), car2.getTotalTime(), car3.getTotalTime());
    System.out.println("Winner is car #" + currentLeader);
  }//END main
}//END class RaceSimulator
