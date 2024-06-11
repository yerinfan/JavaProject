package TheaterKiosk.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MovieList {
	ArrayList<Movie> movies = new ArrayList<>();
	final int MAX_QUANTITY = 10;
	private String movieFilename = "movielist.txt";
	private int lastId;
	private boolean isSaved;

	public MovieList() throws IOException {
		loadMovieListFromFile();
		generateLastId();
		isSaved = true;
	}

	public boolean isSaved() {
		return isSaved;
	}
	
	private void generateLastId() {
		lastId = 0;
		for (Movie movie : movies) {
			int id = movie.getMovieCode();
			if (id > lastId)
				lastId = id;
		}
	}

	private void loadMovieListFromFile() throws IOException {
		FileReader fr;
		try {
			fr = new FileReader(movieFilename);
			BufferedReader br = new BufferedReader(fr);
			String idStr;
			while ((idStr = br.readLine()) != null && !idStr.equals("")) {
				int code = Integer.parseInt(idStr);
				String movieName = br.readLine();
				int price = Integer.parseInt(br.readLine());
				movies.add(new Movie(code, movieName, price));
			}
			fr.close();
			br.close();

		} catch (FileNotFoundException | NumberFormatException e) {
			System.out.println(e.getMessage());
		}
	}

	public int getNumMovies() {
		return movies.size();
	}
	
	public String getMovieInfo(int i) {
		return movies.get(i).toString();
	}
	
	public boolean isValidMovie(int movieCode) {
		for (Movie movie : movies) {
			if (movie.getMovieCode() == movieCode) return true;
		}
		return false;
	}

	public Movie getMovieByCode(int movieCode) {
		for (Movie movie : movies) {
			if (movie.getMovieCode() == movieCode)
				return movie;
		}
		return null;
	}

	public int getMaxQuantity() {
		return MAX_QUANTITY;
	}
	
	public boolean isEmpty() {
		return movies.size() == 0;
	}

	public void deleteItem(int movieCode) {
		movies.remove(getMovieByCode(movieCode));
		isSaved = false;
	}
	
	public String getMovieTimes(int movieCode) {
		// For simplicity, returning hardcoded values
		switch (movieCode) {
		case 1:
			return "1 PM, 3 PM, 5 PM";
		case 2:
			return "2 PM, 4 PM, 6 PM";
		case 3:
			return "1 PM, 3 PM, 5 PM";
		case 4:
			return "2 PM, 4 PM, 6 PM";
		case 5:
			return "1 PM, 3 PM, 5 PM";
		case 6:
			return "2 PM, 4 PM, 6 PM";
		case 7:
			return "1 PM, 3 PM, 5 PM";
		case 8:
			return "2 PM, 4 PM, 6 PM";
		case 9:
			return "1 PM, 3 PM, 5 PM";
		case 10:
			return "2 PM, 4 PM, 6 PM";
		default:
			return "No times available";
		}
	}

	public void addBook(int movieCode, String movieName, int price) {

		Movie movie = new Movie(++lastId, movieName, price);
		movies.add(movie);
		isSaved = false;
	}
	
	public void saveMovieList2File() {

		try {
			FileWriter fw = new FileWriter(movieFilename);
			for (Movie movie : movies) {
				fw.write(movie.getMovieCode() + "\n");
				fw.write(movie.getMovieName() + "\n");
				fw.write(movie.getPrice() + "\n");
			}
			fw.close();
			isSaved = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isTimeAvailable(int movieCode, int ticketTime) {
		if (movieCode % 2 == 0) {
			if (ticketTime == 2 || ticketTime == 4 || ticketTime == 6) {
				return true;
			}
		}else {
			if (ticketTime == 1 || ticketTime == 3 || ticketTime == 7) {
				return true;
			}
		}
		return false;
	}
}
