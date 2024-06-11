package TheaterKiosk.view;

import java.util.Scanner;
import java.util.StringTokenizer;

import TheaterKiosk.model.MovieCart;
import TheaterKiosk.model.MovieList;
import TheaterKiosk.model.Theater;
import TheaterKiosk.model.Tickets;

public class ConsoleView {

	public void displayMessage(String message) {
		System.out.println(message);
	}

	public void displayTicketInfo(MovieCart ticket) {
		System.out.println("Ticket Information:");
		System.out.println(ticket);
	}

	public void displayMovies(MovieList mMovieList) {
		for (int i = 0; i < mMovieList.getNumMovies(); i++) {
			String movieInfo = mMovieList.getMovieInfo(i);
			System.out.println(movieInfo);
		}
	}

	public void displayMovieTimes(MovieList mMovieList, int movieTimes) {
		StringTokenizer st = new StringTokenizer(mMovieList.getMovieTimes(movieTimes) , ", ");
		for (int i = 0; i < 3; i++) {
			System.out.print(st.nextToken() + " ");
		}
	}

	public void displayWelcome() {
		String welcome = "*****************************************\n" + "*    Welcome to Poly Theater Kiosk    *\n"
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

	private void displayMessage(String[] menuList) {
		System.out.println("=========================================");
		for (int i = 1; i < menuList.length; i++) {
			System.out.println(menuList[i]);
		}
		System.out.println(menuList[0]);
		System.out.println("=========================================");
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

		public void bookedTicket(Tickets tickets, int ticketNum) {
			
			System.out.println(tickets.getTicket(ticketNum));
		}
		
		 public void showTheaterChair(Theater theater) {
		        int numRows = theater.getNumRows();
		        int numColumns = theater.getNumColumns();

		        for (int i = 0; i < numRows; i++) {
		            for (int j = 0; j < numColumns; j++) {
		                if (theater.isSeatAvailable(i, j)) {
		                    System.out.print("O "); // Available seat
		                } else {
		                    System.out.print("X "); // Occupied seat
		                }
		            }
		            System.out.println(); // Move to the next row
		        }
		    }
}
