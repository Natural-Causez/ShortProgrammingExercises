//Build on previous task files
//Added acceleration calculation

public class Vehicle {
    Double horsepower; Double aerodynamics; Double weight;

    public Double getHorsepower() {
        return horsepower;
    }//END gethorsepower

    public Double getAerodynamics() {
        return aerodynamics;
    }//END getaerodynamics

    public Double getWeight() {
        return weight;
    }//END weight

    public Double acceleration() {
        Double a = (100/horsepower) * aerodynamics * weight/100;
        return a;
    }//END acceleration

}// END class Vehicle
