package ca.nait.dmit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.nait.dmit.domain.Loan;
import ca.nait.dmit.domain.LoanSchedule;

/**
 * Servlet implementation class LoanOutputServlet
 */
@WebServlet("/LoanOutputServlet")
public class LoanOutputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String amountString = request.getParameter("amount");
		String interestRateString = request.getParameter("interestRate");
		String periodString = request.getParameter("period");
		
		double amount = Double.parseDouble(amountString);
		double interestRate = Double.parseDouble(interestRateString);
		int period = Integer.parseInt(periodString);
		
		Loan currentLoan = new Loan();
		currentLoan.setMortgageAmount(amount);
		currentLoan.setAnnualInterestRate(interestRate);
		currentLoan.setAmortizationPeriod(period);
		
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("en","CA"));
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<p>Your monthly payment amount is ");
		out.println("<span id='monthlyPayment'>" + currencyFormatter.format(currentLoan.monthlyPayment()) + "</span>");
		out.println("</p>");
		out.println("<table><thead><tr><th>Payment Number</th><th>Interest Paid</th><th>Principal Paid</th><th>Remaining Balance</th></tr></thead>");
		out.println("<tbody>");
		for (LoanSchedule currentLoanSchedule : currentLoan.loanScheduleTable()) {
			out.println("<tr>");
			out.println("<td>" + currentLoanSchedule.getPaymentNumber() + "</td>");
			out.println("<td>" + currencyFormatter.format(currentLoanSchedule.getInterestPaid()) + "</td>");
			out.println("<td>" + currencyFormatter.format(currentLoanSchedule.getPrincipalPaid()) + "</td>");
			out.println("<td>" + currencyFormatter.format(currentLoanSchedule.getRemainingBalance()) + "</td>");
			out.println("</td>");
		}
		out.println("</tbody>");
		out.println("</table>");
		
		out.close();
	}

}
