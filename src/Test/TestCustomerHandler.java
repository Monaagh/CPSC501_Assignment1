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
		
	@After
	public void testAfter() {
		
	}
	
	
}
