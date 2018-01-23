//Oliver Etherington
//Pirate object class storing the name of the pirate, the current number of coins (in a purse) and an array of maps of islands.

import java.util.Random;

public class Pirate {
  private String name;
  private GoldCoin[] purse = new GoldCoin[100];
  private Island[] map = new Island[20];

  public Pirate(String n, Island[] mp) {
    name = n;
    map = mp;
  }//END Constructor Pirate

  public Island searchIsland(String n) {
    //For each island on map
    for (Island isle : map) {
      //If island is found, return Island
      //Else, return null

      if (isle.getName().equals(n)) {
        speak("Aye, I found it");
        return isle;
      }//END if
    }//END for

    speak("No good");
    return null;
  }//END searchIsland

  public int getTreasure(Island lookIn) {
    String[] locations = lookIn.getLocations();
    //For every location on island, look for treasure
    int i = 0;
    GoldCoin newCoin;
    for (String place : locations) {
      if (lookIn.dig(i) != null) {
        TreasureChest found = lookIn.dig(i);
        newCoin = found.takeOneGoldCoin();

        //While there is still a coin in the chest
        while (newCoin != null) {
          //Add the new coin to your purse at first available slot
          for (int j = 0; j < purse.length; j++) {
            if (purse[j] == null) {
              purse[j] = newCoin;
              break;
            }//END if
          }//END for
          newCoin = found.takeOneGoldCoin();
        }//END while
        return 1;
      }
      else {
        i++;
      }//END if
    }//END for
    return -1;
  }//END TreasureChest

  public void speak(String phrase) {
    String[] options = {", arrr!", ", shiver me timbers!", ", avast!", ", ahoy, matey!", ", yo, ho ho!"};
    int randomOption = (int)(Math.random() * 4) + 1;
    System.out.println(phrase + options[randomOption]);
  }//END speak

  public int getTotalCoins() {
    int numberOfCoins = 0;
    for (int i = 0; i < purse.length; i++) {
      if (purse[i] != null) {
        numberOfCoins++;
      }//END if
    }//END for
    return numberOfCoins;
  }//END getTotalCoins
}//END class Pirate
