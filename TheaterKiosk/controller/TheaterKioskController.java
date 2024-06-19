package TheaterKiosk.controller;

import java.io.IOException;

import TheaterKiosk.model.Admin;
import TheaterKiosk.model.Cart;
import TheaterKiosk.model.Customer;
import TheaterKiosk.model.FoodStorage;
import TheaterKiosk.model.Movie;
import TheaterKiosk.model.MovieCartItem;
import TheaterKiosk.model.MovieList;
import TheaterKiosk.model.Theater;
import TheaterKiosk.view.ConsoleView;

public class TheaterKioskController {
	MovieList mMovieList;
	ConsoleView view;
	Customer mCustomer;
	Admin mAdmin;
	MovieCartItem mMovieCartItem;
	FoodStorage mFoodStorage;
	Cart mCart;

	String[] menuList = { "0. 종료", "1. 예매 티켓 출력", "2. 티켓 예매하기", "3. 매장 주문하기", "4. 관리자 모드" };

	String[] foodMenuList = { "0. 뒤로가기", "1. 메뉴 정보 보기", "2. 장바구니 보기", "3. 장바구니에 메뉴 추가", "4. 장바구니 메뉴 삭제",
			"5. 장바구니 메뉴 수량 변경", "6. 장바구니 비우기", "7. 주문" };

	String[] adminMenuList = {

			"0. 뒤로가기", "1. 영화 정보 추가", "2. 영화 정보 삭제", "3. 영화 정보 보기", "4. 영화 정보 파일 저장", "5. 식품 정보 추가", "6. 도서 정보 삭제",
			"7. 도서 정보 보기", "8. 도서 정보 파일 저장"

	};

	int menu;

	public TheaterKioskController(MovieList movieList, MovieCartItem movieCartItem,FoodStorage foodStorage, Cart cart, ConsoleView view)
			throws IOException {
		this.view = view;
		mMovieList = movieList;
		mMovieCartItem = movieCartItem;
		mAdmin = new Admin();
		mCustomer = new Customer();
		mFoodStorage = foodStorage;
		mCart = cart;
	}

	public void start() throws Exception {
		welcome();

		do {
			menu = view.selectMenu(menuList);

			switch (menu) {
			case 1 -> ticketIssuance(); // 티켓 발권
			case 2 -> bookingTicket(); // 티켓 예매하기
			case 3 -> orderFood(); // 매장 내 음식 구매
			case 4 -> adminMode();
			case 0 -> end();
			default -> view.displayMessage("잘못된 메뉴 번호입니다.");
			}
		} while (menu != 0);

	}

	private void orderFood() throws IOException {
		registerCustomerInfo();

		int menu;

		do {
			menu = view.selectMenu(foodMenuList);

			switch (menu) {
			case 1 -> viewFoodInfo();
			case 2 -> viewCart();
			case 3 -> addFood2Cart();
			case 4 -> deleteFoodInCart();
			case 5 -> updateFoodInCart();
			case 6 -> resetCart();
			case 7 -> order();
			case 8 -> adminMode();
			default -> view.showMessage("잘못된 메뉴 번호입니다.");
			}
		} while (menu != 0);
	}

	private void order() {
		viewOrderInfo();
		if (view.askConfirm("주문하려면 yes를 입력하세요 : ", "yes") ) {
			// 주문 처리 -> 장바구니 초기화
			mCart.resetCart();
			
		}
	}

	private void viewOrderInfo() {
		view.displayCart(mCart);
		
	}

	private void resetCart() {
		view.displayCart(mCart);

		if (!mCart.isEmpty()) {
			if (view.askConfirm(">> 장바구니를 비우려면 yes를 입력하세요 : ", "yes")) {
				mCart.resetCart();
				view.showMessage(">> 장바구니를 비웠습니다.");
			}
		}
	}

	private void updateFoodInCart() {
		// 장바구니 보여주기
		view.displayCart(mCart);
		if (!mCart.isEmpty()) {
			// 도서 ID 입력 받기
			int foodId = view.selectFoodId(mCart);
			// 수량 입력 받기
			int quantity = view.inputQuantity(0, mFoodStorage.getMaxQuantity());
			// 도서 ID에 해당하는 cartItem 가져와서 cartItem quantity set 수량
			mCart.updateQuantity(foodId, quantity);
		}
	}

	private void deleteFoodInCart() {
		// 장바구니 보여주기
		view.displayCart(mCart);
		if (!mCart.isEmpty()) {
			// 도서 ID 입력 받기
			int foodId = view.selectFoodId(mCart);
			if (view.askConfirm(">> 해당 메뉴를 삭제하려면 yes를 입력하세요 : ", "yes")) {
				// 해당 도서 ID의 cartItem 삭제
				mCart.deleteItem(foodId);
				view.showMessage(">> 해당 메뉴를 삭제했습니다.");
			}
		}
	}

	private void addFood2Cart() {
		view.displayFoodInfo(mFoodStorage);
		int foodId = view.selectFoodfId(mFoodStorage);
		mCart.addItem(mFoodStorage.getFoodById(foodId));
		view.showMessage(">>장바구니에 메뉴를 추가하였습니다.");
	}

	private void viewCart() {
		view.displayCart(mCart);
	}

	private void viewFoodInfo() {
		view.displayFoodInfo(mFoodStorage);
	}

	private void registerCustomerInfo() throws IOException {
		mCustomer = new Customer();
		if (view.askConfirm("폴리텍 회원이신가요? (\"yes\") : ", "yes")) {
			view.inputCustomerInfo(mCustomer);
		}
	}

	// 환영 인사
	private void welcome() {
		view.displayWelcome();
	}

	// 티켓 예약하기
	private void bookingTicket() {
		if (mMovieList.isEmpty()) {
			view.displayMessage("현재 상영 중인 영화가 없습니다.");
			return;
		}

		view.displayMovies(mMovieList);
		int movieCode = view.readNumber("예매하실 영화 번호를 입력하세요. : ");

		if (mMovieList.isValidMovie(movieCode)) { // 영화 존재 유무
			// 상영 시간 출력 및 시간 입력 받기
			view.displayMovieTimes(mMovieList, movieCode);
			int ticketTime = view.readNumber("\n시간을 선택하세요.(숫자만 입력) : ");

			// Add Theater object here
			Theater theater = new Theater(); // 극장을 새로 초기화합니다.
			mMovieCartItem.setTheater(theater); // movieCartItem에 theater를 설정합니다.

			if (isTimeAvailable(movieCode, ticketTime)) {
				view.showTheaterChair(mMovieCartItem, movieCode, ticketTime);
				String seatNum;

				do {
					seatNum = view.inputString("좌석 번호를 입력하세요.(알파벳 숫자 띄어쓰기없이) : ");
				} while (!mMovieCartItem.isSeatAvailable(ticketTime, movieCode, seatNum));

				reserveTicket(ticketTime, movieCode, seatNum);

				// Movie 객체를 설정합니다.
				Movie movie = mMovieList.getMovieByCode(movieCode);
				mMovieCartItem.setMovie(movie);

				view.bookedTicket(mMovieCartItem);

				// 종료
				menu = 0;
				end();

			} else {
				view.displayMessage("현재 상영 중인 시간이 아닙니다.");
			}

		} else {
			view.displayMessage("잘못된 영화 번호입니다.");
		}

	}

	private void reserveTicket(int ticketTime, int movieCode, String seatNum) {
		mMovieCartItem.setTicketTime(ticketTime);
		mMovieCartItem.setMovieCode(movieCode);
		mMovieCartItem.setSeatNum(seatNum);
	}

	public boolean isTimeAvailable(int movieCode, int ticketTime) {
		if (movieCode % 2 == 0) {
			if (ticketTime == 2 || ticketTime == 4 || ticketTime == 6) {
				return true;
			}
		} else {
			if (ticketTime == 1 || ticketTime == 3 || ticketTime == 5) {
				return true;
			}
		}
		return false;
	}

	// 예매 티켓 출력
	private void ticketIssuance() throws Exception {
		// 예매 번호를 입력 받는다.
		int TicketNum = view.readNumber("예매 번호를 입력하세요. (전화번호 뒷 4자리) : ");

		// 번호 여부 확인
		if (checkTicket(TicketNum)) {
			view.bookedTicket(mCustomer, TicketNum); // 있을 경우 영화티켓(영화명, 시간, 관, 좌석번호 출력)

			// 종료
			end();
		} else {
			view.displayMessage("없는 번호입니다.");
		}

	}

	private boolean checkTicket(int ticketNum) throws Exception {
		for (Customer customer : mCustomer.Customers) {
			if (customer.getTicketId() == ticketNum) {
				return true;
			}
		}
		return false;
	}

	// 관리자 모드
	private void adminMode() {

		if (!authenticateAdmin()) {
			view.showMessage("관리자 모드로 전환할 수 없습니다.");
			return;
		}

		// 관리자 모드 진입 -> 영화 추가, 영화 삭제, 영화 정보 파일 저장
		// 관리자 모드일 때의 메뉴 출력
		// 메뉴 선택하면 해당 기능 실행
		int menu;
		do {
			menu = view.selectMenu(adminMenuList);

			switch (menu) {
			case 1 -> addMovie2List();
			case 2 -> deleteMovieInList();
			case 3 -> view.displayMovies(mMovieList);
			case 4 -> saveMovieList2File();
			case 5 -> addFood2Storage();
			case 6 -> deleteFoodInStorage();
			case 7 -> viewFoodInfo();
			case 8 -> saveFoodList2File();
			case 0 -> adminEnd();
			default -> view.showMessage("잘못된 메뉴 번호입니다.");
			}
		} while (menu != 0);
	}

	private void saveFoodList2File() {
		if (mFoodStorage.isSaved()) {
			view.showMessage("메뉴 정보가 파일과 동일합니다.");
		} else {
			mFoodStorage.saveFoodList2File();
			view.showMessage("메뉴 정보를 저장하였습니다.");
		}
	}

	// 관리자 인증 (id, password 확인)
	private boolean authenticateAdmin() {
		view.showMessage("관리자 모드 진입을 위한 관리자 인증");
		String id = view.inputString("관리자 ID : ");
		String password = view.inputString("관리자 password : ");
		return mAdmin.login(id, password);
	}
	
	private void addFood2Storage() {
		view.showMessage("새로운 음식을 추가합니다.");
		
		mFoodStorage.addFood(view.inputString("메뉴 이름 : "),
				view.readNumber("가격 : "));

	}

	private void deleteFoodInStorage() {
		if (mFoodStorage.isEmpty()) {
			view.showMessage("음식 창고에 음식이 없습니다.");
			return;
		}
	
		viewFoodInfo();
	
		int foodId = view.selectFoodId(mFoodStorage);
		if (view.askConfirm(">> 해당 메뉴를 삭제하려면 yes를 입력하세요 : ", "yes")) {
			
			mFoodStorage.deleteItem(foodId);
			view.showMessage(">> 해당 메뉴를 삭제했습니다.");
		}

	}
	
	
	// MovieList에 도서 추가
	private void addMovie2List() {
		view.showMessage("새로운 영화를 추가합니다.");

		// 영화 정보로 Movie 인스턴스 만들어서 mMovieList에 추가
		mMovieList.addMovie(view.inputString("영화 제목 : "), view.readNumber("가격 : "));

	}

	// MovieList에서 도서 삭제
	private void deleteMovieInList() {
		if (mMovieList.isEmpty()) {
			view.showMessage("현재 영화 목록이 비었습니다.");
			return;
		}
		// 영화 목록 보여주기
		view.displayMovies(mMovieList);
		// 영화 코드 입력 받기
		int movieCode = view.selectMovie(mMovieList);
		if (view.askConfirm(">> 해당 영화를 삭제하려면 yes를 입력하세요 : ", "yes")) {
			// 해당 영화 삭제
			mMovieList.deleteItem(movieCode);
			view.showMessage(">> 해당 영화를 삭제했습니다.");
		}

	}

	// 영화 정보를 파일에 저장
	private void saveMovieList2File() {
		if (mMovieList.isSaved()) {
			view.showMessage("영화 정보가 파일과 동일합니다.");
		} else {
			mMovieList.saveMovieList2File();
			view.showMessage("영화 정보를 저장하였습니다.");
		}
	}

	// 관리자 모드 종료 : 책 정보 수정 후 파일에 반영되지 않은 경우, 저장 여부 확인
	private void adminEnd() {
		if (!mMovieList.isSaved()) {
			view.showMessage("수정한 영화 정보가 파일로 저장되지 않았습니다.");
			if (view.askConfirm("영화 정보를 저장하려면 yes를 입력하세요 : ", "yes")) {
				mMovieList.saveMovieList2File();
			}
		}
		view.showMessage("관리자 모드가 종료되었습니다.\n");
	}

	// 종료
	private void end() {
		view.showMessage(">> Poly Theater Kiosk을 종료합니다.");
	}

}
