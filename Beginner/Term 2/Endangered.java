//Author - Oliver Etherington
//Program to determine endangered animals and determine which animal is
//least endangered when the exit code "EXTERMINATE" is entered.

import javax.swing.*;
import java.util.*;

class Endangered {
  public static void main(String[] param) {
    animals();
    System.exit(0);
  }//END main

  public static void animals() {
    List<String> name = new ArrayList<String>();
    List<Integer> numberleft = new ArrayList<Integer>();

    String x = "";
    int y = 0;
    boolean loop = true; //Value does not change so while loop runs infinitely
    while (loop) {
      x = input("Name an animal? (EXTERMINATE to quit)");
      if (x.equalsIgnoreCase("EXTERMINATE")) {
        break;
      }//END if
      name.add(x);

      y = Integer.parseInt(input("How many are left in the wild?"));//Requires parsing as returns
      numberleft.add(y);                                            //string
    }//END while

    int greatestleft = 0;
    int index = 0;
    for(int i = 0; i <= (numberleft.size()-1); i++) {
      if (numberleft.get(i) > greatestleft) {
        greatestleft = numberleft.get(i);
        index = i;
      }//END if
    }//END for
    print("The least endangered animal is the " + name.get(index) + "\nThere are still " + greatestleft + " left in the wild.");
  }//END animals

  public static String input (String str) {
    String z = JOptionPane.showInputDialog(str);
    return z;
  }//END input

  public static void print (String msg) {
    JOptionPane.showMessageDialog(null, msg);
  }//END print
}//END class
