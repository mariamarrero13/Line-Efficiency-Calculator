package DataReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

import Customer.Customer;
import java.util.Deque;

public class DataReader {

	private static ArrayList<String> readData() throws FileNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		String fileName = "dataFiles.txt"; 
		Scanner inputFile = new Scanner(new File("src",fileName)); 
		ArrayList<String> fileContent = new ArrayList<>(); 
		while (inputFile.hasNext())
			fileContent.add(inputFile.nextLine());
		inputFile.close();
		return fileContent; 
	}
	private static Deque<Customer> arraytoQueue(ArrayList<String> data) {
		Deque<Customer> inputDeque = new ArrayDeque<Customer>();
		for (String s : data){
			String[] inputs = s.split(",");
			Customer nc = new Customer(data.indexOf(s), Integer.valueOf(inputs[0]), Integer.valueOf(inputs[1]));
			inputDeque.add(nc);
		}

		return inputDeque;
	}
	
}
