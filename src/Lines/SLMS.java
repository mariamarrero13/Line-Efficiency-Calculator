package Lines;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;

import Customer.Customer;
import Server.Server;

public class SLMS {
	private int numServers;  //number of Servers in the system
	private ArrayList<Queue<Customer>> inputArray; // Array of Files 
	private String name = "SLMS";

	public String process(Queue<Customer> input, int numServers) {
		Server[] servers = new Server[numServers];
		Queue<Customer> inputQueue = input;
		Deque<Customer> line = new ArrayDeque<>();
		int attendingCustomers = 0 ; //number of customers being attended
		ArrayList<Customer> attendedCustomers = new ArrayList<Customer>();	 //list of customers that servers finished attending
		ArrayList<Server> emptyServers = new ArrayList<Server>();			 //list of servers with no customers
		int time = 0;
		//initializes every server with an id from 1 to n
		for(int i = 0 ; i< numServers ; i++)
			servers[i] = new Server(i+1);
		while(!inputQueue.isEmpty()||!line.isEmpty()||!(attendingCustomers ==0)){
			if(!(attendingCustomers==0)){
				//if there is a server available, assigns a new customer to it
				while (attendingCustomers< numServers) {
					Customer nc = line.getFirst();
					Server s = emptyServers.get(0);
					s.setCustomer(nc);
					nc.setAttendingTime(time);
					emptyServers.remove(s);
					attendingCustomers++;
				}
				//Give service to every customer being attended
				for (Server s : servers) {
					s.serve();
					Customer actualCustomer = s.getCustomer();
					//Checks if server finished with customer 
					if(actualCustomer.isServiceCompleted()) { 
						attendedCustomers.add(actualCustomer);  
						attendingCustomers--;
						emptyServers.add(s);  
					}
				}
			}
			//Checks if a new customer arrives to the line and adds it
			while(!inputQueue.isEmpty() && inputQueue.peek().getArrivalTime()==time)
				line.add(inputQueue.remove());
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
	private static double averageTime(ArrayList<Customer>  attendedCustomers){
		double avg = 0;
		for(Customer j : attendedCustomers){
			avg += j.getAttendingTime()-j.getArrivalTime();
		}
		avg = avg/attendedCustomers.size();
		return avg;
	}
}
