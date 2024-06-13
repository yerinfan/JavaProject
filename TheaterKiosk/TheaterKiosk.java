package TheaterKiosk;

import TheaterKiosk.controller.TheaterKioskController;
import TheaterKiosk.model.MovieCartItem;
import TheaterKiosk.model.MovieList;
import TheaterKiosk.view.ConsoleView;

public class TheaterKiosk {
    public static void main(String[] args)throws Exception {
    	// model 생성
    	MovieList movieList = new MovieList();
    	MovieCartItem movieCartItem = new MovieCartItem();
    	
    	// view 생성
    	ConsoleView view = new ConsoleView();
    	
        TheaterKioskController controller = new TheaterKioskController(movieList, movieCartItem, view);
        controller.start();
    }
}
