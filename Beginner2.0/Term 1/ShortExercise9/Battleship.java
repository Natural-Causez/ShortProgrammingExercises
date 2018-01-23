//Oliver Etherington
//Battleship object class storing a number of parts and whether or not the ship has been destroyed

import java.util.ArrayList;

public class Battleship {
  private ArrayList<Part> parts = new ArrayList<Part>();
  private String name = "Battleship";
  private Boolean shipDestroyed = false;

  //Constructor for standard Battleship
  public Battleship(int row) {
    for (int i = 0; i < 5; i++) {
      addPart(row, i);
    }//END for
  }//END Battleship

  //Constructor for all extensions of Battleship
  public Battleship(int row, int numberParts) {
    for (int i = 0; i < numberParts; i++) {
      addPart(row, numberParts);
    }//END for
  }//END Constructor

  public String getName() {
    return name;
  }//END getName

  public void addPart(int row, int column) {
    Part tempPart = new Part(row, column);
    parts.add(tempPart);
  }//END addPart

  public Boolean hit(Battleship b, int c) {
    //Allowed to access parts variable here as inside Battleship class
    //get part, then execute destroy()

    if (c >= b.parts.size()) {
      System.out.println("Miss!");
      return false;
    }//END if

    if (b.parts.get(c).getDestroyed()) {
      System.out.println("Hit!");
      return false;
    }//END if

    System.out.println("Hit!");
    b.parts.get(c).destroy();

		return true;
  }//END hit

  public Boolean shipDestroyed() {
    for (int i = 0; i < parts.size(); i++) {
      if (!(parts.get(i).getDestroyed())) {
        return false;
      }//END if
    }//END for

    return true;
  }//END shipDestroyed

  public ArrayList<Part> getParts() {
    return parts;
  }//END getParts

  public boolean equals(Object p) {
    //If not a battleship
    if (!(p instanceof Battleship)) {
      return false;
    }//END if

    //If not the same number of parts - therefore different ship
    if (((Battleship)p).parts.size() != parts.size()) {
      return false;
    }//END if

    for (int i = 0; i < parts.size(); i++) {
      //If current object part is NOT destroyed and passed object part IS destroyed (!=)
      if ((!(parts.get(i).getDestroyed())) && (((Battleship)p).parts.get(i).getDestroyed())) {
        return false;
      }//END if
      //Or
      //If currect object part IS destroyed and passed object part is NOT destroyed (also !=)
      if ((parts.get(i).getDestroyed()) && (!(((Battleship)p).parts.get(i).getDestroyed()))) {
        return false;
      }//END if
    }//END for

    //Otherwise, must be equal - return true
    return true;
  }//END equals

  public String toString() {
    String shipString = "";

    for (Part p : parts) {
      if (p.getDestroyed()) {
        shipString += "[X]";
      }
      else {
        shipString += "[ ]";
      }//END if

      shipString += " ";
    }//END for-each

    return shipString;
  }//END toString
}//END class Battleship
