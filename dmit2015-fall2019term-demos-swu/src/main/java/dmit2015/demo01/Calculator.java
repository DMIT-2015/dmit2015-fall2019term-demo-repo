package dmit2015.demo01;

public class Calculator {

	public static void main(String[] args) {
		// Check if the number of args passed in 
		// Exit program if number of args is not exactly 3
		if (args.length != 3) {
			System.out.println("Usage: Calculate operand1 operator operand2");
			System.exit(1);
		}
		
		int operand1 = Integer.parseInt(args[0]);
		char operator = args[1].charAt(0);
		int operand2 = Integer.parseInt(args[2]);
		
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
		// Display result using String Concatenation
		System.out.println(operand1 + " " + operator + " " + operand2
				+ " = " + result);
		// Display result using String Formatting
		String message = String.format("%d %s %d = %d", 
				operand1, operator, operand2, result);
		
		System.out.println(message);
		
		
		

	}

}
