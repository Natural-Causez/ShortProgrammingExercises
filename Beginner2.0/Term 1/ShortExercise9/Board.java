//Oliver Etherington
//Board object class storing a number of battleships and generating the board

import java.util.ArrayList;

public class Board {
	private ArrayList<Battleship> ships = new ArrayList<Battleship>();

	public Board() {
		//Ships defined here by assignment
		//1 Battleship, 2 Cruisers, 1 Frigate, 1 Minesweeper

		//1 Battleship
		Battleship battleship1 = new Battleship(ships.size());
		ships.add(battleship1);

		//2 Cruisers
		Cruiser cruiser1 = new Cruiser(ships.size());
		ships.add(cruiser1);
		Cruiser cruiser2 = new Cruiser(ships.size());
		ships.add(cruiser2);

		//1 Frigate
		Frigate frigate1 = new Frigate(ships.size());
		ships.add(frigate1);

		//1 Minesweeper
		Minesweeper minesweeper1 = new Minesweeper(ships.size());
		ships.add(minesweeper1);


		//NOTE - Objects are added as they are created so that the row number can be calculated
		//and passed for the Part objects
	}//END Constructor

	public ArrayList<Battleship> getShips() {
		return ships;
	}//END getShips

	public Boolean fire(int r, int c) {
		Battleship shipToHit = ships.get(r);
		Boolean success = shipToHit.hit(shipToHit, c);
		return success;
	}//END hit

	public String toString() {
		String boardString = "";

		for (Battleship b : ships) {
			for (int i = 0; i < b.getParts().size(); i++) {
				if (b.getParts().get(i).getDestroyed()) {
					boardString += "[X] ";
				}
				else {
					boardString += "[ ] ";
				}//END if
			}//END for


			int j = 5; //Each row on board is 5 pieces long
			//Fill remaining space
			j -= b.getParts().size();
			for (int i = j; i > 0; i--) {
				boardString += "[ ] ";
			}//END for

			//Re-initialise j
			j = 5;

			boardString += "\n";
		}//END for-each

		return boardString;
	}//END toString
}//END class Board
