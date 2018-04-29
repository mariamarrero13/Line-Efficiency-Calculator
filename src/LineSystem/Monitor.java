package LineSystem;

import java.util.ArrayList;
import java.util.Deque;
import java.util.ListIterator;

import Customer.Customer;

public class Monitor {

	public static void balanceCustomers (ArrayList<Deque<Customer>> lines) {
		int minIndex;
		int maxIndex;
		int min =0;
		int max = 0;
		if (lines.isEmpty()) {
			minIndex = -1;
			maxIndex = -1;
		} else {
			min = lines.get(0).size(); // first element as the current minimum
			max = min;
			minIndex = 0;
			maxIndex = 0;
			for (Deque<Customer> line : lines) {
				int curr = line.size();
				if (curr< min) {
					min = curr;
					minIndex = lines.indexOf(line);
				}
				if (curr > max) {
					max = curr;
					maxIndex = lines.indexOf(line);
				}
			}
		} 
		if((max-min) > 1) {
			lines.get(minIndex).addLast((lines.get(maxIndex).pollLast()));
		}
	}

	public static int checkForFasterLine (ArrayList<Deque<Customer>> lines) {
		int fasterIndex = 0;	  // The index of the line that has less waiting time
		int lessWaitingTime = Integer.MAX_VALUE;  //The smaller waiting time of a line

		for(Deque<Customer> line : lines ) {
			int currTime = 0;		  //The waiting time of the actual line we are iterating over
			for(Customer c : line) {
				currTime = currTime + c.getRemainingTime();
			}
			if (currTime < lessWaitingTime) {
				lessWaitingTime = currTime;
				fasterIndex = lines.indexOf(line);
			}
		}
		return fasterIndex;

	}

}