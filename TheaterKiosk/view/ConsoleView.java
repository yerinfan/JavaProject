package TheaterKiosk.view;

import java.util.Scanner;

import TheaterKiosk.model.Cart;
import TheaterKiosk.model.Customer;
import TheaterKiosk.model.FoodStorage;
import TheaterKiosk.model.MovieCartItem;
import TheaterKiosk.model.MovieList;
import TheaterKiosk.model.Theater;

public class ConsoleView {

	public void displayMessage(String message) {
		System.out.println(message);
	}

	public void displayMovies(MovieList mMovieList) {
		for (int i = 0; i < mMovieList.getNumMovies(); i++) {
			String movieInfo = mMovieList.getMovieInfo(i);
			System.out.println(movieInfo);
		}
	}

	public void displayMovieTimes(MovieList mMovieList, int movieCode) {
		System.out.print(mMovieList.getMovieTimes(movieCode));
	}

	public void displayWelcome() {
		String welcome = "*****************************************\n" + "*     Welcome to Poly Theater Kiosk     *\n"
				+ "*****************************************";
		System.out.println(welcome);
	}

	public int selectMenu(String[] menuList) {

		displayMessage(menuList);

		int menu;
		do {
			menu = readNumber(">> 메뉴 선택 : ");
			if (menu < 0 || menu >= menuList.length)
				System.out.println("0부터 " + (menuList.length - 1) + "까지의 숫자를 입력하세요.");
		} while (menu < 0 || menu >= menuList.length);
		return menu;
	}

	private void displayMessage(String[] menuList) {
		System.out.println("=========================================");
		for (int i = 1; i < menuList.length; i++) {
			System.out.println(menuList[i]);
		}
		System.out.println(menuList[0]);
		System.out.println("=========================================");
	}

	public void showTheaterChair(MovieCartItem movieCartItem, int movieCode, int ticketTime) {
		Theater theater = movieCartItem.getTheater(movieCode, ticketTime);
		if (theater == null) {
			System.out.println("해당 영화 코드와 시간에 대한 극장 정보가 없습니다.");
			return;
		}

		boolean[][] seatAvailability = theater.getSeatAvailability();
		char[] rowLabels = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };

		System.out.print("  ");
		for (int k = 1; k <= seatAvailability[0].length; k++) {
			System.out.print(k + " ");
		}
		System.out.println();

		for (int i = 0; i < seatAvailability.length; i++) {
			System.out.print(rowLabels[i] + " ");
			for (int j = 0; j < seatAvailability[i].length; j++) {
				System.out.print(seatAvailability[i][j] ? "O " : "X ");
			}
			System.out.println();
		}
	}

	public void bookedTicket(MovieCartItem mMovieCartItem) {
		System.out.println("======================");
		System.out.println("========예약정보========");
		System.out.println("==========1관==========");
		System.out.println("[영화명 = " + mMovieCartItem.getMovie().getMovieName() + "]");
		System.out.println("[상영 시간 = " + mMovieCartItem.getTicketTime() + " PM]");
		System.out.println("[좌석 번호 = " + mMovieCartItem.getSeatNum() + "]");
		System.out.println("======================");
	}

	public void bookedTicket(Customer mCustomer, int ticketNum) {
		System.out.println("======================");
		System.out.println("=======" + ticketNum + "님!=======");
		System.out.println("==========1관==========");
		System.out.println("[영화명 = 집이 최고야(더빙)]");
		System.out.println("===[상영 시간 = 8 PM]===");
		System.out.println("====[좌석 번호 = F6]====");
		System.out.println("======================");

	}

	// 입력된 문자열을 입력했을 때만 true를 반환하는 confirm용
	public boolean askConfirm(String message, String yes) {

		System.out.print(message);

		Scanner input = new Scanner(System.in);
		if (input.nextLine().equals(yes))
			return true;
		return false;

	}

	// 숫자 입력 받기 (숫자가 아닌 문자를 넣으면 예외 처리하고 다시 입력받기)
	public int readNumber(String message) {
		if (message != null || !message.equals(""))
			System.out.print(message);

		Scanner input = new Scanner(System.in);
		try {
			int number = input.nextInt();
			return number;
		} catch (Exception e) {
			System.out.print("숫자를 입력하세요 :");
			return readNumber(message);
		}
	}

	// 입력된 문자열 출력
	public void showMessage(String message) {
		System.out.println(message);

	}

	// 문자열 입력 받기
	public String inputString(String msg) {
		Scanner in = new Scanner(System.in);
		System.out.print(msg);
		return in.nextLine();
	}

	// MovieList에 있는 영화코드 선택하기
	public int selectMovie(MovieList mMovieList) {

		int movieCode;
		boolean result;
		do {
			movieCode = readNumber("영화의 코드를 입력하세요 : ");
			result = mMovieList.isValidMovie(movieCode);
			if (!result)
				System.out.print("잘못된 영화 코드입니다.");
		} while (!result);

		return movieCode;
	}

	public void inputCustomerInfo(Customer mCustomer) {
		Scanner input = new Scanner(System.in);

		System.out.print(">> 이름 : ");
		mCustomer.setName(input.nextLine());
		System.out.print(">> 전화번호 : ");
		mCustomer.setPhone(input.nextLine());

	}

	public void displayFoodInfo(FoodStorage mFoodStorage) {
		for (int i = 0; i < mFoodStorage.getNumFoods(); i++) {
			String fookInfo = mFoodStorage.getFoodInfo(i);
			System.out.println(fookInfo);
		}

	}

	public void displayCart(Cart cart) {
		if (cart.isEmpty()) {
			System.out.println(">> 장바구니가 비어 있습니다.");
			return;
		}

		for (int i = 0; i < cart.getNumItems(); i++) {
			System.out.println(cart.getItemInfo(i));
		}
		System.out.println("총 금액 : " + cart.getTotalPrice() + "원");

	}

	public int selectFoodfId(FoodStorage mFoodStorage) {
		int foodId;
		boolean result;
		do {
			foodId = readNumber("추가할 메뉴의 ID를 입력하세요 : ");
			result = mFoodStorage.isValidFood(foodId);
			if (!result)
				System.out.print("잘못된 메뉴의 ID입니다.");
		} while (!result);

		return foodId;
	}

	public int selectFoodId(Cart mCart) {
		int foodId;
		boolean result;
		do {
			foodId = readNumber("메뉴의 ID를 입력하세요 : ");
			result = mCart.isValidItem(foodId);
			if (!result)
				System.out.print("잘못된 메뉴의 ID입니다.");
		} while (!result);

		return foodId;
	}

	public int inputQuantity(int min, int max) {
		int number;
		do {
			number = readNumber(">> 수량 입력 (" + min + " ~ " + max + "): ");
			if (number < min || number > max)
				System.out.println("잘못된 수량입니다.");
		} while (number < min || number > max);

		return number;
	}

	public int selectFoodId(FoodStorage mFoodStorage) {
		int foodId;
		boolean result;
		do {
			foodId = readNumber("메뉴의 ID를 입력하세요 : ");
			result = mFoodStorage.isValidItem(foodId);
			if (!result)
				System.out.print("잘못된 메뉴의 ID입니다.");
		} while (!result);

		return foodId;
	}
}
