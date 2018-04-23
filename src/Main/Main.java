package Main;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Queue;

import Customer.Customer;
import DataReader.DataReader;
import Lines.SLMS;

public class Main {
	public static void main(String args) {
		ArrayList<Queue<Customer>> dataFiles = null;
		ArrayList<String> result = new ArrayList<String>() ;
		try {
			dataFiles = DataReader.readData();
		} catch (FileNotFoundException e) {
			// write to output file : Input file not found.
		}
		
		int numServer = 1;
		while(numServer <6) {
			for (Queue<Customer> file : dataFiles) {
				SLMS slms = new SLMS(numServer);
				result.add(slms.process(file)); //the file should be a clone
			}
			numServer+=2;
		}
	}
	public void generateOutputFile(ArrayList<String> result) {
		//TODO
	}
}
