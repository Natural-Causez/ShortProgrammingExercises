//Simple bubble sort for input of 3 numbers from user
//Prints the sorted list then the center value

import java.util.Scanner;
import java.util.InputMismatchException;

public class Middle {

  public static void main(String[] args) {
    sort();
    System.exit(0);
  } //END main

  public static void sort() {
    Scanner sc = new Scanner(System.in); //Scanner object
    double a; double b; double c; //Variables to store input

    System.out.println("Enter 3 numbers to be sorted: ");

    //a = catchcase(sc);
    //b = catchcase(sc);
    //c = catchcase(sc);

    Double[] numlist = new Double[3];
    for (int i = 0; i <= 2; i++) {
        a = catchcase(sc);
        numlist[i] = a;
      }//END for

    ////////////// TESTS //////////////
    //System.out.println("a is " + a);
    //System.out.println("b is " + b);
    //System.out.println("c is " + c);
    /*
    if ((((a < b) && (a > c))) || ((a > b) && (a < c))) {
      System.out.println(a + " is between " + b + " and " + c);
    }
    else if ((((b < a) && (b > c))) || ((b > a) && (b < c))) {
      System.out.println(b + " is between " + a + " and " + c);
    }
    else if ((((c < a) && (c > b))) || ((c > a) && (c < b))) {
      System.out.println(c + " is between " + a + " and " + b);
    }//END if
    */
    ////////////////////////////////////

    ////// BUBBLE SORT ///////
    int n = numlist.length;
    Double temp = 0.0;

    for (int i = 0; i < n; i++) {
      for (int j = 1; j < (n-i); j++) {
        if (numlist[j-1] > numlist[j]) {
          temp = numlist[j-1];
          numlist[j-1] = numlist[j];
          numlist[j] = temp;
        }//END if
      }//END for
    }//END for
    ///////////////////////////

    printresult(numlist);
  } // END sort

  public static double catchcase(Scanner sc) {
    Boolean input = false; double z = 0;

    while (!input) {
      try {
        z = sc.nextDouble();
        input = true;
      } //END try

      catch (InputMismatchException mm) {
        System.out.println("Input must be a number!");
        sc.nextLine();
      } // END catch

    } //END while

    return z;
  } // END catchcase

  public static void printresult(Double[] numlist) {
    String str = "{";
    str = str + numlist[0] + ", " + numlist[1] + ", " + numlist[2] + "}";
    System.out.println(str);

    System.out.println(numlist[1] + " is between " + numlist[0] + " and " + numlist[2]);
  }//END printresult
} // END class Middle
