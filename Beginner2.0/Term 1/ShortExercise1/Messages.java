//Message object class - simple printing to screen

public class Messages {
	
	public static void main(String[] args) {
		namePrinter();
		wordPrinter();
		incrementPrinter(3);
	}//END main

	//Prints my name to screen
	public static void namePrinter() {
		System.out.println("Oliver");
	}//END namePrinter

	//Prints the word 'Mike' to screen
	public static void wordPrinter() {
		System.out.println("Mike");
	}//END wordPrinter

	/*Takes an integer as an input and increments the value by 1 before
	  printing the output to screen*/
	public static void incrementPrinter(int x) {
		System.out.println(x + 1);
	}//END incrementPrinter
}//END class Messages