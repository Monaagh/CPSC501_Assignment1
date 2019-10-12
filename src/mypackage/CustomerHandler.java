/**
 * CPSC 501 Assignment1: Refactoring
 * @author: Mona Agh
 */
package mypackage;

import java.io.*;
import mypackage.Customer;
public class CustomerHandler extends Handler {
	String customerName;
	String phoneNumber;
	String email;

	
	
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
		//printInstruction(reader);
	}

	public void statement(BufferedReader reader) throws IOException {
		Customer customer = null;
		BufferedReader customerReader = getCustomerReader();
		
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
			//String statement = customer.getStatement();
			System.out.println(customer.getStatement());
		}
			System.out.println("-----------------------------------------------------------------");
			printInstruction(reader);
	}
	
	public Customer searchCustomer(String cName, BufferedReader customerReader) throws IOException {
		Customer customer = null;
		String line;
		String[] data;
		while ((line = customerReader.readLine()) != null) {
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
	

	public BufferedReader getCustomerReader() throws FileNotFoundException {
		String fileName = "customer.txt";
		BufferedReader customerReader = new BufferedReader(new FileReader(fileName));
		return customerReader;
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
