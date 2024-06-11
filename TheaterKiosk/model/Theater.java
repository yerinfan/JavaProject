package TheaterKiosk.model;

public class Theater {
	private int seatRow;
	private int seatCol;
	private final int numRows = 8;
	private final int numColumns = 10;
	private boolean[][] seatAvailability;

	public Theater(int seatRow, int seatCol) {
		this.seatRow = seatRow;
		this.seatCol = seatCol;
		this.seatAvailability = new boolean[seatRow][seatCol];
		// Initialize seat availability, for example, all seats are available initially
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				seatAvailability[i][j] = true;
			}
		}
	}

	 private int getRowIndex(char row) {
	        return row - 'a'; // Calculate the row index based on the alphabet character
	    }
	
	// Method to set seat availability at a specific row and column
		public boolean setSeatAvailability(int row, int col, boolean available) {
			return seatAvailability[row][col] = available;
		}
	
	// Method to check seat availability at a specific row and column
		 public boolean isSeatAvailable(String seat) {
		        if (seat.length() != 2) {
		            System.out.println("Invalid seat format. Please enter a seat in the format 'a1' to 'h10'.");
		            return false;
		        }

		        char rowChar = Character.toLowerCase(seat.charAt(0));
		        int col = Character.getNumericValue(seat.charAt(1)) - 1; // Adjust column index to start from 0

		        if (rowChar < 'a' || rowChar > 'h' || col < 0 || col >= numColumns) {
		            System.out.println("올바른 좌석 번호를 입력하세요.");
		            return false;
		        }

		        int row = getRowIndex(rowChar);

		        return seatAvailability[row][col];
		    }

	public int getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}

	public int getSeatCol() {
		return seatCol;
	}

	public void setSeatCol(int seatCol) {
		this.seatCol = seatCol;
	}

	public boolean[][] getSeatAvailability() {
		return seatAvailability;
	}

	public void setSeatAvailability(boolean[][] seatAvailability) {
		this.seatAvailability = seatAvailability;
	}

	
	
	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

}
