//Author - Oliver Etherington
//Program takes input from user of height and weight to calculate bmi.
//Prints result to user.

import javax.swing.*;
import java.text.DecimalFormat;

class BmiCalc {
  public static void main(String[] param) {
    calc();
    System.exit(0);
  } //END main

  public static void calc() {
    String height = JOptionPane.showInputDialog("Enter your height: (in cm)");
    //Convert strings to integers
    double height2 = 0; boolean heightcheck = false; //Initialise as false until check is complete
    //While statement runs while input is equal to or less than 0 prompting appropriate
    //error messages and reprompts user for data.
    while (heightcheck == false) {
      height2 = Double.parseDouble(height);
      System.console().writer().println(height2);
      if (height2 > 0) {
        heightcheck = true;
      }
      else if (height2 == 0) {
        JOptionPane.showMessageDialog(null, "Your height cannot be 0!");
        height = JOptionPane.showInputDialog("Enter your height: (in cm)");
      }
      else if (height2 < 0) {
        JOptionPane.showMessageDialog(null, "Your height cannot be less than 0!");
        height = JOptionPane.showInputDialog("Enter your height: (in cm)");
      }
    }//END while

    String weight = JOptionPane.showInputDialog("Enter your weight: (in kg)");
    double weight2 = 0; boolean weightcheck = false; //Initialise as false until check is complete
    //While statement runs while input is equal to or less than 0 prompting appropriate
    //error messages and reprompts user for data.
    while (weightcheck == false) {
      weight2 = Double.parseDouble(weight);
      System.console().writer().println(weight2);
      if (weight2 > 0) {
        weightcheck = true;
      }
      else if (weight2 == 0) {
        JOptionPane.showMessageDialog(null, "Your weight cannot be 0!");
        weight = JOptionPane.showInputDialog("Enter your weight: (in kg)");
      }
      else if (weight2 < 0){
        JOptionPane.showMessageDialog(null, "Your weight cannot be less than 0!");
        weight = JOptionPane.showInputDialog("Enter your weight: (in kg)");
      }
    }//END while

    height2 = (height2/100);  //Convert cm to m

    double bmi; String bmi2;  //bmi2 needs to be a string as DecimalFormat() returns a string
    bmi = (weight2/(height2*height2));  //BMI formula
    DecimalFormat df = new DecimalFormat("##.");  //Format result to nearest s.f.    
    bmi2 = df.format(bmi); //Returned as string and prints string below,
                           // so no need to convert back
    JOptionPane.showMessageDialog(null, "Your BMI is " + bmi2);
  }//END calc
}//END class
