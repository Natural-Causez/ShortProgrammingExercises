//Person object class with constructor and one class method

public class Meal {
	int starter_dish;
	int main_dish;
	int dessert_dish;

	public Meal(int starter, int main, int dessert) {
		starter_dish = starter;
		main_dish = main;
		dessert_dish = dessert;
	}//END constructor

	public int calculateCalories() {
		return starter_dish + main_dish + dessert_dish;
	}//END calculateCalories
}//END class Meal