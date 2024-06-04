package TheaterKiosk.controller;

import TheaterKiosk.model.MovieCart;
import TheaterKiosk.model.MovieList;
import TheaterKiosk.view.ConsoleView;

public class TheaterKioskController {

	ConsoleView view;
	MovieList nMovieList;
	MovieCart mMovieCart;

	String[] menuList = {"0. 종료", "1. 예매 티켓 출력하기", "2. 영화 예매하기"};
	
	String[] reserveMenuList = {"0. 뒤로가기", "1. 장바구니 보기", "2. 장바구니 담기", "3. 장바구니에 음식 삭제", "4. 장바구니 비우기", "5. 결제하기"};
	
	public TheaterKioskController(MovieList movieList, MovieCart movieCart, ConsoleView view2) {
		// TODO Auto-generated constructor stub
	}

	public void start() {
		// TODO Auto-generated method stub
		
	}

}
