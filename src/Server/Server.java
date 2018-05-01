package Server;

import Customer.Customer;
/**
 * 
 * @author Yamil J Gonzalez ID 802153112 Sect 090
 *
 */
public class Server {
	private int sid; // id of server
	private Customer cust; // customer server is currently attending

	public Server (int sid) {
		this.sid = sid;
	}
	public void setCustomer(Customer cust) {
		this.cust = cust;
		cust.setServerid(sid);
	}
	public Customer getCustomer() {
		return cust;
	}
	public int getServerid() {
		return sid;
	}

	public void serve () {
		cust.isServed(1);
	}
	public void serve (int time) {
		if(cust.getRemainingTime() < time)
			cust.isServed(cust.getRemainingTime());
		else
			cust.isServed(time);
	}

}
