package TheaterKiosk.model;

public class CartItem {
	Food food;
	int foodId;
	int quantity;
	
	public CartItem(Food food) {
		this.food = food;
		this.foodId = food.getFoodId();
		this.quantity = 1;
	}
	
	public Food getFood() {
		return food;
	}
	public void setBook(Food food) {
		this.food = food;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void addQuantity(int quantity) {
		this.quantity += quantity;
		
	}

	@Override
	public String toString() {
		return food.getFoodId() + ", " + food.getFoodName() + ", " + quantity + "개, " + getPrice() + "원";
	}

	public int getPrice() {
		return quantity * food.getPrice();
	}
	
}
