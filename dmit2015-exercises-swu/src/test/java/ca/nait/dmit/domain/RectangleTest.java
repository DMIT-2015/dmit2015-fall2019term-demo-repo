package ca.nait.dmit.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class RectangleTest {

	@Test
	public void testArea() {
		// Create a rectangle with width of 4 and length of 40
		Rectangle rectangle1 = new Rectangle(4, 40);
		assertEquals(4, rectangle1.getWidth(), 0);
		assertEquals(40, rectangle1.getLength(), 0);
		assertEquals(160, rectangle1.area(), 0);
		Rectangle rectangle2 = new Rectangle();
		rectangle2.setWidth(3.5);
		rectangle2.setLength(35.9);
		assertEquals(125.65, rectangle2.area(), 0.005);
	}

	@Test
	public void testPerimeter() {
		Rectangle rectangle1 = new Rectangle(4, 40);
		assertEquals(88, rectangle1.perimeter(), 0);
		Rectangle rectangle2 = new Rectangle(3.5, 35.9);
		assertEquals(78.8, rectangle2.perimeter(), 0);
	}

}
