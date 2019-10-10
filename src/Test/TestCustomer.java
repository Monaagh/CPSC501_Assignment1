package Test;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
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
		assertEquals("403", john.number);
		assertEquals("John@gmail.com", john.email);
	}
	
	@Test
	public void testTotalAmount() throws NumberFormatException, IOException {
		
		Customer john = new Customer("John Doe", "403", "John@gmail.com");
		Rental rental = new Rental(tape1, john, 23);
		john.addRental(rental);
		john.statement();
		
		assertEquals(33.5, john.totalAmount, 0.0001);
		//assertNull(tape.movie());
	}
	
	@Test
	public void testTotalAmountZero() throws NumberFormatException, IOException {
		
		Customer john = new Customer("John Doe", "403", "John@gmail.com");
		Rental rental = new Rental(tape1, john, 0);
		john.addRental(rental);
		john.statement();
		
		assertEquals(2, john.totalAmount, 0.0001);

	}
	
	@Test
	public void testTotalAmountNegative() throws NumberFormatException, IOException {
		
		Customer john = new Customer("John Doe", "403", "John@gmail.com");
		Rental rental = new Rental(tape1, john, -2);
		john.addRental(rental);
		john.statement();
		
		assertEquals(2, john.totalAmount, 0.0001);

	}
	
	@Test
	public void testTotalAmountTwoMovie() throws NumberFormatException, IOException {
		
		Customer john = new Customer("John Doe", "403", "John@gmail.com");
		Rental rental1 = new Rental(tape1, john, 23);
		Rental rental2 = new Rental(tape2, john, 0);
		john.addRental(rental1);
		john.addRental(rental2);
		john.statement();
		
		assertEquals(35.5, john.totalAmount, 0.0001);

	}
	
	@Test
	public void testTotalAmountNoRental() throws NumberFormatException, IOException {
		
		Customer john = new Customer("John Doe", "403", "John@gmail.com");
		//Rental rental1 = new Rental(tape1, john, 23);
		//john.addRental(rental1);
		john.statement();		
		assertEquals(0, john.totalAmount, 0.0001);

	}
	
	
	@After
	public void testAfter() {
		System.out.println("all tests done");
	}
}





