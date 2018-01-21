//Test file for ensuring the object Counter behaves as expected

import javax.swing.*;
import java.util.*;

public class TestCounter {
  public static void main(String[] args) {
    Counter ctr = new Counter(); //Create Object
            
    for (int i = 0; i<=3; i++) {
      ctr.increment();    //Increment instance of ctr
      System.out.println("Value of counter instance within object, ctr: " + ctr.getValue() + "\n");
    } //END for
            
    String str = ""; int n = 0;
    str = JOptionPane.showInputDialog("Enter a number to decrease the instance by: \n");
    n = Integer.parseInt(str);
           
    if ((ctr.getValue() - n) < 0) {
      System.out.println("ctr Object cannot hold values less than 0!");
    }
    else {
      ctr.decrease(n);
      System.out.println("Value of counter instance within object, ctr: " + ctr.getValue() + "\n");
    }// END if
                
    ctr.reset();

  } //END main
} //END class TestCounter
