package Test;

import static org.junit.Assert.assertEquals;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.junit.Before;
import org.junit.Test;
import mypackage.*;

public class TestMovieHandler {
	MovieHandler mhandler;
	
	@Before
	public void setUp() {
		 mhandler = new MovieHandler();
	}
	
	@Test
	public void testGetMovieName() {
		String name = "Harry Poter";
		InputStream stream = new ByteArrayInputStream(name.getBytes(StandardCharsets.UTF_8));
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String expectedName = mhandler.getMovieName(reader);
		assertEquals(expectedName, name);
	}
	
	@Test
	public void testGetMovieSerialNumber() {
		String sn = "110";
		InputStream stream = new ByteArrayInputStream(sn.getBytes(StandardCharsets.UTF_8));
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String expectedSN = mhandler.getMovieSerialNumber(reader);
		assertEquals(expectedSN, sn);
	}
	
	@Test
	public void testGetMoviePriceCode() {
		String code = "0";
		InputStream stream = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		int expectedCode = mhandler.getMoviePriceCode(reader);
		assertEquals(expectedCode, Integer.parseInt(code));
	}
}
