package TheaterKiosk;

import TheaterKiosk.controller.TheaterKioskController;
import TheaterKiosk.model.Cart;
import TheaterKiosk.model.FoodStorage;
import TheaterKiosk.model.MovieCartItem;
import TheaterKiosk.model.MovieList;
import TheaterKiosk.view.ConsoleView;

public class TheaterKiosk {
    public static void main(String[] args)throws Exception {
    	// model 생성
    	MovieList movieList = new MovieList();
    	MovieCartItem movieCartItem = new MovieCartItem();
    	FoodStorage foodStorage = new FoodStorage();
    	Cart cart = new Cart();
    	// view 생성
    	ConsoleView view = new ConsoleView();
    	
        TheaterKioskController controller = new TheaterKioskController(movieList, movieCartItem, foodStorage, cart, view);
        controller.start();
    }
}
