<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.text.NumberFormat, ca.nait.dmit.domain.Loan, ca.nait.dmit.domain.LoanSchedule"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
    <head>
        <title>Income Tax Form</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        <!-- Bootstrap CSS -->
    	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
         
        <title>Mortgage Payment Calculator</title>
    </head>
    <body>
    	<div class="container">
    
    	<div class="jumbotron">
	    	<h1>Mortgage Payment Calculator Form</h1>
    	</div>
    	
    	<c:if test="${errorMessage != null}">
			<div class="error">
				<h3>${errorMessage}</h3>
			</div>
		</c:if>
		
		<c:if test="${currentLoan != null}">
			<div class="alert alert-info" role="alert">
				The monthly payment amount is
				<strong><fmt:formatNumber value="${currentLoan.monthlyPayment()}" type="currency" /></strong>			
				for a mortgage amount of
				<strong><fmt:formatNumber value="${currentLoan.mortgageAmount}" type="currency" /></strong> 
				at an annual interest rate of
				<strong>${currentLoan.annualInterestRate}%</strong>
				for a period of <strong>${currentLoan.amortizationPeriod}</strong> years.
			
			</div>
			
			<table class="table table-striped table-hover table-sm">
		    	<thead>
		    		<tr>
		    			<th>Payment Number</th>
		    			<th>Interest Paid</th>
		    			<th>Principal Paid</th>
		    			<th>Remaining Balance</th>
		    		</tr>
		    	</thead>
		    	<tbody>
		    		<c:forEach items="${currentLoan.loanScheduleTable()}" var="loanSchedule">
			    		<tr >
					    	<td >${loanSchedule.paymentNumber}</td>
					        <td ><fmt:formatNumber value="${loanSchedule.interestPaid}" type="currency" /></td>
					        <td ><fmt:formatNumber value="${loanSchedule.principalPaid}" type="currency" /></td>
					        <td ><fmt:formatNumber value="${loanSchedule.remainingBalance}" type="currency" /></td>
					    </tr>					
					</c:forEach>
		    		
		    	</tbody>
		    </table>
		</c:if>
    	
    	<form method="post" action="LoanRedirectServlet" >
    		<div class="form-group">
    			<label for="amount">Mortgage Amount</label>
    			<div class="input-group mb-2">
    				<div class="input-group-prepend">
		         		<div class="input-group-text">$</div>
		         	</div>
			    	<input type="number" 
			    		class="form-control" 
			    		id="amount" 
			    		name="amount" 
			    		placeholder="Enter mortgage amount"
			    	>
			    </div>
    		</div>
    		<div class="form-group">
    			<label for="interestRate">Annual Interest Rate (as a percentage of 100)</label>
    			<div class="input-group mb-2">
				    <input class="form-control" 
				    	id="interestRate" 
				    	name="interestRate" 
				    	placeholder="Enter annual interest rate"
				    >
    				<div class="input-group-append">
		         		<div class="input-group-text">%</div>
		         	</div>
				 </div>
    		</div>
    		<div class="form-group">
    			<label for="period">Amortization Period</label>
			    <input class="form-control" 
			    	type="number"
			    	min="1"
			    	max="25"
			    	id="period" 
			    	name="period" 
			    	placeholder="Enter amortization period"
			    >
    		</div>
    					  
    		<button type="submit" class="btn btn-primary">Calculate</button>
    	</form>
    
    	</div>

		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    </body>
</html>