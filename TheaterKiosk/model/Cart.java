package TheaterKiosk.model;

import java.util.ArrayList;

public class Cart {
	//private CartItem[] itemList = new CartItem[64];
		private ArrayList<CartItem> itemList = new ArrayList<>();
		//private int numItems = 0;

		public boolean isEmpty() {
			return itemList.isEmpty();
		}

		public ArrayList<CartItem> getItemList() {
			return itemList;
		}

		public int getNumItems() {
			return itemList.size();
		}

		public String getItemInfo(int index) {
			return itemList.get(index).toString();
		}

		public void addItem(Food food) {
			
			CartItem item = getCartItem(food);
			if (item == null) {
				itemList.add(new CartItem(food));
			}
			else {
				item.addQuantity(1);
			}
		}
		
		private CartItem getCartItem(Food food) {
			
			for (CartItem item : itemList) {
				if (item.getFood() == food) return item;
			}
			
			return null;
		}
		
		private CartItem getCartItem(int foodId) {
			for (CartItem item : itemList) {
				if (item.foodId == foodId) return item;
			}
			return null;
		}
		

		public void resetCart() {
			itemList.clear();
		}

		public boolean isValidItem(int foodId) {
			for (CartItem item : itemList) {
				if (item.foodId == foodId) return true;
			}
			return false;
		}

		public void deleteItem(int foodId) {
			CartItem item = getCartItem(foodId);
			itemList.remove(item);
		}

		public void updateQuantity(int foodId, int quantity) {
			
			if (quantity == 0)
				deleteItem(foodId);
			else {
				CartItem item = getCartItem(foodId);
				item.setQuantity(quantity);
			}
			
		}

		public int getTotalPrice() {
			int totalPrice = 0;
			for (CartItem item : itemList) {
				totalPrice += item.getPrice();
			}
			return totalPrice;
		}

		
}
