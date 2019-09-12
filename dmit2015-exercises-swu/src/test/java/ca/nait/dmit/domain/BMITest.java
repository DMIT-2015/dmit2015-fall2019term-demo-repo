package ca.nait.dmit.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class BMITest {

	@Test
	public void testUnderweight() {
		BMI bmi1 = new BMI(100,66);
		assertEquals(16.1, bmi1.bmi(), 0);
		assertEquals("underweight", bmi1.bmiCategory());
	}
	
	@Test
	public void testNormal() {
		BMI bmi1 = new BMI(140,66);
		assertEquals(22.6, bmi1.bmi(), 0);
		assertEquals("normal", bmi1.bmiCategory());
	}

	@Test
	public void testOverweight() {
		BMI bmi1 = new BMI(175,66);
		assertEquals(28.2, bmi1.bmi(), 0);
		assertEquals("overweight", bmi1.bmiCategory());
	}
	
	@Test
	public void testObese() {
		BMI bmi1 = new BMI(200,66);
		assertEquals(32.3, bmi1.bmi(), 0);
		assertEquals("obese", bmi1.bmiCategory());
	}
}
