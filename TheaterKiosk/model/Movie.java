package TheaterKiosk.model;

public class Movie {
	private int movieCode;
	private String movieName;
    private int price;
    private static int counter = 0;
    
	public Movie(String movieName, int price) {
		this.movieCode = ++counter;
		this.movieName = movieName;
		this.price = price;
	}

	public int getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(int movieCode) {
		this.movieCode = movieCode;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "[" + movieCode + ". 영화명 = " + movieName
				+ ", 가격 = " + price + "원]";
	}
	
	
  
}
