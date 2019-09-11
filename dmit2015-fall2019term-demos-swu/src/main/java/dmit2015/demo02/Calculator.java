package dmit2015.demo02;

import javax.swing.JOptionPane;

public class Calculator {

	public static void main(String[] args) {
		// Prompt for math expression (ex. 3 + 5)
		String mathExpression = JOptionPane.showInputDialog(
				"Enter a math expression (ex. 3 + 5)");
		
		// Extract operand1, operator, operand2 from mathExpression
		// Assume operand1 and operand2 are a single digit number		
		mathExpression = mathExpression.replaceAll(" ", "");
		int operand1 = Character.getNumericValue( mathExpression.charAt(0) );
		char operator = mathExpression.charAt(1);
		int operand2 = Character.getNumericValue( mathExpression.charAt(2) );
		
		// The result of the operation
		int result = 0;
		
		// Determine which operation to perform
		// (+ for addition, - for subtraction, . for multiplication, / for division)
		// and calculate the result
		switch(operator) {
		case '+':
			result = operand1 + operand2;
			break;
		case '-':
			result = operand1 - operand2;
			break;
		case '.':
			result = operand1 * operand2;
			break;
		case '/':
			result = operand1 / operand2;
			break;
		default:
			System.out.println(operator + " is not a valid operator");
			System.exit(1);
		}
		
		// Display the result in the format: operand1 operator operand2 = result
		// Display result using String Formatting
		String message = String.format("%d %s %d = %d", 
				operand1, operator, operand2, result);
		JOptionPane.showMessageDialog(null, message);
				

	}

}
