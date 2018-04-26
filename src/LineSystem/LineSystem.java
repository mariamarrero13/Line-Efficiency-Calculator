package LineSystem;

import java.util.ArrayList;
import java.util.Queue;

import Customer.Customer;

public interface LineSystem {
	
	String process(Queue<Customer> input, int numServers);
	/*
	 * 
	 */
}
