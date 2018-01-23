//Oliver Etherington
//GoldCoin object class for currency in shop

import java.util.Random;

public class GoldCoin {
  //Assigned by the Eastern Trading Company - want to keep track of them
  private final int coinNumber;
  private Boolean[] usedCoins = new Boolean[100]; //Keep track of numbers already used

  int max = usedCoins.length;
  int min = 1;
  int range = max - min;

  public GoldCoin () {
    //Randomly generate a UNIQUE number here
    int randomInt = (int)(Math.random() * range) + min;
    //Check for uniqueness otherwise, generate a new number
    while (usedCoins[randomInt] != null) {
      randomInt = (int)(Math.random() * range) + min;
    }//END while

    coinNumber = randomInt;
    //Then mark number as being used
    usedCoins[randomInt] = true;
  }//END Constructor GoldCoin

  public int getCoinNumber() {
    return coinNumber;
  }//END getCoinNumber
}//END class GoldCoin
