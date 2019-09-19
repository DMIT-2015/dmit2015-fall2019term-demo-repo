package ca.nait.dmit.domain;

import org.apache.commons.math3.util.Precision;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
	
	private double mortgageAmount;
	private double annualInterestRate;
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
