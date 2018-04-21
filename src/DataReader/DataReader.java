package DataReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

import Customer.Customer;
import java.util.Deque;

public class DataReader {

	private static ArrayList<String> readFileNames() throws FileNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		String fileName = "dataFiles.txt"; 
		Scanner inputFile = new Scanner(new File("src",fileName)); 
		ArrayList<String> fileContent = new ArrayList<>(); 
		while (inputFile.hasNext())
			fileContent.add(inputFile.nextLine());
		inputFile.close();
		return fileContent; 
	}
	private static ArrayList<Deque> readData() throws FileNotFoundException{
		ArrayList<String> fileNames = readFileNames();
		ArrayList<Deque> files = new ArrayList<Deque>();
		for (String fileName : fileNames) {
			Scanner inputFile = new Scanner(new File("src",fileName));
			ArrayList<String> fileContent = new ArrayList<>(); 
			while (inputFile.hasNext())
				fileContent.add(inputFile.nextLine());
			inputFile.close();
			Deque<Customer> cd = new ArrayDeque<>();
			for (String s : fileContent){
				String[] inputs = s.split(",");
				Customer nc = new Customer(fileNames.indexOf(s), Integer.valueOf(inputs[0]), Integer.valueOf(inputs[1]));
				cd.add(nc);
			}
			files.add(cd);
		}
		return files;
	}
}
