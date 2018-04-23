package Customer;

public class Customer {
	private int custid;         // id of this customer
	private int serverid; 	   // id of the line the customer is
	private int arrivalTime;    // arrival time of this customer
	private int remainingTime;  // remaining service time for this customer
	private int attendingTime;
	public Customer (int id, int at, int rt) { 
		custid = id; 
		arrivalTime = at; 
		remainingTime = rt; 
	}
	public int getCustid() {
		return custid;
	}
	public int getServerid() {
		return serverid;
	}
	public void setServerid(int id) {
		serverid = id;
	}
	public int getArrivalTime() {
		return arrivalTime;
	}
	public int getRemainingTime() {
		return remainingTime;
	}
	public void setAttendingTime(int attendingTime) {
		this.attendingTime = attendingTime;
	}
	public int getAttendingTime() {
		return attendingTime;
	}
	/**
	 * Registers an update of serviced received by this customer. 
	 * @param q the time of the service being registered. 
	 */
	public void isServed(int q) { 
		if (q < 0) return;   // only register positive value of q
		remainingTime -= q; 
	}
	public boolean isServiceCompleted() {
		return remainingTime==0;
	}

}