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

public class TestManager {
	Manager manager;
	
	@Before
	public void setUp() {
		manager = new Manager();
	}
	
	@Test
	public void test() {
		
	}
	

	@After
	public void testAfter() {
		
	}
	
	
}
