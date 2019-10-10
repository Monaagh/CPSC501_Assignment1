package Test;
import org.junit.Test;
import static org.junit.Assert.*;

import mypackage.*;

public class TestMovie {
	
	@Test
	public void testName() {
		Movie movie = new Movie("Lord of the Rings", 0);
		assertEquals(0, movie.priceCode());
		assertEquals("Lord of the Rings", movie.name());
	}
}
