//New Van object class with more class inheritance and use of an override

public class Van extends SportCar {
  Double carryWeight;

  public void Van() {
    carryWeight = 0.0;
  }//END constructor method

  public Double getCarryWeight() {
    return carryWeight;
  }//END getCarryWeight

  public void setCarryWeight(Double cw) {
    carryWeight = cw;
  }//END setCarryWeight

  //// OVERRIDE ///////
  public Double acceleration() {
    Double a = (100/horsepower) * (aerodynamics/2) * weight/100;
    return a;
  }//END acceleration
  ////////////////////
}//END class Van
