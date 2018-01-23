//Room object class which stores properties of the room and can nest more rooms in order to construct maze.

public class Room {
	private String name;

	private Room blueDoorRoom;
	private Room redDoorRoom;

	private Boolean containsMonster;
	private Boolean isFinalRoom;

	//Standard Room Constructor
	public Room (String n, Room blue, Room red, Boolean monster, Boolean last) {
		name = n;
		blueDoorRoom = blue;
		redDoorRoom = red;
		containsMonster = monster;
		isFinalRoom = last;
	}//END constructor

	//Monster Room Constructor
	public Room(String n, Boolean monster, Boolean last) {
		name = n;
		containsMonster = monster;
		isFinalRoom = last;
	}//END constructor

	public String getRoomName() {
		return name;
	}//END getName

	public Boolean hasMonster() {
		return containsMonster;
	}//END hasMonster

	public Boolean blueHasMonster() {
		return blueDoorRoom.hasMonster();
	}//END hasMonster

	public Boolean redHasMonster() {
		return redDoorRoom.hasMonster();
	}//END redHasMonster

	public Room getBlueRoom() {
		return blueDoorRoom;
	}//END getBlueRoom

	public Room getRedRoom() {
		return redDoorRoom;
	}//END getRedRoom

}//END class Room