//Author - Oliver Etherington
//Program to determine price & discount of a
//selection of hybrid or fully electric model car

import javax.swing.*;

class Cars {

  public static void main(String[] args) {
    int base = 20000; //Can change the base price here
    String engine = "";
    engine = type();

    Boolean solar = false;
    solar = panel();

    int discount = 0;
    discount = value(engine, solar);

    int total = 0;
    total = cost(base, engine, solar, discount);

    msg(base, engine, solar, discount, total);

    System.exit(0);
  }//END main

  public static String type() {
    Boolean inputcorrect = false; String ans = "";
    while (!inputcorrect) {
      ans = input("Would you like a hybrid or a fully electric model car? (Hybrid/Electric)");
      if (ans.equalsIgnoreCase("Hybrid")) {
        inputcorrect = true;
        return ans;
      }
      else if (ans.equalsIgnoreCase("Electric")) {
        inputcorrect = true;
        return ans;
      }
      else {
        print("Please enter in the correct format.");
      }//END if
    }//END while
    return ans;
  }//END type

  public static Boolean panel() {
    Boolean inputcorrect = false; String a = "";
    while (!inputcorrect) {
      a = input("Would you like a solar panel on the roof? (y/n)");
      if (a.equalsIgnoreCase("y")) {
        inputcorrect = true;
        return true;
      }
      else if (a.equalsIgnoreCase("n")) {
        inputcorrect = true;
        return false;
      }
      else {
        print("Please enter in the correct format.");
      }//END if
    }//END while
    return false; //Never returns as never gets this far - java requirement
  }//END panel

    public static Integer value (String engine, Boolean solar) {
        int discount = 0;
        if ((engine.equalsIgnoreCase("Electric")) && (solar == true)) {
            discount = discount + 500;
          }//END if
        return discount;
      }//END value

  public static Integer cost (int base, String engine, Boolean solar, int discount) {
    ////// VARIABLES ///////
    int panel = 5000;
    int electric = 2000;
    int total = base;
    ////////////////////////

    if ((engine.equalsIgnoreCase("Electric")) && (solar == true)) {
      total = base + panel + electric - discount;
      return total;
    }
    else if ((engine.equalsIgnoreCase("Hybrid")) && (solar == false)) {
      total = base;
      return total;
    }
    else if ((engine.equalsIgnoreCase("Electric")) && (solar == false)) {
      total = base + electric;
      return total;
    }
    else if ((engine.equalsIgnoreCase("Hybrid")) && (solar == true)) {
      total = base + panel;
      return total;
    }//END if
    return total;
  }//END cost

  public static void msg (int base, String engine, Boolean solar, int discount, int total) {
    String solartrue = ""; String solarprice = "";
    //Unless choice is true, strings otherwise stay empty - leaving final string unchanged
    if (solar == true) {
        solartrue = "Yes";
        solarprice = "\nSolar Panel: £5000";
    }
    else if (solar == false) {
        solartrue = "No";
    }//END if

      String electricprice = "";
      if (engine.equalsIgnoreCase("Electric")) {
        electricprice = "\nElectric Model: £2000";
      }//END if

    //Constructs final string to print - dependent on choices
    print("Your Order:\n" +
          "Type of car: " + engine +
          "\nSolar panel: " + solartrue + "\n\n" +
          "Base price: £" + base + electricprice + solarprice +
          "\nDiscount: £" + discount +
          "\nTotal: £" + total);
  }//END display

  public static String input (String inp) {
    String z = "";
    z = JOptionPane.showInputDialog(inp);
    return z;
  }//END input

  public static void print (String msg) {
    JOptionPane.showMessageDialog(null, msg);
  }//END print
}//END cars
