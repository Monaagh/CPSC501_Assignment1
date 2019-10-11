/**
 * CPSC 501 Assignment1: Refactoring
 * @author: Mona Agh
 */
package mypackage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

public class Customer extends DomainObject
    {
	 public double totalAmount;
     public int frequentRenterPoints = 0;
     public String number;
     public String email;
     public boolean redeem;
     public double award;
     Vector<Rental> rentalVector = new Vector<Rental>();
    
     
	public Customer(String name, String number, String email) {
        setName(name);
        redeem = false;
        this.number = number;
        this.email = email;
    }
    public String getStatement() throws NumberFormatException, IOException {
    	String fileName = "rental.txt";
    	String header = "Rental Record for " + name() + ":\n";
    	
		BufferedReader fileReader = new BufferedReader(new FileReader(fileName)); 
        Vector<Rental> rentalVector = getRentalRecord(fileReader);
        
        String result = getAmountOwed(header, rentalVector);
        //add footer lines
        result +=  "\tAmount owed is " + String.valueOf(totalAmount) + "\n";
        result += "\tYou earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }
    
	public String getAmountOwed(String result, Vector<Rental> rentalVector) throws NumberFormatException, IOException {
		totalAmount=0;
		
        Enumeration<Rental> rentals = rentalVector.elements();
		while (rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental) rentals.nextElement();

            //determine amounts for each line
            switch (each.tape().movie().priceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (each.daysRented() > 2)
                        thisAmount += (each.daysRented() - 2) * 1.5;
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += each.daysRented() * 3;
                    break;
                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if (each.daysRented() > 3)
                        thisAmount += (each.daysRented() - 3) * 1.5;
                    break;

            }
            totalAmount += thisAmount;
            updateFreqPoints(each);					//added method
            //show figures for this rental
            result += "\t" + each.tape().movie().name()+ "\t" + String.valueOf(thisAmount) + "\n";
		}
		return result;
	}
	
	public Vector<Rental> getRentalRecord(BufferedReader fileReader) throws FileNotFoundException, IOException {
		Vector<Rental> rentalVector = new Vector<Rental>();
		Rental rentalRecord = null; 
		String line;
		String[] data;
		while ((line = fileReader.readLine()) != null) {
			data = line.split(",");
			if (data[1].equals(this.name())) {
				//System.out.println(data[1]);
				Manager manager = new Manager();
				Customer customer = manager.cHandler.searchCustomer(data[1],manager.cHandler.getCustomerReader());
				Tape tape = manager.mHandler.searchTape(data[0], manager.mHandler.getMovieReader());
				int days = Integer.parseInt(data[2]);
				rentalRecord = new Rental(tape, customer, days);
				rentalVector.addElement(rentalRecord);
			}		
		}
		
		return rentalVector;
	}
	
	
	private void updateFreqPoints(Rental each) {
		 // add frequent renter points
        frequentRenterPoints ++;
        // add bonus for a two day new release rental
        if ((each.tape().movie().priceCode() == Movie.NEW_RELEASE) && each.daysRented() > 1) frequentRenterPoints ++;
        
        award = 0.01 * frequentRenterPoints;
        if (award > 10) {
        	redeem = true;
        }
	}
	
	public void redeemAward(int amount) {
		if (redeem) {
			award = award - amount;
		}
	}
	
	public void addRental(Rental arg) {
     	rentalVector.addElement(arg);
    }
	
    public static Customer get(String name) {
    	return (Customer) Registrar.get("Customers", name);
    }
    public void persist() {
    	Registrar registrar = new Registrar();
    	registrar.add("Customers", this);
    }
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
    }