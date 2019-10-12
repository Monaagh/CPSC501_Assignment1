/**
 * CPSC 501 Assignment1: Refactoring
 * @author: Mona Agh
 */
package mypackage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Registrar {
	private static HashMap<String, Object> instances;
	static BufferedWriter writer;
	
	public Registrar() {
		instances = new HashMap<String, Object>();
		writer = null;
	}
	public static void add(String name, Customer customer) {
		instances.put(name, customer);
		try {
			String fileCustomer = "customer.txt";
			String cName = customer.getName();
			String cNumber = customer.getNumber();
			String cEmail = customer.getEmail();
			
			writer = new BufferedWriter(new FileWriter(fileCustomer, true));
			writer.write(cName + "," + cNumber + "," + cEmail);
			writer.write("\n");
			writer.close(); 
			
			
		} catch (IOException e) {
			System.out.println("Exception:" + e);
		}
	}

	public static void add(String name, Movie movie) {
		instances.put(name, movie);		
	}
	
	public static void add(String name, Rental rental) {
		instances.put(name, rental);
		try {
			String fileRental = "rental.txt";
			//String mName = rental.tape.movie().getName();
			//int mCode = tape.movie().priceCode();
			String mSNumber = rental.tape().getSerial();
			String cName = rental.customer().getName();
			int daysRented = rental.daysRented();
			
			writer = new BufferedWriter(new FileWriter(fileRental, true));
			writer.write(mSNumber + "," + cName + "," + daysRented);
			writer.write("\n");
			writer.close();
			
			
		} catch (IOException e) {
			System.out.println("Exception:" + e);
		}
	}
	
	public static void add(String name,Tape tape) {
		instances.put(name, tape);	
		try {
			String fileTape = "tape.txt";
			String mName = tape.movie().getName();
			int mCode = tape.movie().priceCode();
			String mSNumber = tape.getSerial();
			
			writer = new BufferedWriter(new FileWriter(fileTape, true));
			writer.write(mSNumber + "," + mName + "," + mCode);
			writer.write("\n");
			writer.close();
			
			
		} catch (IOException e) {
			System.out.println("Exception:" + e);
		}
	}

	public static Object get(String string, String name) {
		return instances.get(name);
	}

}
