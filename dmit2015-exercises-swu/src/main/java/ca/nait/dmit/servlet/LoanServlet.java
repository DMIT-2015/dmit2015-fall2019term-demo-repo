package ca.nait.dmit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.nait.dmit.domain.Loan;

/**
 * Servlet implementation class LoanServlet
 */
@WebServlet("/LoanServlet")
public class LoanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

//		JsonArrayBuilder loanScheduleBuilder = Json.createArrayBuilder();
//		for (LoanSchedule currentLoanSchedule : currentLoan.getMonthlyLoanSchedules()) {
//			JsonObject loanScheduleJson = Json.createObjectBuilder()
//					.add("paymentNumber", currentLoanSchedule.getPaymentNumber())
//					.add("interestPaid", currentLoanSchedule.getInterestPaid())
//					.add("principalPaid", currentLoanSchedule.getPrincipalPaid())
//					.add("remainingBalance", currentLoanSchedule.getRemainingBalance())
//					.build();		
//			loanScheduleBuilder.add(loanScheduleJson);
//		}
//		
//		JsonObject loanJson = Json.createObjectBuilder()
//				.add("mortgageAmount", currentLoan.getMortgageAmount())
//				.add("annualInterestRate", currentLoan.getAnnualInterestRate())
//				.add("amortizationPeriod", currentLoan.getAmortizationPeriod())
//				.add("monthlyPayment", currentLoan.getMonthlyPayment())
//				.add("loanSchedules", loanScheduleBuilder)
//				.build();
		
		Jsonb jsonb = JsonbBuilder.create();
		String loanJson = jsonb.toJson(currentLoan);
				
		out.println(loanJson.toString());
				
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
