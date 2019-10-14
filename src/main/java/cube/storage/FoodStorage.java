package cube.storage;

import cube.model.food.Food;
import cube.model.food.FoodList;

public class FoodStorage {
	private FoodList foodList;

	public FoodStorage() {
		this.foodList = new FoodList();
	}

	public FoodStorage(FoodList foodList) {
		this.foodList = foodList;
	}

	public FoodList getFoodList() {
		return foodList;
	}

	public void appendFood(Food food) {
		foodList.add(food);
	}

	public void storeFoodList(FoodList foodlist) {
		this.foodList = foodlist;
	}
}