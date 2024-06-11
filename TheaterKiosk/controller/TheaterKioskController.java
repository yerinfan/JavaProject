package TheaterKiosk.controller;

import java.io.BufferedReader;
import java.io.FileReader;

import TheaterKiosk.model.Admin;
import TheaterKiosk.model.Customer;
import TheaterKiosk.model.MovieCart;
import TheaterKiosk.model.MovieList;
import TheaterKiosk.model.Theater;
import TheaterKiosk.view.ConsoleView;

public class TheaterKioskController {
		MovieList mMovieList;
		ConsoleView view;
		MovieCart mMovieCart;
		Customer mCustomer;
		Admin mAdmin;
		Theater mTheater;

		
		
    String[] menuList = {
			"0. 종료",
			"1. 예매 티켓 출력",
			"2. 티켓 예매하기",
	};
    
    String[] adminMenuList = {

			"0. 종료",
			"1. 영화 정보 추가",
			"2. 영화 정보 삭제",
			"3. 영화 정보 보기",
			"4. 영화 정보 파일 저장"

	};

    public TheaterKioskController(MovieList movieList, MovieCart movieCart, ConsoleView view) {
		this.view = view;
		mMovieList = movieList;
		mMovieCart = movieCart;
		mAdmin = new Admin();
		
	}

	public void start() throws Exception {
		welcome();
		
        int menu;

        do {
            menu = view.selectMenu(menuList);

            switch (menu) {
                case 1 -> ticketIssuance(); // 티켓 발권
//                    view.displayMessage("Enter Ticket Number:");
//                    String ticketNo = scanner.next();
//                    MovieCart ticket = movieList.checkTicket(ticketNo);
//                    if (ticket != null) {
//                        view.displayTicketInfo(ticket);
//                    } else {
//                        view.displayMessage("Ticket not found. Please try again.");
//                    }
//                    break;
                case 2 -> bookingTicket(); // 티켓 예매하기
//                    view.displayMovies(movieList.getMovies());
//                    view.displayMessage("Enter Movie Code:");
//                    String movieCode = scanner.next();
//                    view.displayMovieTimes(movieList.getMovieTimes(movieCode));
//                    int movieTime = scanner.nextInt();
//                    
//                    break;
                case 0 -> end();
                default -> view.displayMessage("잘못된 메뉴 번호입니다.");
            }
        } while (menu != 0);

    }


	private void bookingTicket() {
		view.displayMovies(mMovieList);
		int movieCode = view.readNumber("예매하실 영화 번호를 입력하세요. : ");
		mMovieCartItem.setMovieCode(movieCode);
		
		if (mMovieList.isValidMovie(movieCode)) { // 영화 존재 유무
			// 상영 시간 출력 및 시간 입력 받기
			view.displayMovieTimes(mMovieList, movieCode);
			int ticketTime = view.readNumber("시간을 선택하세요.(숫자만 입력) : ");
			
			if (mMovieList.isTimeAvailable(movieCode, ticketTime)) {
				view.showTheaterChair(mTheater);
				do {
					String seatNum = view.inputString("좌석 번호를 입력하세요.(알파벳 숫자 띄어쓰기없이) : ");
					} while(mTheater.isSeatAvailable(ticketTime, movieCode, seatNum));
				
			}else {
				view.showMessage("상영 중이지 않습니다.");
			}
			
		}else {
			view.showMessage("잘못된 번호입니다.");
		}
		
	}

	// 종료
		private void end() {
			view.showMessage(">> Poly Theater Kiosk을 종료합니다.");
		}

	private void ticketIssuance() throws Exception {
		// 예매 번호를 입력 받는다.
		int TicketNum= view.readNumber("예매 번호를 입력하세요.");
		
		// 번호 여부 확인
		if(checkTicket(TicketNum)) {
			view.bookedTicket(tickets, TicketNum); // 있을 경우 영화티켓(영화명, 시간, 관, 좌석번호 출력)
		}else {
			view.displayMessage("없는 번호입니다.");
		}
		
	}


	private boolean checkTicket(int ticketNum) throws Exception {
		FileReader fileReader = new FileReader("Customer.txt");
		BufferedReader br = new BufferedReader(fileReader);
		String line;
		
		while ((line = br.readLine()) != null) {
            int storedTicketNo = Integer.parseInt(line.trim());
            if (ticketNum == storedTicketNo) {
                return true;
            }
        }
		
		return false;
	}

	private void welcome() {
			view.displayWelcome();
	}
}
