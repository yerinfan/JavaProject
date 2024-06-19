package TheaterKiosk.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Customer extends User{
	public ArrayList<Customer> Customers = new ArrayList<>();
	private int ticketId;
    private int lastId;
    private String name;
    private boolean isSaved = false;
    private String CustomerFileName = "Customer.txt";
    private String address;
	private String email;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
    
    public int getTicketId() {
        return ticketId;
    }

	public String getName() {
        return name;
    }

	public Customer() throws IOException {
		loadBookedTicketFromFile();
		isSaved = true;
	}
	
	public Customer(String userName, int ticketId) {
		name = userName;
		this.ticketId = ticketId;
	}

	private void loadBookedTicketFromFile() {
		FileReader fr;
		try {
			fr = new FileReader(CustomerFileName);
			BufferedReader br = new BufferedReader(fr);
			String nameStr;
			while ((nameStr = br.readLine()) != null && !nameStr.equals("")) {
				String userName = nameStr;
				int TicketId = Integer.parseInt(br.readLine());
				Customers.add(new Customer(userName, TicketId));
			}
			fr.close();
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
