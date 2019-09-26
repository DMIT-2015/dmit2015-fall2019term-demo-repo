<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.text.NumberFormat, ca.nait.dmit.domain.Loan, ca.nait.dmit.domain.LoanSchedule"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>Mortgage Calculator Results</title>
</head>
<body>

	<div class="container">

		<div class="jumbotron">
			<h1>Mortgage Calculator Results</h1>
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
		

	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
		integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
		crossorigin="anonymous"></script>

</body>
</html>