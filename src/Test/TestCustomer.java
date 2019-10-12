package Test;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Vector;
import org.junit.Before;
import org.junit.After;

import mypackage.*;

public class TestCustomer {
	
	Movie movie1;
	Movie movie2;
	Tape tape1;
	Tape tape2;
	Rental rental;
	
	@Before
	public void setUp() {
		movie1 = new Movie("grudge", 0);
		movie2 = new Movie("Harry Potter", 0);
		tape1 = new Tape("123", movie1);
		tape2 = new Tape("124", movie2);
			
	}
	
	@Test
	public void testName() {
		Customer john = new Customer("John Doe", "403", "John@gmail.com");
		assertEquals("John Doe", john.name());
		assertEquals("403", john.getNumber());
		assertEquals("John@gmail.com", john.getEmail());
	}
	
	@Test
	public void testTotalAmount() throws NumberFormatException, IOException {
		
		Customer john = new Customer("John Doe", "403", "John@gmail.com");
		Rental rental = new Rental(tape1, john, 23);
		Vector<Rental> vector = new Vector<Rental>();
		vector.addElement(rental);
		String temp = "";
		john.getAmountOwed(temp, vector);
		
		assertEquals(33.5, john.getTotalAmount(), 0.0001);
		//assertNull(tape.movie());
	}
	
	@Test
	public void testTotalAmountZero() throws NumberFormatException, IOException {
		
		Customer john = new Customer("John Doe", "403", "John@gmail.com");
		Rental rental = new Rental(tape1, john, 0);
		Vector<Rental> vector = new Vector<Rental>();
		vector.addElement(rental);
		String temp = "";
		john.getAmountOwed(temp, vector);
		
		assertEquals(2, john.getTotalAmount(), 0.0001);

	}
	
	@Test
	public void testTotalAmountNegative() throws NumberFormatException, IOException {
		
		Customer john = new Customer("John Doe", "403", "John@gmail.com");
		Rental rental = new Rental(tape1, john, -2);
		Vector<Rental> vector = new Vector<Rental>();
		vector.addElement(rental);
		String temp = "";
		john.getAmountOwed(temp, vector);
		
		assertEquals(2, john.getTotalAmount(), 0.0001);

	}
	
	@Test
	public void testTotalAmountTwoMovie() throws NumberFormatException, IOException {
		
		Customer john = new Customer("John Doe", "403", "John@gmail.com");
		Rental rental1 = new Rental(tape1, john, 23);
		Rental rental2 = new Rental(tape2, john, 0);
		Vector<Rental> vector = new Vector<Rental>();
		vector.addElement(rental1);
		vector.addElement(rental2);
		String temp = "";
		john.getAmountOwed(temp, vector);
		
		assertEquals(35.5, john.getTotalAmount(), 0.0001);

	}
	
	@Test
	public void testTotalAmountNoRental() throws NumberFormatException, IOException {
		
		Customer john = new Customer("John Doe", "403", "John@gmail.com");
		john.getStatement();		
		assertEquals(0, john.getTotalAmount(), 0.0001);

	}
	
	@Test
	public void testGetRentalRecordNull() throws NumberFormatException, IOException {
		Customer john = new Customer("John Doe", "403", "John@gmail.com");
		String rentalFile = "";
		InputStream stream = new ByteArrayInputStream(rentalFile.getBytes(StandardCharsets.UTF_8));
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		Vector<Rental> vectorExpected  = john.getRentalRecord(reader);
		Vector<Rental> vector = new Vector<Rental>();
		assertEquals(vectorExpected,vector);
		
	}
	
	
	@After
	public void testAfter() {
		System.out.println("all tests done");
	}
}





