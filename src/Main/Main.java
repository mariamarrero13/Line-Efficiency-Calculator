package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

import Customer.Customer;
import DataReader.DataReader;
import LineSystem.LineSystem;
import LineSystem.MLMS;
import LineSystem.MLMSBLL;
import LineSystem.MLMSBWT;
import LineSystem.SLMS;

public class Main {
	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
		ArrayList<Queue<Customer>> dataFiles = null;
		ArrayList<String> fileNames = DataReader.readFileNames();
		ArrayList<String> result = new ArrayList<String>() ;
			dataFiles = DataReader.readData();
			int i=0;
			for (Queue<Customer> file : dataFiles) {
				result.add("Number of customers is: " + file.size());
				ArrayList<LineSystem> lineSystems = new ArrayList<>();
				lineSystems.add(new SLMS());
				lineSystems.add(new MLMS());
				lineSystems.add(new MLMSBLL());
				lineSystems.add(new MLMSBWT());
				for (LineSystem ls : lineSystems) {
					for (int j =1 ; j <6 ; j = j+2) {
						result.add(ls.process(clone(file),j)); 
					}
				}
				generateOutputFile(result , fileNames.get(i));
				result.clear();
				i++;
			}

	}
	public static void generateOutputFile(ArrayList<String> result , String fileName) throws FileNotFoundException, UnsupportedEncodingException {
		int indexToTrim = 	fileName.lastIndexOf(".");
		String fn = fileName.substring(0, indexToTrim);
		PrintStream out = new PrintStream(new File("outputFiles", fn + "_OUT.txt"));
		for (String s : result) {
			out.println(s);
		}
		out.close();
	}
	private static Queue<Customer> clone(Queue<Customer> file) {
		Queue<Customer> copy = new ArrayDeque<>();
		Iterator<Customer> iter = file.iterator();
		while(iter.hasNext()) {
			Customer c = iter.next();
			Customer copyCust = new Customer(c.getCustid(),c.getArrivalTime(),c.getRemainingTime());
			copy.add(copyCust);
		}
		return copy;
	}
}
