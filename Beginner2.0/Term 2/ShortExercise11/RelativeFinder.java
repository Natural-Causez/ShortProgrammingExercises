//Humerous ode to A series of unfortunate events in which the program has to evaluate the 'best' place to send the children
//for their own safety. Comparison of the trait 'friendliness'.

import java.util.Scanner;

public class RelativeFinder {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        //Create Person Objects and List contents
        Person joesphine = new Person("Josephine", 5);
        Person uncleMonty = new Person("Uncle Monty", 5);
        Person olaf = new Person("Olaf", -10);
        Person sir = new Person("sir", 0);
        
        Banker mrPoe = new Banker("MrPoe", 5);
        
        mrPoe.addRelative(joesphine.getName(), 100, joesphine.getLikeness());
        mrPoe.addRelative(uncleMonty.getName(), 20, uncleMonty.getLikeness());
        mrPoe.addRelative(olaf.getName(), 10, olaf.getLikeness());
        mrPoe.addRelative(sir.getName(), 20, sir.getLikeness());
        
        //Run until User enters "2"
        while (true) {
            System.out.println("Please enter a value: ");
            System.out.println("1.) Move Children to new home");
            System.out.println("2.) Exit");
            String input = scan.next();    
            if (input.equals("1")) {
                mrPoe.FindRelative(mrPoe);
            }//END if
            
            if (input.equals("2")) {
                return;
            }//END if
        }//END while
    }//END main
    
}//END class RelativeFinder
