//Oliver Etherington
//Driver class for Battleship board game

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Game {

	public static void main(String[] args) {
		Board board1 = new Board();
		ArrayList<Battleship> allShips = board1.getShips();
		ArrayList<Battleship> remainingShips = board1.getShips();
		//remainingShips are ships not destroyed on board

		String userInp = "";
    Scanner scan = new Scanner(System.in);
		int rowToHit = 0;
		int columnToHit = 0;
		Battleship shipToHit;

		int numberBattleships = Collections.frequency(allShips, new Battleship(0));
		int numberCruisers = Collections.frequency(allShips, new Cruiser(0));
		int numberFrigates = Collections.frequency(allShips, new Frigate(0));
		int numberMinesweepers = Collections.frequency(allShips, new Minesweeper(0));

		Boolean hitSuccess = false;
		String[] userCoords = new String[2];
		int counter = 1;

		//Game loop
  	while (!userInp.equals("quit")) {
			System.out.println("Battleships: " + numberBattleships);
			System.out.println("Cruisers: " + numberCruisers);
			System.out.println("Frigates: " + numberFrigates);
			System.out.println("Minesweepers: " + numberMinesweepers);

  		System.out.println(board1);
			System.out.println("Enter co-ordinates to fire at: (Two integers, same line)");

			userInp = scan.nextLine();

			if (!userInp.equals("quit")) {
				userCoords = userInp.split(" ");

				rowToHit = Integer.parseInt(userCoords[0]);
				columnToHit = Integer.parseInt(userCoords[1]);

				//rowToHit = scan.nextInt();
				//columnToHit = scan.nextInt();

				shipToHit = allShips.get(rowToHit);
				hitSuccess = shipToHit.hit(shipToHit, columnToHit);

				//If ship has just been destroyed, decrement variable counters then loop
				if (hitSuccess && shipToHit.shipDestroyed()) {
					if (shipToHit instanceof Cruiser) {
						numberCruisers--;
					}
					else if (shipToHit instanceof Frigate) {
						numberFrigates--;
					}
					else if (shipToHit instanceof Minesweeper) {
						numberMinesweepers--;
					}
					else {
						numberBattleships--;
					}//END if
				}//END if
			}//END if

  	}//END while


	}//END main

}//END class Game
