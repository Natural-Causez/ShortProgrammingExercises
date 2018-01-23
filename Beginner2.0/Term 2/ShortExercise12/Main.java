//Text based plane simulator of a plane attempting to take off on a runway.
//Calculates if enough speed was gathered in order to take off before the end of the runway was reached.

public class Main {

	public static void main(String[] args) {
		Display d1 = new Display();
		Simulation s1 = new Simulation();
		Airplane a1 = new Airplane(5, 0);
		d1.render();
		//Runs infinitely while program is open
		while (true) {
			s1.run(a1, d1);
			
			a1 = new Airplane(5, 0);
			s1 = new Simulation();
		}//END while
	}//END main

}//END class
