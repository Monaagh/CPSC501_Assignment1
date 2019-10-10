package Test;
import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import mypackage.*;

public class TestTape {
	Movie movie;
	Tape tape;
	
	@Before
	public void setUp() {
		movie = new Movie("grudge", 0);
		tape = new Tape("123", movie);
			
	}
	@Test
	public void testName() {
		assertEquals("123", tape.getSerial());
		assertEquals("grudge", tape.movie().name());
		assertEquals(0, tape.movie().priceCode());
	}
	
	@After
	public void testAfter() {
		System.out.println("all tests done");
	}
}
