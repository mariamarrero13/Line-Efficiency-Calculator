package Lines;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import Customer.Customer;

public class SLMS {
	private int numServers;
	private ArrayList<Deque<Customer>> inputArray;
	public SLMS (int numServers , ArrayList<Deque<Customer>> inputArray) {
		this.numServers = numServers;
		this.inputArray = inputArray;
	}
	
	
	public void serve(Deque<Customer> input) {
		Deque<Customer> inputQueue = input;
		Deque<Customer> attendingCustomers = new ArrayDeque<Customer>();
		ArrayList<Customer> attendedCustomers = new ArrayList<Customer>();
		int time = 0;

		while(!inputQueue.isEmpty()||!attendingCustomers.isEmpty()){
			if(!attendingCustomers.isEmpty()){
				Customer actualCustomer= attendingCustomers.peekFirst();
				actualCustomer.isServed(1);
				if(actualCustomer.getRemainingTime()==0){
					actualCustomer.setDepartureTime(time);
					attendedCustomers.add(attendingCustomers.removeFirst());
				}
				else
					attendingCustomers.addLast(attendingCustomers.removeFirst());
			}
			if(!inputQueue.isEmpty()&& inputQueue.peekFirst().getArrivalTime()==time){
				attendingCustomers.addLast(inputQueue.removeFirst());
			}
			time++;
		}
	}
}
