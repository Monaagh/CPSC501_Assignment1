package Test;
import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import mypackage.*;

public class TestRental {
	Movie movie;
	Tape tape;
	Customer john;
	Rental rental;
	int days;
	
	@Before
	public void setUp() {
		movie = new Movie("grudge", 0);
		tape = new Tape("123", movie);
		john = new Customer("John Doe", "403", "John@gmail.com");
			
	}
	@Test
	public void testName() {
		days = 2;
		rental = new Rental(tape, john ,days);
		assertEquals("123", rental.tape().getSerial());
		assertEquals("grudge", rental.tape().movie().name());
		assertEquals(0, rental.tape().movie().priceCode());
		assertEquals(02, rental.daysRented());
	}
	
	@After
	public void testAfter() {
		System.out.println("all tests done");
	}

}
