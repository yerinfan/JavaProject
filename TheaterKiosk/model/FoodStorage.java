package TheaterKiosk.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FoodStorage {
	ArrayList<Food> foodList = new ArrayList<>();
	final int MAX_QUANTITY = 10;
	private String foodFilename = "foodlist.txt";
	private int lastId;
	private boolean isSaved;

	public FoodStorage() throws IOException {
		loadFoodListFromFile();
		generateLastId();
		isSaved = true;
	}

	private void generateLastId() {
		lastId = 0;
		for (Food food : foodList) {
			int id = food.getFoodId();
			if (id > lastId)
				lastId = id;
		}
	}

	private void loadFoodListFromFile() throws IOException {
		FileReader fr;
		try {
			fr = new FileReader(foodFilename);
			BufferedReader br = new BufferedReader(fr);
			String idStr;
			while ((idStr = br.readLine()) != null && !idStr.equals("")) {
				int id = Integer.parseInt(idStr);
				String menuName = br.readLine();
				int price = Integer.parseInt(br.readLine());
				foodList.add(new Food(id, menuName, price));
			}
			fr.close();
			br.close();

		} catch (FileNotFoundException | NumberFormatException e) {
			System.out.println(e.getMessage());
		}
	}

	public int getNumFoods() {
		return foodList.size();
	}

	public String getFoodInfo(int i) {
		return foodList.get(i).toString();
	}

	public boolean isValidFood(int foodId) {
		for (Food food : foodList) {
			if (food.getFoodId() == foodId)
				return true;
		}
		return false;
	}

	public Food getFoodById(int foodId) {
		for (Food food : foodList) {
			if (food.getFoodId() == foodId)
				return food;
		}
		return null;
	}

	public int getMaxQuantity() {
		return MAX_QUANTITY;
	}

	public boolean isEmpty() {
		return foodList.size() == 0;
	}

	public void deleteItem(int foodId) {
		foodList.remove(getFoodById(foodId));
		isSaved = false;
	}

	public void addFood(String menuName,int price) {

		Food food = new Food(++lastId, menuName, price);
		foodList.add(food);
		isSaved = false;
	}

	public boolean isSaved() {
		return isSaved;
	}

	public void saveFoodList2File() {

		try {
			FileWriter fw = new FileWriter(foodFilename);
			for (Food food : foodList) {
				fw.write(food.getFoodId() + "\n");
				fw.write(food.getFoodName() + "\n");
				fw.write(food.getPrice() + "\n");
			}
			fw.close();
			isSaved = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isValidItem(int foodId) {
		for (Food food : foodList) {
			if (food.getFoodId() == foodId) return true;
		}
		return false;
	}
}
