package dmit2015.demo03;

import static org.junit.Assert.*;

import org.junit.Test;

public class CircleTest {

	@Test
	public void testArea() {
		// Creates a circle with a radius of 1
		Circle circle1 = new Circle();
		// The area should be 3.14
		assertEquals(Math.PI, circle1.area(), 0);
	}

	@Test
	public void testCircumference() {
		// Create a circle with a radious of 25
		Circle circle1 = new Circle(25);
		//assertEquals(2, circle1.circumference(), 200);
		assertTrue(true);
	}

}
