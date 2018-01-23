//Oliver Etherington
//Driver class for GoldCoin.java, TreasureChest.java, Island.java & Pirate.java
//
//A scenario of various islands which contain multiple locations, some of which
//may or may not contain treasure. Pirates search for treasure and store the
//GoldCoins found in their purse.

import java.util.Scanner;

public class TreasureHunt {
  public static void main(String[] args) {
    Island island1 = new Island("Guadeloupe", new TreasureChest());
    Island island2 = new Island("Port Royal", new TreasureChest());
    Island island3 = new Island("Tortuga", new TreasureChest());

    Island[] islandMap = {island1, island2, island3};


    Pirate pirate1 = new Pirate("Captain Pegg", islandMap);
    Island newIsland;
    int newTreasure;

    String userInp = "";
    Scanner scan = new Scanner(System.in);

    pirate1.speak("Give me an island");
    userInp = scan.nextLine();

    while (!userInp.equals("stop")) {
      //Search for users island
      newIsland = pirate1.searchIsland(userInp);
      if (newIsland != null) {
        newTreasure = pirate1.getTreasure(newIsland);
        if (newTreasure == 1) {
          pirate1.speak(userInp + " was a fine choice");
          pirate1.speak("I now hold " + pirate1.getTotalCoins() + " coins");
        }//END if
        if (newTreasure == -1) {
          pirate1.speak(userInp + " was empty, arrrggg");
        }//END if
      }//END if
      pirate1.speak("Give me another island");
      userInp = scan.nextLine();
    }//END while

  }//END main
}//END class TreasureHunt
