package LineSystem;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Queue;

import Customer.Customer;
import Server.Server;
/**
 * 
 * @author Maria A Marrero ID 802154204 Sect 090
 * @author Yamil J Gonzalez ID 802153112 Sect 090
 *
 */
public class MLMS extends SLMS {
	/**
	 * Simulates the MLMS line system with the inputLine received
	 * @param input : the inputLine used to simulate the system
	 * @param numServers : the number of servers in the simulation
	 * @return a string with the time that the system finished simulating, the average waiting time per customer and the
	 * 			average number of customers overpassing
	 */
	public String process(Queue<Customer> input, int numServers) {

		Queue<Customer> inputQueue = input;									 //list of upcoming customers
		ArrayList<Deque<Customer>> line = new ArrayList<Deque<Customer>>();	 //list of lines
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
				}
			}
			time++;
		}
		//compute final statistics
		return "MLMS    " + numServers + ": " + time +" "+averageTime(attendedCustomers) + " " +customerOverpassing(attendedCustomers);
	}
	/**
	 * Finds the index of the line that has less customers
	 * @param line the arraylist of queues to search in
	 * @return the index of the line
	 */
	protected static int findMin(ArrayList<Deque<Customer>> line) {
		int minIndex;
		if (line.isEmpty()) {
			minIndex = -1;
		} else {
			final ListIterator<Deque<Customer>> iter = line.listIterator();
			Integer min = iter.next().size(); // first element as the current minimum
			minIndex = iter.previousIndex();
			while (iter.hasNext()) {
				Integer curr = iter.next().size();
				if (curr.compareTo(min) < 0) {
					min = curr;
					minIndex = iter.previousIndex();
				}
			}
		}
		return minIndex;
	}
	/**
	 * Verifies if every queue in the arrayList is empty
	 * @param line
	 * @return true if at least one line still has customers
	 */
	protected static boolean isEmpty(ArrayList<Deque<Customer>> line) {
		for(Deque<Customer> dq : line) {
			if(!dq.isEmpty()) return false;
		}
		return true;
	}
	/**
	 * Verifies if a server is not attending a costumer but still has a line
	 * @param emptyServers
	 * @param line
	 * @return true if at least one server follows this statement
	 **/
	protected boolean serverHasLine(ArrayList<Server> emptyServers, ArrayList<Deque<Customer>> line) {
		for(Server s: emptyServers) {
			if(!(line.get(s.getServerid()-1).isEmpty())){
				return true;
			}
		}
		return false;
	}
	/**
	 * Calculates the average number of customers that reached the server before another customer who arrived earlier
	 * @param attendedCustomers
	 * @return a double number containing the average value.
	 */
	protected double customerOverpassing(ArrayList<Customer> attendedCustomers) {
		int i = 0;
		double m = 0;
		while (i< attendedCustomers.size()) {
			Customer c = attendedCustomers.get(i);
			for(Customer g : attendedCustomers) {
				if(c.getArrivalTime()<g.getArrivalTime() && c.getAttendingTime()>g.getAttendingTime()) {
					m++;
				}
			}
			i++;
		}
		m = m / (double) attendedCustomers.size();
		return  Math.round(m * 100.0) / 100.0;
	}
}
