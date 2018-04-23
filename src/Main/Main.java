package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import Customer.Customer;
import DataReader.DataReader;
import Lines.SLMS;

public class Main {
	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
		ArrayList<Queue<Customer>> dataFiles = null;
		ArrayList<String> fileNames = DataReader.readFileNames();
		ArrayList<String> result = new ArrayList<String>() ;
		try {
			dataFiles = DataReader.readData();
			int i=0;
			for (Queue<Customer> file : dataFiles) {
				SLMS slms = new SLMS();

				result.add(slms.process(clone(file),1)); //the file should be a clone
//				result.add(slms.process(clone(file),3)); //the file should be a clone
//				result.add(slms.process(clone(file),5)); //the file should be a clone

				generateOutputFile(result , fileNames.get(i));
				result.clear();
				i++;
			}
		} catch (FileNotFoundException e) {
			// write to output file : Input file not found.
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
		
		Queue<Customer> copy = new ArrayDeque<Customer>(file);
		return copy;		
	}
}
