package TheaterKiosk;

import TheaterKiosk.controller.TheaterKioskController;
import TheaterKiosk.model.MovieCart;
import TheaterKiosk.model.MovieList;
import TheaterKiosk.view.ConsoleView;

public class TheaterKiosk {
    public static void main(String[] args) {
    	// model 생성
    	MovieList movieList = new MovieList();
    	MovieCart movieCart = new MovieCart();
    	
    	// view 생성
    	ConsoleView view = new ConsoleView();
    	
        TheaterKioskController controller = new TheaterKioskController(movieList, movieCart, view);
        controller.start();
    }
}
