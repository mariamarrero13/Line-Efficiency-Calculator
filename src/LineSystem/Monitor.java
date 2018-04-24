package LineSystem;

import java.util.ArrayList;
import java.util.Deque;
import Customer.Customer;

public class Monitor {
	
	public static void checkForCustBalance (ArrayList<Deque<Customer>> lines) {
		ArrayList<Integer> sizes = new ArrayList<>();
		double numberOfCustomers = 0;
		double numberOfLines = lines.size();
		for(Deque<Customer> dq : lines) {
			sizes.add(dq.size());
			numberOfCustomers = numberOfCustomers+ dq.size();
		}
		
		double customersPerLine = numberOfCustomers/numberOfLines; // average number of customers per line to be balanced
		if( customersPerLine % 1 == 0) {	// is integer 
			for(Integer size : sizes) {
				
			}
		}
	}
	
	public static void checkForTimeBalance (ArrayList<Deque<Customer>> lines) {
	}
	
	private static void balanceCust(ArrayList<Deque<Customer>> lines, double x) {
		
	}
	
	private static void balanceTime(ArrayList<Deque<Customer>> lines) {
		
	}
	
	
}
