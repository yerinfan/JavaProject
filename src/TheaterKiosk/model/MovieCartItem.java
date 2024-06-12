package TheaterKiosk.model;

import java.util.HashMap;
import java.util.Map;

public class MovieCartItem {
	private Movie movie;
	private Theater theater;
	private int movieCode;
	private int ticketTime;
	private String seatNum;
	Theater mTheater;

	 private Map<Integer, Map<Integer, Theater>> theaterMap = new HashMap<>();
	
	public MovieCartItem(Movie movie, Theater theater, int ticketTime) {
		this.movie = movie;
		this.theater = theater;
		this.ticketTime = ticketTime;
	}

	public MovieCartItem() {
        // 예제: 각 영화코드별로 상영시간에 해당하는 각각의 Theater 인스턴스를 저장하는 예제입니다.
        for (int code = 1; code <= 10; code++) {
            theaterMap.put(code, new HashMap<>());
            for (int time = 1; time <= 7; time++) {
                theaterMap.get(code).put(time, new Theater());
            }
        }
    }
	
	 public boolean isSeatAvailable(int ticketTime, int movieCode, String seatNum) {
	        Theater theater = getTheater(movieCode, ticketTime);
	        if (theater != null) {
	            return theater.isSeatAvailable(seatNum);
	        }
	        return false;
	    }

	public boolean isTimeAvailable(MovieList mMovieList, int movieCode, int ticketTime) {
		mMovieList.getMovieTimes(movieCode);
		return false;
	}

    // Theater 반환 메서드
    public Theater getTheater(int movieCode, int ticketTime) {
        if (theaterMap.containsKey(movieCode) && theaterMap.get(movieCode).containsKey(ticketTime)) {
            return theaterMap.get(movieCode).get(ticketTime);
        }
        return null;
    }
	
	public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public Theater getTheater() {
        return this.theater;
    }
    
    public int getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(int movieCode) {
		this.movieCode = movieCode;
	}

	public int getTicketTime() {
		return ticketTime;
	}

	public void setTicketTime(int ticketTime) {
		this.ticketTime = ticketTime;
	}

	public Theater getmTheater() {
		return mTheater;
	}

	public void setmTheater(Theater mTheater) {
		this.mTheater = mTheater;
	}

	public Movie getMovie() {
		return movie;
	}

	public String getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

}
