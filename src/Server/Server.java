package Server;

import Customer.Customer;

public class Server {
private int sid; // id of server
private Customer cust; // customer server is currently attending

public Server (int sid) {
	this.sid = sid;
}
public void serve(Customer cust) {
	cust.isServed(1);
}
public int getServerid() {
	return sid;
}

}
