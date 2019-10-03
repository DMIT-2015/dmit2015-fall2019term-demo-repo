package ca.nait.dmit.domain;

import javax.validation.constraints.Positive;

public class Rectangle {

	@Positive(message = "Width must be a positive number")
	private double width;
	
	@Positive(message = "Length must be a positive number")
	private double length;
	
	public double area() {
		return width * length;
	}
	
	public double perimeter() {
		return 2 * (length + width);
	}
	
	public Rectangle() {
		super();
		width = 1;
		length = 1;
	}
	
	
	public Rectangle(double width, double length) {
		super();
		this.width = width;
		this.length = length;
	}


	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	
	
}
