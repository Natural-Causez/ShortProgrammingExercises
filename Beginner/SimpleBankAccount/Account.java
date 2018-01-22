//A simple bank account holding the interest and balance
//Calculates compound interest that only applies every 12 months

import javax.swing.*;
import java.util.*;

public class Account {
    private double balance;
    private double interestRate;
    
    public Account(double initialBalance) {
        balance = initialBalance;
    } //END constructor Account
    
    public void deposit(double amount) {
        if (amount < 0) {
            System.out.println("Cannot deposit amount less than 0!");
        }
        else {
            balance = balance + amount;
        }//END if
    }//END deposit

    public void withdraw(double amount) {
        if (amount < 0) {
            System.out.println("Cannot withdraw amount below 0!");
        }
        else {
            balance = balance - amount;
        }//END if
    }//END withdraw
         
    public void getBalance() {
        System.out.println("Current balance is: " + balance);
    }//END getBalance
         
    public void setInterest(double rate) {
        interestRate = rate;
    }//END setInterest
        
    public void computeInterest(int n) {
        double x = 1 + interestRate;
        double tempBalance = 0;
        int months = n;

        if (n < 12) {
            tempBalance = balance;
        }
        tempBalance = balance * x;
        n -= 12;

        while (n >= 12) {
            tempBalance = tempBalance * x;
            n -= 12;
        }//END while

        String str = String.format("%.2f", tempBalance); //Format result to 2 decimal places
        System.out.println("Balance at the end of " + months + " months: £" + str);
    }//END computeInterest

    public void targetMonths(double target) {
        String targetStr = String.format("%.2f", target); //Add £.00 if necessary
        String balanceStr = String.format("%.2f", balance); //Formatting            
            
        double n = (Math.log(target/balance)) / (Math.log(1 + interestRate));
        
        if (target < balance) {
            n = 0; //Catch case when target is already met
        }
        else if (n < 1) {
            n = 12; //Catch case when annual interest may not have applied yet
        }
        else {
            double a = (n % 1); //Reduce to fraction, if any
                    
            if (a == 0) {
                n = n * 12;
            }
            else {
                n = Math.round(n + 0.5);
                n = n * 12;
            }//END if
                    
        }//END if
        
        int x = (int)n;
            
        System.out.println("To reach £" + targetStr + " from £" + balanceStr + " it will take " + x + " months.");
    }//END targetMonths
} //END class Account
