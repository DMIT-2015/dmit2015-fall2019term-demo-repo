package ca.nait.demo;

import org.junit.*; // for @Test
import static org.junit.Assert.*; // for assertEquals() method.

public class UnitTest {
	@Test
	public void testPowerOperator() { // 2^3 should be 8
		//assertEquals(8, 2 ^ 3);
		assertEquals(8, Math.pow(2, 3), 0);
	}

	@Test
	public void testDivision() { // 4/5 should be 0.80
		assertEquals(0.80, 4.0 / 5, 0);
	}

	@Test
	public void testStringCompare() { // do a case-insensitive string compare
		assertTrue("dmit221".equalsIgnoreCase("DMIT221") );
	}

	@Test(expected = java.lang.ArithmeticException.class)
	public void testForException() { // this test method will succeed if a exception is ArithmeticException is thrown
		assertEquals(0, 3 / 0);
	}
}