public class SportCar extends Vehicle {
    Double topspeed;
    
    public SportCar() {
        aerodynamics = 0.5;
    }//END SportCar
    
    public Double getTopSpeed() {
        return topspeed;
    }//END gettopspeed
        
    public void setHorsepower(Double h) {
        horsepower = h;
    }//END sethorsepower
        
    public void setWeight(Double w) {
        weight = w;
    }//END setweight
        
    public void setTopSpeed(Double ts) {
        topspeed = ts;
    }//END settopspeed
        
}//END class SportCar
