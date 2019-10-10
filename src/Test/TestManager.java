package Test;
import org.junit.Test;
import mypackage.Manager;
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

public class TestManager {
	Manager manager;
	
	@Before
	public void setUp() {
		manager = new Manager();
	}
	
	@Test
	public void testGetCustomerName() {
		String name = "adam";
		InputStream stream = new ByteArrayInputStream(name.getBytes(StandardCharsets.UTF_8));
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String expectedName = manager.getCustomerName(reader);
		assertEquals(expectedName, name);
	}
	
	@Test
	public void testGetCustomerPhone() {
		String number = "1234567";
		InputStream stream = new ByteArrayInputStream(number.getBytes(StandardCharsets.UTF_8));
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String expectedNumber = manager.getCustomerPhone(reader);
		assertEquals(expectedNumber, number);
	}
	
	@Test
	public void testGetCustomerEmail() {
		String email = "adam@gmail.com";
		InputStream stream = new ByteArrayInputStream(email.getBytes(StandardCharsets.UTF_8));
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String expectedEmail = manager.getCustomerEmail(reader);
		assertEquals(expectedEmail, email);
	}
	
	@Test
	public void testGetMovieName() {
		String name = "Harry Poter";
		InputStream stream = new ByteArrayInputStream(name.getBytes(StandardCharsets.UTF_8));
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String expectedName = manager.getMovieName(reader);
		assertEquals(expectedName, name);
	}
	
	@Test
	public void testGetMovieSerialNumber() {
		String sn = "110";
		InputStream stream = new ByteArrayInputStream(sn.getBytes(StandardCharsets.UTF_8));
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String expectedSN = manager.getMovieSerialNumber(reader);
		assertEquals(expectedSN, sn);
	}
	
	@Test
	public void testGetMoviePriceCode() {
		String code = "0";
		InputStream stream = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		int expectedCode = manager.getMoviePriceCode(reader);
		assertEquals(expectedCode, Integer.parseInt(code));
	}
	
	
	@After
	public void testAfter() {
		
	}
	
	
}
