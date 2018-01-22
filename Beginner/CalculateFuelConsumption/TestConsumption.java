//Program to calculate 'fuel consumption' on car objects
//Use of class inheritance and getter/setter methods

import java.util.Formatter;

public class TestConsumption {
    public static void main(String[] args) {
        SportCar car1 = new SportCar();
        car1.sethorsepower(200.0);
        car1.setweight(1500.0);
        car1.settopspeed(220.0);
        
        SportCar car2 = new SportCar();
        car2.sethorsepower(100.0);
        car2.setweight(1000.0);
        car2.settopspeed(170.0);
        
        SportCar car3 = new SportCar();
        car3.sethorsepower(135.0);
        car3.setweight(1100.2);
        car3.settopspeed(173.0);
        
        String fuelCons = "";
        
        ////////// car1 ////////////
        fuelCons = calculateConsumption(car1);
        System.out.println("Fuel consumption for car1 is " + fuelCons);
        ////////////////////////////////
        
        ////////// car2 ////////////
        fuelCons = calculateConsumption(car2);
        System.out.println("Fuel consumption for car2 is " + fuelCons);
        ////////////////////////////////
        
        ////////// car3 ////////////
        fuelCons = calculateConsumption(car3);
        System.out.println("Fuel consumption for car3 is " + fuelCons);
        ////////////////////////////////
    }//END main
        
    public static String calculateConsumption(SportCar obj) {
        Double horsepower = obj.gethorsepower();
        Double weight = obj.getweight();
        Double topspeed = obj.gettopspeed();
        Double aerodynamics = obj.getaerodynamics();
        
        Double fuelCons = 0.0;
        
        fuelCons = (1000+(weight/5)) * (topspeed/100) * ((aerodynamics*horsepower)/10000);
                             
        String str = String.format("%.2f", fuelCons); //Formatting
                             
        return str;
    }//END calculateConsumption
}//END class TestConsumption
