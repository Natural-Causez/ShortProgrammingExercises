//Author - Oliver Etherington
//Program to record a tally of votes for a changable set of paintings

import javax.swing.*;

class Paintings {
  public static void main (String[] param) {
    tally();
    System.exit(0);
  }

  public static void tally() {
    String[] paintings = {"Mona Lisa", "Water Lilies", "The Scream", "A Young Rembrandt"};
    int[] paintingscores = new int[15];//Make sure this number [10] matches the number of
                                       //paintings to be judged against
    int nopaintings = 0;
    nopaintings = paintings.length;
    String intromsg = "Please tell us which painting you think is the best?\n";
    for (int element = 0; element <= (nopaintings-1); element++) {
      intromsg = intromsg + "\nVote " + ((char)(65+element)) + " for " + paintings[element];
    }//END for

    char choice = 'a';
    int ascii = 0;
    String ans = "You voted for ";
    int index = 0;
    while (choice != 0) {
      String scores = "\nThe current votes are\n";
      choice = input(intromsg);
      if (choice == '0') {
        break;
      }//END if
      ascii = (int) choice; //Converts char value to ascii value
      index = ascii-65; //Correct for array elements to start at 0
      if (((ascii-65) >= nopaintings) || ((ascii-97) >= nopaintings)) {
        print("Invalid value!");
      }
      else {
        paintingscores[index] = paintingscores[index] + 1;
        for (int element = 0; element <= (nopaintings-1); element++) {
          scores = scores + paintingscores[element] + " : " + paintings[element] + "\n";
        }//END for
        print(ans + paintings[index] + scores);
      }//END if
    }//END while


  }//END tally()

  //Modified short-hand for JOptionPane commands as dealing with chars
  public static char input(String str) {
    char z;
    String y = "";
    y = JOptionPane.showInputDialog(str);
    z = y.charAt(0);
    return z;
  }//END input()

  //Short-hand for JOptionPane commands
  public static void print(String prnt) {
    JOptionPane.showMessageDialog(null, prnt);
  }//END print()
}//END class Paintings
