package dmit2015.demo03;

public class Circle {
	
	/** The radius of this circle */
	private double radius = 1;

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Circle(double radius) {
		super();
		this.radius = radius;
	}

	public Circle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public double area() {
		return Math.PI * Math.pow(radius, 2); 
	}
	
	public double circumference() {
		return 2 * Math.PI * radius;
	}
	
}
