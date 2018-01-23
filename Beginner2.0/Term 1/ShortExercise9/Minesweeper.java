//Oliver Etherington
//Minesweeper object class - a subclass of Battleship, defining the size

public class Minesweeper extends Battleship {
  public Minesweeper(int row) {
    super(row, 2);
  }//END Constructor

  public Boolean hit(Battleship b, int c) {
  	//Override method

    if (c >= 2) {
      System.out.println("Miss!");
      return false;
    }//END if

    //Check if already destroyed
    if (b.getParts().get(c).getDestroyed()) {
      System.out.println("Hit!");
      return false;
    }//END if

		//Calculate chance
		int chance = (int)(Math.random() * 2);

		if (chance == 1) {
			return super.hit(b, c);
		}
		else {
      System.out.println("Miss!");
			return false;
		}//END if

  }//END hit
}//END class Minesweeper
