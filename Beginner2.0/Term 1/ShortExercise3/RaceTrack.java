//RaceTrack object class to determine which car is leading after each lap

public class RaceTrack {

  private int averageLapTime;
  private Boolean isRaining;

  //CONSTRUCTOR
  public RaceTrack (int time, Boolean rain) {
    averageLapTime = time;
    isRaining = rain;
  }//END constructor RaceTrack

  public int determineLeader(int car1TotalTime, int car2TotalTime, int car3TotalTime) {
    //System.out.println("car1TotalTime = " + car1TotalTime);
    //System.out.println("car2TotalTime = " + car2TotalTime);
    //System.out.println("car3TotalTime = " + car3TotalTime);

    if (car1TotalTime < car2TotalTime) {
      //car1 is in front of car2
      //But we don't know if car1 is in front of car3
      if (car1TotalTime < car3TotalTime) {
        //Here car1 is in the lead
        System.out.println("The car in the lead is #" + 1);
        System.out.println("#2 car3");
        System.out.println("#3 car2");
        return 1;
      }
      else {
        //Here car3 is in the lead as we already know that car3 < car2 as
        //car3 < car1 and car1 < car2
        System.out.println("The car in the lead is #" + 3);
        System.out.println("#2 car2");
        System.out.println("#3 car1");
        return 3;
      }//END if
    }
    else {
      //Here car2 < car1 but we don't know if car2 < car3
      if (car2TotalTime < car3TotalTime) {
        //Here car2 is in the lead
        System.out.println("The car in the lead is #" + 2);
        System.out.println("#2 car1");
        System.out.println("#3 car3");
        return 2;
      }
      else {
        //Otherwise car3 is in the lead as car2 < car1 therefore car3 < car1
        System.out.println("The car in the lead is #" + 3);
        System.out.println("#2 car2");
        System.out.println("#3 car1");
        return 3;
      }//END if
    }//END if
  }//END determineLeader

  //public void setAverageLapTime(int time) {
  //  averageLapTime = time;
  //}//END setAverageLapTime

  public void setIsRaining(Boolean rain) {
    isRaining = rain;
  }//END setIsRaining

  public int getLapTime() {
    return averageLapTime;
  }//END getLapTime

  public Boolean checkIsRaining() {
    return isRaining;
  }//END checkIsRaining
}//END class RaceTrack
