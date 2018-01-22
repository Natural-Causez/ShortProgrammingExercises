//Build on previous exercise with addition of Van class object and use of overriding superclass methods

import java.util.Formatter;

public class TestConsumption {
    public static void main(String[] args) {
        SportCar car1 = new SportCar();
        car1.setHorsepower(200.0);
        car1.setWeight(1500.0);
        car1.setTopSpeed(220.0);
        
        SportCar car2 = new SportCar();
        car2.setHorsepower(100.0);
        car2.setWeight(1000.0);
        car2.setTopSpeed(170.0);
        
        Van car3 = new Van();
        car3.setHorsepower(135.0);
        car3.setWeight(1100.2);
        car3.setTopSpeed(173.0);
        car3.setCarryWeight(1750.0);
        
        String fuelCons = "";
        
        ////////// car1 ////////////
        fuelCons = calculateConsumption(car1);
        System.out.println("Fuel consumption for car1 is " + fuelCons);
        System.out.println("Acceleration for car1 is " + car1.acceleration());
        ////////////////////////////////
        
        ////////// car2 ////////////
        fuelCons = calculateConsumption(car2);
        System.out.println("\nFuel consumption for car2 is " + fuelCons);
        System.out.println("Acceleration for car2 is " + car2.acceleration());
        ////////////////////////////////
        
        ////////// car3 ////////////
        System.out.println("\nCarry weight for car3 (Van) is: " + car3.getCarryWeight());
        System.out.println("Acceleration (Override) for car3 is " + (String.format("%.2f", car3.acceleration())));
        ////////////////////////////////
    }//END main
        
    public static String calculateConsumption(SportCar obj) {
        Double horsepower = obj.getHorsepower();
        Double weight = obj.getWeight();
        Double topspeed = obj.getTopSpeed();
        Double aerodynamics = obj.getAerodynamics();
            
        Double fuelCons = (1000 + (weight/5)) * (topspeed/100) * ((aerodynamics * horsepower)/10000);
                             
        String str = String.format("%.2f", fuelCons); //Formatting
                             
        return str;
    }//END calculateConsumption
}//END class TestConsumption