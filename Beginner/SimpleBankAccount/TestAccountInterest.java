//Test file for Account.java to set up and print data

import javax.swing.*;

public class TestAccountInterest {
    public static void main(String[] args) {
            //Construct objects
            Account account1= new Account(500);
            Account account2 = new Account(100);
            
            double intRateTemp = 0;
            
            intRateTemp = Double.parseDouble(JOptionPane.showInputDialog("Enter the interest rate (%) for Account 1: \n"));
            intRateTemp = intRateTemp/100;
            account1.setInterest(intRateTemp);
            
            intRateTemp = Double.parseDouble(JOptionPane.showInputDialog("Enter the interest rate (%) for Account 2: \n"));
            intRateTemp = intRateTemp/100;
            account2.setInterest(intRateTemp);
            
            int n = Integer.parseInt(JOptionPane.showInputDialog("Computing total balance + interest...\n" + "For how many months?"));
            System.out.println("\nAccount 1:");
            account1.computeInterest(n);
            System.out.println("\nAccount 2:");
            account2.computeInterest(n);
            
            double target = Double.parseDouble(JOptionPane.showInputDialog("Enter savings target: \n"));
            System.out.println("\nAccount 1:");
            account1.targetMonths(target);
            System.out.println("\nAccount 2:");
            account2.targetMonths(target);
    } //END main
} //END class TestAccountInterest
