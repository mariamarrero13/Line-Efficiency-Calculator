package LineSystem;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;

import Customer.Customer;
import Server.Server;

public class MLMSBLL extends MLMS {
	/**
	 * Simulates the MLMS line system with the inputLine received
	 * @param input : the inputLine used to simulate the system
	 * @param numServers : the number of servers in the simulation
	 * @return a string with the time that the system finished simulating, the average waiting time per customer and the
	 * 			average number of customers overpassing
	 */
	public String process(Queue<Customer> input, int numServers) {
		Queue<Customer> inputQueue = input;									 //list of upcoming customers
		ArrayList<Deque<Customer>> line = new ArrayList<Deque<Customer>>();  //list of lines
		int attendingCustomers = 0 ; 										 //number of customers being attended
		ArrayList<Customer> attendedCustomers = new ArrayList<Customer>();	 //list of customers that servers finished attending
		ArrayList<Server> busyServers = new ArrayList<Server>();			 // list of servers with customers
		ArrayList<Server> emptyServers = new ArrayList<Server>();			 //list of servers with no customers
		int time = 0;
		//initializes every server with an id from 1 to n
		for(int i = 1 ; i< numServers + 1 ; i++) {
			emptyServers.add(new Server(i));
			line.add(new ArrayDeque<Customer>());
		}


		while(!inputQueue.isEmpty()||!isEmpty(line)||!(attendingCustomers ==0)){

			//Checks if a new customer arrives to the line and adds it
			while(!inputQueue.isEmpty() && inputQueue.peek().getArrivalTime()==time) {
				//calculate which line is emptier and deposit the customer in it
				int min = findMin(line);
				line.get(min).add(inputQueue.remove());
			}
			//if there is a server available, assigns a new customer to it
			int i = 0;
			while (!isEmpty(line) && serverHasLine(emptyServers, line) ) {
				Server s = emptyServers.get(i);
				if(!line.get(s.getServerid()-1).isEmpty()) {
					Customer nc = line.get(s.getServerid()-1).remove();
					s.setCustomer(nc);
					nc.setAttendingTime(time);
					emptyServers.remove(s);
					busyServers.add(s);
					attendingCustomers++;
				}
				else {
					i++;
				}
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
					Monitor.balanceCustomers(line);
				}
			}
			time++;
		}
		//compute final statistics
		return "MLMSBLL " + numServers + ": " + time +" "+averageTime(attendedCustomers) + " " +customerOverpassing(attendedCustomers);
	}
}
