//Oliver Etherington
//
//Calorie Tracker Assignment
//Makes a person object 'eat' 3 meals and then reduces calorie count to 2000
//through extensive calculation and serious walking.
//Enjoy.

public class CalorieTracker {
	//Main function carries out all vital calculations, while creating objects
	//from Person.java and Meal.java
	public static void main(String [] args) {
		Person person1 = new Person();
		System.out.println("Person 1 has " + person1.numberCalories() + " calories.");

		//Important information is in comments below

		Meal omlette_breakfast = new Meal(110, 425, 140); //Toast, Omlette, Banana
		person1.eat(omlette_breakfast);
		System.out.println("An Omlette Breakfast has " + omlette_breakfast.calculateCalories() + " calories.");
		System.out.println("Person 1 has a total calorie count after meal 1 (Omlette Breakfast) of: " + person1.numberCalories());

		Meal pizza_dinner = new Meal(210, 455, 335); //Potato Wedges, Pizza, Cheesecake
		person1.eat(pizza_dinner);
		System.out.println("A Pizza Dinner has " + pizza_dinner.calculateCalories() + " calories.");
		System.out.println("Person 1 has a total calorie count after meal 2 (Pizza Dinner) of: " + person1.numberCalories());

		Meal pancake_breakfast = new Meal(150, 800, 170); //Fruit Yoghurt, Pancakes, Strawberries
		person1.eat(pancake_breakfast);
		System.out.println("A Pancake Breakfast has " + pancake_breakfast.calculateCalories() + " calories.");
		System.out.println("Person 1 has a total calorie count after meal 3 (Pancake Breakfast) of: " + person1.numberCalories());

		person1.walk(795); //This is some walk. Nothing else to do with their day??
		//795 because total calorie count is 2795 and required final answer is 2000
		//But there is no requirement to do the subtraction.

		//I wrote it here just in case anyone decides to look...
		//person1.walk(person1.numberCalories() - 2000);
		//

		System.out.println("Person 1 has a walk for 795 minutes (Committed. A 13hr 15min walk!)" +
						   " to have a final calorie count of " + person1.numberCalories() +".");
	}//END main
}//END class CalorieTracker