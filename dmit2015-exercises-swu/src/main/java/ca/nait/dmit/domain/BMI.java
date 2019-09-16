package ca.nait.dmit.domain;

import org.apache.commons.math3.util.Precision;

/**
 * This class is use to calculate a person's body mass index (BMI) and their BMI Category.
 * @author Sam Wu
 * @version 2019.09.12
 */
public class BMI {
	
	private int weight;
	private int height;
	
	/**
	 * Calculate the body mass index (BMI) using the weight and height of the person.
	 * The BMI of a person is calculated using the formula: BMI = 700 * weight / (height * height)
	 * where weight is in pounds and height is in inches.
	 * @return the body mass index (BMI) value of the person
	 */
	public double bmi() {
		return Precision.round( 703 * weight / Math.pow(height, 2), 1);
	}
	
	/**
	 * Determines the BMI Category of the person using their BMI value and
	 * comparing it against the following table.
	 * <table>
	 * <thead>
	 * <tr>
	 * <th>BMI Range</th>
	 * <th>BMI Category</th>
	 * </tr>
	 * </thead>
	 * <tbody>
	 * <tr>
	 * <td>< 18.5</td>
	 * <td>underweight</td>
	 * </tr>
	 * <tr>
	 * <td>>= 18.5 and < 25</td>
	 * <td>normal</td>
	 * </tr>
	 * <tr>
	 * <td>>= 25 and < 30</td>
	 * <td>overweight</td>
	 * </tr>
	 * <tr>
	 * <td>>= 30</td>
	 * <td>obese</td>
	 * </tr>
	 * </tbody>
	 * </table>
	 *
	 * @return one of following: underweight, normal, overweight, obese.
	 */
	public String bmiCategory() {
		double bmiValue = bmi();
		if (bmiValue < 18.5)
			return "underweight";
		else if (bmiValue < 25) 
			return "normal";
		else if (bmiValue < 30)
			return "overweight";
		else 
			return "obese";
	}
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public BMI() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BMI(int weight, int height) {
		super();
		this.weight = weight;
		this.height = height;
	}
	
	

}
