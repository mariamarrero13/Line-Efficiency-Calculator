package DataReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

import Customer.Customer;
import java.util.Deque;
import java.util.Queue;

public class DataReader {
/**
 * Reads the names of the inputFiles to be read
 * @return an arrayList with the names of the files
 * @throws FileNotFoundException if the file does not exists
 */
	public static ArrayList<String> readFileNames() throws FileNotFoundException {
		ArrayList<String> data = new ArrayList<String>();
		String fileName = "dataFiles.txt"; 
		Scanner inputFile = new Scanner(new File("inputFiles",fileName)); 
		ArrayList<String> fileContent = new ArrayList<>(); 
		while (inputFile.hasNext())
			fileContent.add(inputFile.nextLine());
		inputFile.close();
		return fileContent; 
	}
	/**
	 * Reads every file named in dataFiles.txt
	 * @return an array list of queues
	 * @throws FileNotFoundException id dataFiles.txt is not found
	 */
	public static ArrayList<Queue<Customer>> readData() throws FileNotFoundException {
		ArrayList<String> fileNames = readFileNames();
		ArrayList<Queue<Customer>> files = new ArrayList<Queue<Customer>>();
		for (String fileName : fileNames) {
			try {
				Scanner inputFile = new Scanner(new File("inputFiles",fileName));
				ArrayList<String> fileContent = new ArrayList<>(); 
				while (inputFile.hasNext())
					fileContent.add(inputFile.nextLine());
				inputFile.close();
				if (fileContent.isEmpty()) throw new NumberFormatException();
				Queue<Customer> cd = new ArrayDeque<>();
				int i=0;
				for (String s : fileContent){
					String[] inputs = s.split(" ");
					Customer nc = new Customer(i++, Integer.valueOf(inputs[0]), Integer.valueOf(inputs[1]));
					cd.add(nc);
				}
				files.add(cd);
			}
			catch(FileNotFoundException e) {
				int indexToTrim = 	fileName.lastIndexOf(".");
				String fn = fileName.substring(0, indexToTrim);
				PrintStream out = new PrintStream(new File("outputFiles", fn + "_OUT.txt"));
				out.println("Input file not found.");
				out.close();
			}
			catch(NumberFormatException e) {
				int indexToTrim = 	fileName.lastIndexOf(".");
				String fn = fileName.substring(0, indexToTrim);
				PrintStream out = new PrintStream(new File("outputFiles", fn + "_OUT.txt"));
				out.println("Input file does not meet the expected format or it is empty");
				out.close();
			}
		}
		return files;
	}
}
