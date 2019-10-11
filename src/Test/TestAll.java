package Test;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import org.junit.runner.RunWith;

@RunWith(Suite.class)
@SuiteClasses({TestMovie.class, TestCustomer.class, TestCustomerHandler.class, TestMovieHandler.class, TestManager.class,
	TestRental.class, TestTape.class})
public class TestAll{
	
}

