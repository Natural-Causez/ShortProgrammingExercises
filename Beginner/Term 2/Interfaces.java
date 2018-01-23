//Author - Oliver Etherington
//Program to collect information about usability of two interfaces

import javax.swing.*;

class Interfaces {

  public static void main(String[] param) {
    mistakes();
    System.exit(0);
  } //END main

  public static void mistakes() {
    int interface1 = 0; //Counters for number of mistakes
    int interface2 = 0;
    int noofmistakes = 0;
    for (int i = 1; i <= 2; i++) {
      //Nested for loops to reduce lines of code

      for(int x = 1; x <= 5; x++) {
        noofmistakes = Integer.parseInt(input("How many mistakes did participant " + x + " make on interface " + i + "?"));
        if (i == 1) {
          interface1 = interface1 + noofmistakes;
        }
        else if (i == 2) {
          interface2 = interface2 + noofmistakes;
        }//END if
      }//END for
    }//END for

    print(interface1 + " mistakes were made with interface 1");
    print(interface2 + " mistakes were made with interface 2");
    if (interface1 > interface2) {
      print("Interface 2 seems to be easier to use than interface 1");
    }
    else if (interface2 > interface1) {
      print("Interface 1 seems to be easier to use than interface 2");
    }//END if
  }//END mistakes

  //Short-hand for JOptionPane commands
  public static String input (String msg) {
    String input = "";
    input = JOptionPane.showInputDialog(msg);
    return input;
  }// END input

  //Short-hand for JOptionPane commands
  public static void print (String str) {
    JOptionPane.showMessageDialog(null, str);
  }// END print

} //END class
