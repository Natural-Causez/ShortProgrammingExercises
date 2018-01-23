//Game in which the player is presented with a set of doors and has to navigate their way to the 'The Great Hall' to win.

import java.util.Scanner;

public class DoorMazeGame {
	public static void main(String[] args) {
		//Room Constructor:
		//public Room (String n, Room blue, Room red, Boolean monster, Boolean last)

		Room monsterRoom = new Room("The Monster Room", true, false);

		Room room6 = new Room("The Great Hall", false, true);
		Room room5 = new Room("The Fifth Hall", room6, monsterRoom, false, false);
		Room room4 = new Room("The Fourth Hall", monsterRoom, room5, false, false);
		Room room3 = new Room("The Third Hall", room4, monsterRoom, false, false);
		Room room2 = new Room("The Second Hall", room3, monsterRoom, false, false);
		Room room1 = new Room("The First Hall", monsterRoom, room2, false, false);
		

		Player player = new Player("Name", 2, room1);

		System.out.println("//////////////////////////////////////////");
		System.out.println("///// WELCOME TO THE DOOR MAZE GAME  /////");
		System.out.println("/   You will start in 'The First Hall'   /");
		System.out.println("/   Choose the red door or the blue door /");
		System.out.println("/   But beware, if you encounter the     /");
		System.out.println("/   monster, you will be attacked and    /");
		System.out.println("/   lose a life.                         /");
		System.out.println("/   You have " + player.getLives() + " lives.                    /");
		System.out.println("/////////////   Good Luck. ///////////////");
		System.out.println("//////////////////////////////////////////");

		Scanner sc = new Scanner(System.in);
		String choice = "";

		while (player.getLives() > 0 && (choice != "red" || choice != "blue")) {
			System.out.println(player.getCurrentRoom().getRoomName());
			if ((player.getCurrentRoom().getRoomName()).equals("The Great Hall")) {
				player.win();
				player.setLives(0);
				break;
			}//END if
			System.out.println("Make a choice. \"red\" or \"blue\":");
			choice = sc.nextLine();
			player.determineChoice(choice);
		}//END while

		if (player.getLives() == 0) {
			System.out.println("//////////////////////////////////////////");
			System.out.println("/////////////  GAME OVER  ////////////////");
			System.out.println("//////////////////////////////////////////");
		}//END if

	}//END main
}//END class DoorMazeGame