//SportCar object class storing topspeed, default aerodynamic coefficient with getters and setters
//Demonstrating use of class inheritance

public class SportCar extends Vehicle {
    Double topspeed;
    
    public SportCar() {
        aerodynamics = 0.5;
    }//END SportCar
    
    public Double gettopspeed() {
        return topspeed;
    }//END gettopspeed
        
    public void sethorsepower(Double h) {
        horsepower = h;
    }//END sethorsepower
        
    public void setweight(Double w) {
        weight = w;
    }//END setweight
        
    public void settopspeed(Double ts) {
        topspeed = ts;
    }//END settopspeed
        
}//END class SportCar
