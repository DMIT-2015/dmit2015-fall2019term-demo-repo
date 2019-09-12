package ca.nait.dmit.domain;

public class Rectangle {

	private double width;
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
