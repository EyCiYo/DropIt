<%@page import="dao.BookingDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/DropIt/Payment/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Archivo:ital,wght@0,100..900;1,100..900&display=swap"
	rel="stylesheet">
<title>Payment</title>
</head>
<body>
	<%
	String bid = (String) request.getAttribute("bookingid");
	out.println(bid);
	BookingDao bd = new BookingDao();
	double price = bd.getBookingPrice(bid);
	if(bid == null){
		RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");
		rd.forward(request, response);
	}
	%>
	<div class="payment-container">
		<div class="pay-left">
			<h2>
				Booking ID: <span id="book-id"><%=bid%></span>
			</h2>
			<h2>
				Amount Payable: <span id="amount"><%=price%></span>
			</h2>
			<div class="payment-methods">
				<input type="radio" name="payment-method" id="debit" value="debit"
					onchange="updateString(this.value)" checked><label
					for="debit">Debit Card</label> <input type="radio"
					name="payment-method" id="credit" value="credit"
					onchange="updateString(this.value)"><label for="credit">Credit
					Card</label>
			</div>
			<button id="payButton" onclick="showPaymentForm()">Pay</button>
			<a href="${pageContext.request.contextPath}/CancelPayment?bookingId=${bid}">
				<button id="goHome">Cancel Payment</button>
			</a>
		</div>
		<form class="card-form-container" id="cardFormContainer"
			action="/DropIt/MakePayment" method="post">
			<div class="card-form" id="cardForm">
				<h3 id="formTitle">Debit Card Payment</h3>
				<label for="cardNumber">Card Number:</label>
				<div class="cardnumber">
					<input type="text" class="num-inp" maxlength="4" name="card1"
						required>
					<div>-</div>
					<input type="text" class="num-inp" maxlength="4" name="card2"
						required>
					<div>-</div>
					<input type="text" class="num-inp" maxlength="4" name="card3"
						required>
					<div>-</div>
					<input type="text" class="num-inp" maxlength="4" name="card4"
						required>
				</div>

				<label for="cardName">Cardholder Name:</label> <input type="text"
					id="cardName" name="name" required> <label for="expiryDate">Expiry
					Date:</label> <input type="month" id="expiryDate" name="expiryDate"
					required> <label for="cvv">CVV:</label> <input type="text"
					id="cvv" maxlength="3" name="cvv" required>
				<div style="display: flex; gap: 1rem; margin-top: 2rem;">
					<button type="submit">Submit Payment</button>
					<button onclick="resetForm()">Reset</button>
				</div>
				<input type="hidden" value="<%=bid%>" name="bookingid">
			</div>
		</form>
		<%
			String err = (String)request.getAttribute("errorMessage");
			if(err != null){%>
				<script>alert('<%=err%>')</script>
		<%	}
		%>
	</div>



	<script src="${pageContext.request.contextPath}/Payment/script.js"></script>
</body>
</html>