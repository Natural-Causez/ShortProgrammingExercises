//Author - Oliver Etherington
//Program takes input from user to determine the zone a particular
//London Underground station is in. Message is returned to user with result.

import javax.swing.*;

class Zone {
  public static void main (String [] param) {
    zonecalc();
    System.exit(0);
  }//END main

  public static void zonecalc() {
    //List of stations that program recognises:
    //zone1 = Baker Street, Knightsbridge, Oxford Circus
    //zone2 = Arsenal, Canary Wharf, Oval
    //zone3 = Canning Town, Park Royal, Stonebridge Park
    //zone4 = Barking, Hounslow Central, Richmond
    //zone5 = Cockfosters, Hatton Cross, South Ruislip
    //zone6 = Epping, Northwood, Upminster
    String userinput = JOptionPane.showInputDialog("Which station do you need to know the zone of?");
    //Each branch compares for a different zone
    if (userinput.equalsIgnoreCase("Baker Street") || userinput.equalsIgnoreCase("Knightsbridge") || userinput.equalsIgnoreCase("Oxford Circus")) {
      //equalsIgnoreCase matches strings with or without capitals - still needs to match string exactly
      JOptionPane.showMessageDialog(null, userinput + " is in Zone 1.");
    }
    else if (userinput.equalsIgnoreCase("Arsenal") || userinput.equalsIgnoreCase("Canary Wharf") || userinput.equalsIgnoreCase("Oval")) {
      JOptionPane.showMessageDialog(null, userinput + " is in Zone 2.");
    }
    else if (userinput.equalsIgnoreCase("Canning Town") || userinput.equalsIgnoreCase("Park Royal") || userinput.equalsIgnoreCase("Stonebridge Park")) {
      JOptionPane.showMessageDialog(null, userinput + " is in Zone 3.");
    }
    else if (userinput.equalsIgnoreCase("Barking") || userinput.equalsIgnoreCase("Hounslow Central") || userinput.equalsIgnoreCase("Richmond")) {
      JOptionPane.showMessageDialog(null, userinput + " is in Zone 4.");
    }
    else if (userinput.equalsIgnoreCase("Cockfosters") || userinput.equalsIgnoreCase("Hatton Cross") || userinput.equalsIgnoreCase("South Ruislip")) {
      JOptionPane.showMessageDialog(null, userinput + " is in Zone 5.");
    }
    else if (userinput.equalsIgnoreCase("Epping") || userinput.equalsIgnoreCase("Northwood") || userinput.equalsIgnoreCase("Upminster")) {
      JOptionPane.showMessageDialog(null, userinput + " is in Zone 6.");
    }
    else {
      JOptionPane.showMessageDialog(null, "Are you sure \"" + userinput + "\" is a London Underground station? Check spelling for errors.");
    }//END if

  }//END zonecalc
}//END class Zone
