package TheaterKiosk.model;

public class MovieCartItem {
    private Movie movie;
    private int movieCode;
    private int time;
    private String seatNum;

    public MovieCartItem(Movie movie,int movieCode, int time, String seatNum) {
        this.movie = movie;
        this.movieCode = movieCode;
        this.time = time;
        this.seatNum = seatNum;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getTime() {
        return time;
    }

 
    public int getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(int movieCode) {
		this.movieCode = movieCode;
	}

	@Override
    public String toString() {
        return "Movie: " + movie.getMovieName() + ", Time: " + time + ", Seat: " + seatNum;
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

	public void setTime(int time) {
		this.time = time;
	}
}
