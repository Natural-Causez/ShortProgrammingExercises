//Person object class recording the name and the likeness of the individual.

public class Person {
    //Define Person Object
    private String name;
    private int likeness; 
    
    public Person(String name, int likeness) {
        this.name = name;
        this.likeness = likeness;
    }//END Constructor

    public String getName() {
        return name;
    }//END getName

    public void setName(String name) {
        this.name = name;
    }//END setName

    public int getLikeness() {
        return likeness;
    }//END getLikeness

    public void setLikeness(int likeness) {
        this.likeness = likeness;
    }//END setLikeness
    
    public String toString() {
        return name + "  has friendliness " + likeness;
    }//END toString
}//END class Person
