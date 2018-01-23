//Oliver Etherington
//Island object class storing a name, an array of locations and an array of pieces of 'Treasure'

import java.util.Random;

public class Island {
  private String name;
  private String[] locations = new String[13];
  private TreasureChest[] treasure = new TreasureChest[locations.length];
  //Find treasure by comparing corresponding index of locations and treasure

  public Island(String n, TreasureChest chest) {
    name = n;

    //Treasure is randomly hidden at one of the locations
    int max = locations.length;
    int min = 0;
    int range = max = min;
    int hiddenAt = (int)(Math.random() * range) + min;
    treasure[hiddenAt] = chest;
    /////////////////////////////////////////////////////
  }//END Constructor

  public TreasureChest dig(int digAt) {
    //if treasure is found, return chest
    if (digAt > locations.length) {
      return null;
    }//END if

    if (treasure[digAt] != null) {
      return treasure[digAt];
    }
    else {
      return null;
    }//END if

    //else, return null
  }//END digAt

  public String getName() {
    return name;
  }//END getName

  public int getNumberLocations() {
    return locations.length;
  }//END getNumberLocations

  public String[] getLocations() {
    return locations;
  }//END getTreasures

/////////////FUNCTION FOR TESTING////////////////
  public int treasureIsAt() {
    for (int i = 0; i < locations.length; i++) {
      if (treasure[i] != null) {
        return i;
      }//END if
    }//END for
    return 0;
  }//END treasureIsAt
/////////////////////////////////////////////////
}//END class Island
