package TheaterKiosk.model;

import java.util.List;

public class MovieCart {
    private String ticketNo;
    private List<MovieCartItem> items;

    public MovieCart(String ticketNo, List<MovieCartItem> items) {
        this.ticketNo = ticketNo;
        this.items = items;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public List<MovieCartItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ticket No: ").append(ticketNo).append("\n");
        for (MovieCartItem item : items) {
            sb.append(item).append("\n");
        }
        return sb.toString();
    }
}
