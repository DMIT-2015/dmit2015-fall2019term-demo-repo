package dmit2015.jsf.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.el.functions.Numbers;
import org.omnifaces.util.Messages;

import dmit2015.model.Loan;

@Named
@ViewScoped
public class LoanController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Loan currentLoan = new Loan();	// +getter
	
	public void calculate() {
		Messages.addGlobalInfo("Your monthly payment is {0}", 
				Numbers.formatCurrency(currentLoan.monthlyPayment(),"$") );
	}

	public Loan getCurrentLoan() {
		return currentLoan;
	}
}