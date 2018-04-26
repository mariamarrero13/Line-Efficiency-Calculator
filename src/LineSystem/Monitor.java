package LineSystem;

import java.util.ArrayList;
import java.util.Deque;
import java.util.ListIterator;

import Customer.Customer;

public class Monitor {

	public static void checkForCustBalance (ArrayList<Deque<Customer>> lines) {
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
			for (int i =0 ; i <lines.size() ; i++) {

				int curr = lines.get(i).size();
				if (curr< min) {
					min = curr;
					minIndex = i;
				}
				if (curr > max) {
					max = curr;
					maxIndex = i;
				}
			}
		} 
		if((max-min) > 1) {
			System.out.println("Before Min: " +lines.get(minIndex).size() + "Max: " +lines.get(maxIndex).size());
			lines.get(minIndex).addLast((lines.get(maxIndex).pollLast()));
			System.out.println("Min: " +min + "Max: " +max);
			System.out.println("After Min: " +lines.get(minIndex).size() + "Max: " +lines.get(maxIndex).size());
		}
	}

	public static void checkForTimeBalance (ArrayList<Deque<Customer>> lines) {
		
	}

}
