/**
 * CPSC 501 Assignment1: Refactoring
 * @author: Mona Agh
 */
package mypackage;

import java.io.*;
import mypackage.Customer;
public class CustomerHandler {
	String customerName = null;
	String phoneNumber = null;
	String email = null;
	String command;
	
	public CustomerHandler() {
		customerName = null;
		phoneNumber = null;
		email = null;
	}
	
	public void addCustomer(BufferedReader reader) throws IOException {
		customerName = getCustomerName(reader);
		phoneNumber = getCustomerPhone(reader);
		email = getCustomerEmail(reader);	
		
		Customer customer = new Customer(customerName, phoneNumber, email);
		customer.persist();
		System.out.println("Customer added to the database");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Please press a key to continue.");
		command = reader.readLine();
		System.out.println("Please choose from the following commands by entering the number");
	}
	
	
	public void statement(BufferedReader reader) throws IOException {
		Customer customer = null;
		String fileName = "customer.txt";
		BufferedReader customerReader = new BufferedReader(new FileReader(fileName));
		
		try {
			while (true) {
				System.out.println("Enter customer's name:");
				customerName = reader.readLine();
				if (!customerName.isEmpty() || customerName != null) {
					customer = searchCustomer(customerName.toLowerCase(), customerReader);
					break;
				}			
			}
		} catch (IOException e) {
				System.out.println("Exception:" + e);
		}
		 customerReader.close();
		
		if (customer != null) {
			String statement = customer.getStatement();
			System.out.println(statement);
		}
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Please press a key to continue.");
			command = reader.readLine();
			System.out.println("Please choose from the following commands by entering the number");
	}
	
	public Customer searchCustomer(String cName, BufferedReader reader) throws IOException {
		Customer customer = null;
		String line;
		String[] data;
		while ((line = reader.readLine()) != null) {
			data = line.split(",");
			if (data[0].equals(cName)) {
				customer = new Customer(data[0], data[1], data[2]);
			}		
		}
		
		if (customer == null) {
			System.out.println("Opps! This customer is not in the database");
		}
			
		return customer;
	}
	
	public Customer searchCustomer2(String cName, BufferedReader reader) throws IOException {
		Customer customer = null;
		String fileName = "customer.txt";
		BufferedReader customerReader = new BufferedReader(new FileReader(fileName));
		String line;
		String[] data;
		while ((line = customerReader.readLine()) != null) {
			data = line.split(",");
			if (data[0].equals(cName)) {
				customer = new Customer(data[0], data[1], data[2]);
			}
				
		}
		
		if (customer == null) {
			System.out.println("This customer is not in the database, please enter following information to add the customer to the system:");
			String customerName = cName;
			
			String phoneNumber = getCustomerPhone(reader);
			
			String email  = getCustomerEmail(reader);
				
			customer = new Customer(customerName, phoneNumber, email);
			customer.persist();
			System.out.println("Customer added to the database");
			System.out.println("-----------------------------------------------------------------");
		}
		return customer;
	}
	

	public String getCustomerName(BufferedReader reader) {
		String name = null;
		try {
			while (true) {
				System.out.println("Enter customer's name:");
				name = reader.readLine();
				name = name.toLowerCase();
				if (!name.isEmpty()) {
					break;
				}
			}
		} catch (IOException e) {
			System.out.println("Exception:" + e);
		} 
		
		return name;
	}
	
	public String getCustomerPhone(BufferedReader reader) {
		String number = null;
		try {
			while (true) {
				System.out.println("Enter customer's phone:");
				number = reader.readLine();
				if (!number.isEmpty()) {
					break;
				}
			}
		} catch (IOException e) {
				System.out.println("Exception:" + e);
		}
		
		return number;
	}
	
	
	
	public String getCustomerEmail(BufferedReader reader) {
		String email = null;
		try {
			while (true) {
				System.out.println("Enter customer's email:");
				email = reader.readLine();
				if (!email.isEmpty()) {
					break;
				}
			}
		} catch (IOException e) {
				System.out.println("Exception:" + e);
		}

		return email;
	}
}
