package LineSystem;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;

import Customer.Customer;
import Server.Server;

public class SLMS implements Line{
	public String process(Queue<Customer> input, int numServers) {
		Queue<Customer> inputQueue = input;
		Deque<Customer> line = new ArrayDeque<>();
		int attendingCustomers = 0 ; //number of customers being attended
		ArrayList<Customer> attendedCustomers = new ArrayList<Customer>();	 //list of customers that servers finished attending
		ArrayList<Server> busyServers = new ArrayList<Server>();	
		ArrayList<Server> emptyServers = new ArrayList<Server>();			 //list of servers with no customers
		int time = 0;
		//initializes every server with an id from 1 to n
		for(int i = 0 ; i< numServers ; i++) {
			emptyServers.add(new Server(i+1));
		}
			
		
		while(!inputQueue.isEmpty()||!line.isEmpty()||!(attendingCustomers ==0)){
			
			//Checks if a new customer arrives to the line and adds it
			while(!inputQueue.isEmpty() && inputQueue.peek().getArrivalTime()==time)
				line.add(inputQueue.remove());
			
			//if there is a server available, assigns a new customer to it
			while (attendingCustomers < numServers && !line.isEmpty()) {
				Customer nc = line.remove();
				Server s = emptyServers.get(0);
				s.setCustomer(nc);
				nc.setAttendingTime(time);
				emptyServers.remove(s);
				busyServers.add(s);
				attendingCustomers++;
			}
			if(!(attendingCustomers==0)){
				Iterator<Server> iter = busyServers.iterator();
				//Give service to every customer being attended
				while (iter.hasNext()) {
					Server s = iter.next();
					s.serve();
					Customer actualCustomer = s.getCustomer();
					//Checks if server finished with customer 
					if(actualCustomer.isServiceCompleted()) { 
						attendedCustomers.add(actualCustomer);  
						attendingCustomers--;
						iter.remove();
						emptyServers.add(s); 
					}
				}
			}
			time++;
		}
		//compute final statistics
		return "SLMS " + numServers + ": " + time +" "+averageTime(attendedCustomers) + " 0";
	}
	/**
	 * Calculates the average time the customer waits to finish being attended 
	 * since arrives to the line
	 * @param attendedCustomers
	 * @return average time 
	 */
	protected static double averageTime(ArrayList<Customer>  attendedCustomers){
		double avg = 0;
		for(Customer j : attendedCustomers){
			avg += j.getAttendingTime()-j.getArrivalTime();
		}
		avg = avg/attendedCustomers.size();
		return  Math.round(avg * 100.0) / 100.0;
	}
}
