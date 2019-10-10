package dmit2015.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanSchedule {

	// declare data fields
	private int paymentNumber;
	private double interestPaid;
	private double principalPaid;
	private double remainingBalance;
		
}
