package Test;
import org.junit.Test;
import mypackage.*;
import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

public class TestCustomerHandler {
	CustomerHandler chandler;
	
	@Before
	public void setUp() {
		chandler = new CustomerHandler();
	}
	
	@Test
	public void testGetCustomerName() {
		String name = "adam";
		InputStream stream = new ByteArrayInputStream(name.getBytes(StandardCharsets.UTF_8));
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String expectedName = chandler.getCustomerName(reader);
		assertEquals(expectedName, name);
	}
	
	@Test
	public void testGetCustomerPhone() {
		String number = "1234567";
		InputStream stream = new ByteArrayInputStream(number.getBytes(StandardCharsets.UTF_8));
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String expectedNumber = chandler.getCustomerPhone(reader);
		assertEquals(expectedNumber, number);
	}
	
	@Test
	public void testGetCustomerEmail() {
		String email = "adam@gmail.com";
		InputStream stream = new ByteArrayInputStream(email.getBytes(StandardCharsets.UTF_8));
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String expectedEmail = chandler.getCustomerEmail(reader);
		assertEquals(expectedEmail, email);
	}
	
	@Test
	public void testSearchCustomer() throws IOException {
		String line = "adam,1234567,adam@gmail.com";
		String name = "adam";
		InputStream stream = new ByteArrayInputStream(line.getBytes(StandardCharsets.UTF_8));
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		Customer expectedCustomer = chandler.searchCustomer(name, reader);
		assertTrue(expectedCustomer instanceof Customer);
		assertEquals(expectedCustomer.name(), name);
	}
	
	@Test
	public void testSearchCustomerNull() throws IOException {
		String line = " ";
		String name = "adam";
		InputStream stream = new ByteArrayInputStream(line.getBytes(StandardCharsets.UTF_8));
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		Customer expectedCustomer = chandler.searchCustomer(name, reader);
		assertNull(expectedCustomer);
	}
		
	@After
	public void testAfter() {
		
	}
	
	
}
