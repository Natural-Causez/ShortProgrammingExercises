//Author - Oliver Etherington
//Program to record the number of certain birds seen at
//the same time on different days

import javax.swing.*;
import java.util.*;

class GardenWatch {
  public static void main(String[] args) {
    //Can add/change the birds to be observed inside this array
    String[] birds = {"Blue Tit", "Blackbird", "Robin", "Wren", "Greenfinch"};
    int nobirds = 0;
    nobirds = birds.length;
    Boolean[] birdseen = new Boolean[nobirds]; //Array elements correspond to birds[]

    cleanrecord(birdseen);
    newreport(birds, birdseen, nobirds);
    printreport(birds, birdseen, nobirds);

    System.exit(0);
  }//END main

  public static void cleanrecord(Boolean[] birdseen) { 
    //Initialises array to all false
    Arrays.fill(birdseen, false);
  }//END cleanrecord

  public static void newreport(String[] birds, Boolean[] birdseen, int nobirds) {
    String birdstr = "What bird are you reporting?\n";
    for (int x = 0; x <= (nobirds-1); x++) { 
      //Constructs the string for any length of birds[]
      birdstr =  birdstr + (x+1) + ") " + birds[x] + "\n";
    }//END for
    birdstr = birdstr + "\nPlease enter selection (100 to QUIT): ";

    int choice = 0;
    while (choice != 100) {
      choice = input(birdstr);
      if (choice == 100) {
        break;
      }
      else if ((choice <= 0) || (choice > nobirds)) {
        print("Invalid choice!");
      }
      else {
        birdseen[choice-1] = true;
      }//END if
    }//END while
  }//END newreport

  public static void printreport(String[] birds, Boolean[] birdseen, int nobirds) {
    String report = "Your Garden Watch results are:\n";
    for (int i = 0; i <= (nobirds-1); i++) {
      report = report + (i+1) + ")" + birds[i] + ":  ";
      if (birdseen[i] == true) {
        report = report + "YES\n";
      }
      else if (birdseen[i] == false) {
        report = report + "NO\n";
      }//END if
    }//END for

    print(report);
  }//END printreport

  public static Integer input(String inp) {
    int z = 0;
    try {
      z = Integer.parseInt(JOptionPane.showInputDialog(inp));
    }
    catch (NumberFormatException ex) {
      print("Input must be an integer!");
    }
    
    return z;
  }//END input

  public static void print (String msg) {
    JOptionPane.showMessageDialog(null, msg);
  }//END print
}//END class gardenwatch
