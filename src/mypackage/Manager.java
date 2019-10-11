/**
 * CPSC 501 Assignment1: Refactoring
 * @author: Mona Agh
 */
package mypackage;

import java.io.*;

public class Manager extends DomainObject{
	public Customer customer;
	public Movie movie;
	public Tape tape;
	public Rental rental;
	public BufferedReader fileReader;
	String command = null;
	int number = 0;
	//String customerName = null;
	//String phoneNumber = null;
	//String email = null;
	//String movieName = null;
	//int movieCode = 0;
	//String tapeSN = null;
	BufferedReader reader;
	CustomerHandler cHandler;
	MovieHandler mHandler;
	
	public Manager() {
		reader = new BufferedReader(new InputStreamReader(System.in));
		cHandler = new CustomerHandler();
		mHandler = new MovieHandler();
	}
	
	
	public void init() throws IOException {
		
		System.out.println("Please choose from the following commands by entering the number");
		while (true) {
		
			System.out.println("1. Enter a new customer");
			System.out.println("2. Enter a new movie");
			System.out.println("3. Rent a movie");
			System.out.println("4. Get a customer's statement");
			System.out.println("5. Get the list of the customers that have overdue");
			System.out.println("6. Exit from the application");
			System.out.println("-----------------------------------------------------------------");
		
			try {
				command = reader.readLine();
			} catch (IOException e) {
				System.out.println("Exception:" + e);
			}
		
			try {
				number = Integer.parseInt(command);
		
				if (number == 1) { 
					cHandler.addCustomer(reader);
					cHandler.printInstruction(reader);
				} else if (number == 2) {
					mHandler.addMovie(reader);
					mHandler.printInstruction(reader);
				} else if (number == 3) {
					rentMovie();	
				} else if (number == 4) {
					cHandler.statement(reader);	
				} else if (number == 5) {
					getCustomerWithOverdue();
				} else if (number == 6) {
					exit();
				} else {
					System.out.println("Wrong number! Please enter a number from the following commands.");
				}
			} catch (NumberFormatException e) {
				
			}
		}
	}
	
	

	private void getCustomerWithOverdue() throws FileNotFoundException, IOException {
		String fileName = "rental.txt";
		String customerName;
		
		fileReader = new BufferedReader(new FileReader(fileName));
		String line;
		String[] data;
		int rentDays;
		String nameCap;
		String tapeSN;
		while ((line = fileReader.readLine()) != null) {
			data = line.split(",");
			rentDays = Integer.parseInt(data[2]);
			tapeSN = data[0];
			customerName = data[1];
			if (rentDays > 10) {
				customer = cHandler.searchCustomer(customerName, cHandler.getCustomerReader());
				tape = mHandler.searchTape(tapeSN, mHandler.getMovieReader());
				nameCap = customer.name().substring(0,1).toUpperCase() + customer.name().substring(1);
				int overdue = rentDays - 10;
				System.out.print(nameCap + " has " + overdue + " days overdue for " + tape.movie().name());
				System.out.println(". The contact information is:");
				System.out.println("\tEmail: " + customer.email);
				System.out.println("\tPhone Number: " + customer.number);
				System.out.println("-----------------------------------------------------------------");
				
			}
		} 
		
		printInstruction();
	}


	private void printInstruction() throws IOException {
		System.out.println("Please press a key to continue.");
		command = reader.readLine();
		System.out.println("Please choose from the following commands by entering the number");
	}



	private void rentMovie() throws IOException {
		customer = null;
		tape = null;
		String tapeSN;
		String customerName =null; 
		
		while (customer == null) {
			customerName = cHandler.getCustomerName(reader);
			customer = cHandler.searchCustomer(customerName.toLowerCase(), cHandler.getCustomerReader());
			
			if (customer == null) {
				System.out.println("You need to first add the customer to database");
				cHandler.addCustomer(reader);
				System.out.println("Now you can rent a movie");
			}
		}
			
		while (tape == null) {
			tapeSN = mHandler.getMovieSerialNumber(reader);
			tape = mHandler.searchTape(tapeSN, mHandler.getMovieReader());
			
			if (tape == null) {
				System.out.println("You need to first add the movie to the database");
				mHandler.addMovie(reader);
				System.out.println("Now you can rent the movie");
			}
		}

		rental = new Rental(tape,customer, 0);
		rental.persist();
		System.out.println("Mission accomplished! Rental information added to the database");
		System.out.println("-----------------------------------------------------------------");
		printInstruction();
	}
			

	private void exit() {
		System.exit(0);
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public Movie getMovie() {
		return movie;
	}
}
