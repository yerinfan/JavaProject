package TheaterKiosk.model;

public class Food {
	private int foodId;
	private String foodName;
	private int price;

	public Food(int foodId, String foodName, int price) {
		this.foodId = foodId;
		this.foodName = foodName;
		this.price = price;
	}

	public int getFoodId() {
		return foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "["+ foodId + ". " + foodName + ", " + price + "원]";
	}
}
