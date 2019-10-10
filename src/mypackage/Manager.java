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
	String customerName = null;
	String phoneNumber = null;
	String email = null;
	String movieName = null;
	int movieCode = 0;
	String tapeSN = null;
	BufferedReader reader;
	
	public Manager() {
		reader = new BufferedReader(new InputStreamReader(System.in));
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
					addCustomer();		
				} else if (number == 2) {
					addMovie();		
				} else if (number == 3) {
					rentMovie();	
				} else if (number == 4) {
					getStatement();	
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
	
	private void addCustomer() throws IOException {
		customerName = getCustomerName(reader);
		phoneNumber = getCustomerPhone(reader);
		email = getCustomerEmail(reader);	
		
		customer = new Customer(customerName, phoneNumber, email);
		customer.persist();
		System.out.println("Customer added to the database");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Please press a key to continue.");
		command = reader.readLine();
		System.out.println("Please choose from the following commands by entering the number");
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
	
	private void addMovie() throws IOException {
		movieName = getMovieName(reader);		
		movieCode = getMoviePriceCode(reader);		
		tapeSN = getMovieSerialNumber(reader);	
		
		movie = new Movie(movieName, movieCode);
		tape = new Tape(tapeSN, movie); 
		tape.persist();
		System.out.println("Tape added to the database");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Please press a key to continue.");
		command = reader.readLine();
		System.out.println("Please choose from the following commands by entering the number");
	}


	public String getMovieSerialNumber(BufferedReader reader) {
		String sn = null;
		try {
			while (true) {
				System.out.println("Enter tape's serial number:");
				sn = reader.readLine();
				if (!sn.isEmpty()) {
					break;
				}
			}
		} catch (IOException e) {
				System.out.println("Exception:" + e);
		}
		
		return sn;
	}


	public int getMoviePriceCode(BufferedReader reader) {
		int code = 0;
		try {
			while (true) {
				System.out.println("Enter movies's price code:");
				String temp = reader.readLine();
				
				if (!temp.isEmpty()) {
					code = Integer.parseInt(temp);
					break;
				}
			}
		} catch (IOException e) {
				System.out.println("Exception:" + e);
		}
		
		return code;
	}


	public String getMovieName(BufferedReader reader) {
		String name = null;
		try {
			while (true) {
				System.out.println("Enter movie's name:");
				name = reader.readLine();
				if (!name.isEmpty()) {
					break;
				}
			}
		} catch (IOException e) {
				System.out.println("Exception:" + e);
		}
		
		return name;
	}


	private void getCustomerWithOverdue() throws FileNotFoundException, IOException {
		String fileName = "rental.txt";
		fileReader = new BufferedReader(new FileReader(fileName));
		String line;
		String[] data;
		int rentDays;
		String nameCap;
		while ((line = fileReader.readLine()) != null) {
			data = line.split(",");
			rentDays = Integer.parseInt(data[2]);
			tapeSN = data[0];
			customerName = data[1];
			if (rentDays > 10) {
				customer = searchCustomer(customerName);
				tape = searchTape(tapeSN);
				nameCap = customer.name().substring(0,1).toUpperCase() + customer.name().substring(1);
				int overdue = rentDays - 10;
				System.out.print(nameCap + " has " + overdue + " days overdue for " + tape.movie().name());
				System.out.println(". The contact information is:");
				System.out.println("\tEmail: " + customer.email);
				System.out.println("\tPhone Number: " + customer.number);
				System.out.println("-----------------------------------------------------------------");
				
			}
		} 
		
		System.out.println("Please press a key to continue.");
		command = reader.readLine();
		System.out.println("Please choose from the following commands by entering the number");
	}


	private void getStatement() throws IOException {
		try {
			while (true) {
				System.out.println("Enter customer's name:");
				customerName = reader.readLine();
				if (!customerName.isEmpty() || customerName != null) {
					customer = searchCustomerStatement(customerName.toLowerCase());
					break;
				}			
			}
		} catch (IOException e) {
				System.out.println("Exception:" + e);
		}
		
		//System.out.println(customer.name());
		if (customer != null) {
			String statement = customer.statement();
			System.out.println(statement);
		}
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Please press a key to continue.");
			command = reader.readLine();
			System.out.println("Please choose from the following commands by entering the number");
	}


	private void rentMovie() throws IOException {
		try {
			while (true) {
				System.out.println("Enter customer's name:");
				customerName = reader.readLine();
				if (!customerName.isEmpty()) {
					customer = searchCustomer(customerName.toLowerCase());
					break;
				}			
			}
		} catch (IOException e) {
				System.out.println("Exception:" + e);
		}
		
		try {
			while (true) {
				System.out.println("Enter tape's serial number:");
				tapeSN = reader.readLine();
				if (!tapeSN.isEmpty()) {
					tape = searchTape(tapeSN);
					break;
				}			
			}
		} catch (IOException e) {
				System.out.println("Exception:" + e);
		}

		rental = new Rental(tape,customer, 0);
		rental.persist();
		System.out.println("Mission accomplished! Rental information added to the database");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Please press a key to continue.");
		command = reader.readLine();
		System.out.println("Please choose from the following commands by entering the number");
	}
				
	
	public Customer searchCustomer(String cName) throws IOException {
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
			customerName = cName;
			
			phoneNumber = getCustomerPhone(reader);
			
			email = getCustomerEmail(reader);
				
			customer = new Customer(customerName, phoneNumber, email);
			customer.persist();
			System.out.println("Customer added to the database");
			System.out.println("-----------------------------------------------------------------");
		}
		return customer;
	}
	
	public Customer searchCustomerStatement(String cName) throws IOException {
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
		//fileReader.close();
		
		if (customer == null) {
			System.out.println("Opps! This customer is not in the database");
		}
			
		return customer;
	}
	
	public Tape searchTape(String tapeSN) throws IOException {
		Tape tape = null;
		Movie movie = null;
		String fileName = "tape.txt";
		BufferedReader tapeReader = new BufferedReader(new FileReader(fileName));
		//tapeReader = 
		String line;
		String[] data;
		while ((line = tapeReader.readLine()) != null) {
			data = line.split(",");
			if (data[0].equals(tapeSN)) {
				movie = new Movie(data[1], Integer.parseInt(data[2]));
			    tape = new Tape(data[0], movie);
			}
				
		}
		
		if (tape == null) {
			System.out.println("This tape is not in the database, please enter following information to add the tape to the system:");
			this.tapeSN = tapeSN;
			movieName = getMovieName(reader);
			
			movieCode = getMoviePriceCode(reader);
			
			
			movie = new Movie(movieName, movieCode);
			tape = new Tape(tapeSN, movie); 
			tape.persist();
			System.out.println("Tape added to the database");
			System.out.println("-----------------------------------------------------------------");

		}
		return tape;
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