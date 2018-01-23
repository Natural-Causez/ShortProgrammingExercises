//Player object class which stores the name of the player, lives left and the current room. Contains game logic.

public class Player {
	private String name;
	private int livesLeft;
	private Room currentRoom;

	public Player (String n, int lives, Room room) {
		name = n;
		livesLeft = lives;
		currentRoom = room;
	}//END constructor

	public Boolean moveRoom (Room nextRoom) {
		System.out.println("Correct! Moving on...");
		currentRoom = nextRoom;

		return true;
	}//END moveRoom

	public int getLives() {
		return livesLeft;
	}//END getLives

	public void loseLife() {
		livesLeft -= 1;
	}//END loseLife

	public void playerAttacked() {
		System.out.println("Room contains a monster!");
		System.out.println("You have been attacked!");
		System.out.println("You have lost 1 life.");

		loseLife();
		System.out.println("You have " + getLives() + " lives remaining.");
	}//END playerAttacked

	public void determineChoice(String choice) {
		Boolean correct;
		if (choice.equals("blue")) {
			if (currentRoom.blueHasMonster()) {
				playerAttacked();
			}
			else {
				correct = moveRoom(currentRoom.getBlueRoom());
			}//END if
		}
		else if (choice.equals("red")) {
			if (currentRoom.redHasMonster()) {
				playerAttacked();
			}
			else {
				correct = moveRoom(currentRoom.getRedRoom());
			}//END if
		}//END if
	}//END determineChoice

	public Room getCurrentRoom() {
		return currentRoom;
	}//END getCurrentRoom

	public void setLives(int lives) {
		livesLeft = lives;
	}

	public void win() {
		System.out.println("/// Congratulations! You have reached ////");
		System.out.println("///// The Great Hall. You have won. //////");
	}//END win
}//END class Player