//Oliver Etherington
//GoldCoin object class storing a unique coin number and numbers of every coin before it.

import java.util.Random;

public class GoldCoin {
  //Assigned by the Eastern Trading Company - want to keep track of them
  private int coinNumber;
  private Boolean[] usedCoins = new Boolean[100]; //Keep track of numbers already used

  int max = usedCoins.length;
  int min = 1;
  int range = max - min;

  public GoldCoin () {
    //Randomly generate a UNIQUE number here
    coinNumber = (int)(Math.random() * range) + min;
    //Check for uniqueness otherwise, generate a new number
    while (usedCoins[coinNumber] != null) {
      coinNumber = (int)(Math.random() * range) + min;
    }//END while

    //Then mark number as being used
    usedCoins[coinNumber] = true;
  }//END Constructor GoldCoin

  public int getCoinNumber() {
    return coinNumber;
  }//END getCoinNumber
}//END class GoldCoin
