//Banker object class which stores two TreeMaps of locations and friendliness ratings of people that a banker may know of.

import java.util.Map.Entry;
import java.util.TreeMap;

public class Banker extends Person {
    //Define TreeMaps
    private TreeMap<String, Integer> relativeLocation = new TreeMap();
    private TreeMap<String, Integer> relativeFriendliness = new TreeMap();
    
    public Banker(String _name, int _friendliness) {
        super(_name, _friendliness);
    }//END Constructor
    
    //Add object to TreeMap
    public void addRelative(String _name, int _dis, int _friendliness) {
        relativeLocation.put(_name, _dis);
        relativeFriendliness.put(_name, _friendliness);
    }//END addRelative
    
    //Finds lowest key value (By distance) (From 1st List) and determines friendliness (From 2nd List)
    public void FindRelative(Banker _banker) {
        String name;
        int dis;
        int friendliness;
        Entry<String, Integer> relative = null;
        for (Entry<String, Integer> entry : relativeLocation.entrySet()) {
            if (relative == null || relative.getValue() > entry.getValue()) {
                relative = entry;
            }//END if
        }//END for
        name = relative.getKey();
        dis = relative.getValue();
        friendliness = relativeFriendliness.get(name);
        
        System.out.println("Nearest Relative is: " + name + " who is " + dis + " miles away");
        
        if (friendliness < this.getLikeness()) {           
            System.out.println(this.getName() + " has decided to move the children due to " + name + " friendliness being " + friendliness);
            relativeLocation.remove(name);
            relativeFriendliness.remove(name);
        }
        else {
            System.out.println(this.getName() + " has decided to keep the children at " + name + " home due to them being friendy");
        }//END if
        
    }//END FindRelative
}//END class Banker