//Oliver Etherington
//TreasureChest object class defining the number of coins and their unique IDs
//Can extract one coin at a time.

public class TreasureChest {
  private GoldCoin[] goldCoins = new GoldCoin[17]; //Defined by assignment

  public TreasureChest() {
    //Create 17 gold coins and store in goldCoins[]
    generateCoins();
  }//END Constructor TreasureChest

  public GoldCoin takeOneGoldCoin() {
    //Only while there are coins remaining
    if (goldCoins.length > 0) {
      int i = 0;
      for (GoldCoin coin : goldCoins) {
        if (goldCoins[i] != null) {
          goldCoins[i] = null;
          return coin;
        }//END if
        i++;
      }//END for
    }//END if
    return null;
  }//END takeOneGoldCoin

  public void generateCoins() {
    GoldCoin coin;
    //Fill array goldCoins with GoldCoins
    for (int i = 0; i < goldCoins.length; i++) {
      coin = new GoldCoin();
      goldCoins[i] = coin;
    }//END for
  }//END generateCoins
}//END Class TreasureChest
