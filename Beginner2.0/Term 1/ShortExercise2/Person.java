//Person object class to manipulate Meal object class and more class methods.

public class Person {
	int calories = 0;

	public int numberCalories() {
		return calories;
	}//END numberCalories

	public void eat(Meal m) {
		calories += m.calculateCalories();
	}//END eat

	public void walk(int minutes) {
		calories -= minutes;
	}//END walk
}//END class Person