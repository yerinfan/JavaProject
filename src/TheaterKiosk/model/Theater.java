package TheaterKiosk.model;

public class Theater {
	private int seatRow;
	private int seatCol;
	private final int numRows = 8;
	private final int numCols = 10;
	private boolean[][] seatAvailability;

    public Theater() {
        seatAvailability = new boolean[numRows][numCols];
        // Initialize all seats as available
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
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
//		 public boolean isSeatAvailable(int String seat) {
//		        if (seat.length() != 2) {
//		            System.out.println("Invalid seat format. Please enter a seat in the format 'a1' to 'h10'.");
//		            return false;
//		        }
//
//		        char rowChar = Character.toLowerCase(seat.charAt(0));
//		        int col = Character.getNumericValue(seat.charAt(1)) - 1; // Adjust column index to start from 0
//
//		        if (rowChar < 'a' || rowChar > 'h' || col < 0 || col >= numCols) {
//		            System.out.println("올바른 좌석 번호를 입력하세요.");
//		            return false;
//		        }
//
//		        int row = getRowIndex(rowChar);
//
//		        return seatAvailability[row][col];
//		    }
		
		 public boolean isSeatAvailable(String seatId) {
		        char rowChar = Character.toLowerCase(seatId.charAt(0));
		        int row = rowChar - 'a'; // Convert row character to index
		        int col = Integer.parseInt(seatId.substring(1)) - 1; // Convert column number to index

		        if (row >= 0 && row < numRows && col >= 0 && col < numCols) {
		            return seatAvailability[row][col];
		        }
		        return false; // Return false if seatId is out of bounds
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

	public boolean getSeatAvailability(int i, int j) {
		return seatAvailability[i][j];
	}

	public void setSeatAvailability(boolean[][] seatAvailability) {
		this.seatAvailability = seatAvailability;
	}

	
	
	public int getNumRows() {
		return numRows;
	}

	public int getNumCols() {
		return numCols;
	}

	public boolean[][] getSeatAvailability() {
	    return seatAvailability;
	}


}
