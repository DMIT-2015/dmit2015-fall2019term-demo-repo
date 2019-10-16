package dmit2015.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.apache.commons.math3.util.Precision;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
	
	// must be >= 50000
	@DecimalMin(value = "50000", message = "Mortgage Amount must be at least ${value}")
	private double mortgageAmount;
	
	// must be > 0
	@DecimalMin(value = "0.01", message = "Annual Interest Rate must be at least ${value}")
	private double annualInterestRate;
	
	// must be between 1 and 25
	@Min(value = 1, message="Amortization Period must be between {value} and 25 years")
	@Max(value = 25, message="Amortization Period must be between 1 and {value} years")
	private int amortizationPeriod;
	
	public double monthlyPayment() {
		double monthlyPercentageRate = Math.pow(1 + annualInterestRate / 200, 1.0/6.0) ;		
		return Precision.round(
				mortgageAmount * (monthlyPercentageRate - 1) /
				(1 - Math.pow(monthlyPercentageRate, -12 * amortizationPeriod) )
				, 2);
	}
	
	public LoanSchedule[] loanScheduleTable() {
		int totalPayments = amortizationPeriod * 12;
		LoanSchedule[] schedule = new LoanSchedule[totalPayments];
		double monthlyPercentageRate = Math.pow(1 + annualInterestRate / 200, 1.0/6.0) - 1;
		double remainingBalance = mortgageAmount;
		double interestPaid;
		double principalPaid;
		double monthlyPaymentValue = monthlyPayment();
		for( int paymentNumber = 1; paymentNumber <= totalPayments; paymentNumber++ ) {
			LoanSchedule currentLoanSchedule = new LoanSchedule();
			currentLoanSchedule.setPaymentNumber(paymentNumber);
			
			interestPaid = Precision.round(monthlyPercentageRate * remainingBalance, 2);			
			currentLoanSchedule.setInterestPaid(interestPaid);
			
			if (remainingBalance < monthlyPaymentValue) {
				principalPaid = remainingBalance;
			} else {				
				principalPaid = Precision.round(monthlyPaymentValue - interestPaid, 2);
			}
			currentLoanSchedule.setPrincipalPaid(principalPaid);			
			
			remainingBalance = Precision.round( remainingBalance - principalPaid, 2);			
			currentLoanSchedule.setRemainingBalance(remainingBalance);
			
			int index = paymentNumber - 1;
			schedule[index] = currentLoanSchedule;			
		}
		return schedule;
	}
}
