<%@page import="bean.User"%>
<%@page import="dao.UserDao"%>
<%@page import="dao.BookingDao"%>
<%@page import="bean.Booking"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="/DropIt/Bill/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Archivo:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    <title>Bill</title>
</head>
<body>
	<%
		String bookingid = (String)session.getAttribute("bookingid");
		Booking obj = new Booking();
		User user = new User();
		BookingDao bd = new BookingDao();
		UserDao ud = new UserDao();
		String billNo = (String)request.getAttribute("billNo");
	
/* 		out.println(bookingid+billNo); */
		if(bookingid != null){
/* 			out.println("<h1>Found booking</h1>"); */
			obj = bd.getBookingDetails(bookingid);
		}
		else{
			out.println("<script>alert('Bill not available.')</script>");
			RequestDispatcher rd = request.getRequestDispatcher("../index.jsp");
			rd.forward(request, response);
		} 
	%>
    <div class="bill">
    <form action="./PrintBill" method="post">
        <h1>Bill Details</h1>
        <input type="hidden" value="<%=bookingid %>" name="bookingid">
        <input type="hidden" value="<%=billNo %>" name="billno">
        <div style="display:flex;">
            <h3>Bill No:&nbsp; </h3><span id="bill-no"><%=billNo %></span><br>
        </div>
        <div style="display:flex;">
            <h4>Sender Name:&nbsp; </h4><span id="sender-name"><%=obj.getSenderName() %></span>
        </div>
        <div style="display:flex;">
            <h4>Sender Mobile No:&nbsp; </h4><span id="sender-mobile"><%=obj.getSenderMobile() %></span>
        </div>
        <div style="display:flex;">
            <h4>Sender Email:&nbsp; </h4><span id="email"><%=obj.getSenderEmail() %></span>
        </div>
        <div style="display:flex;">
            <h4>Booking ID:&nbsp; </h4><span id="booking-no"><%=obj.getBookingId() %></span>
        </div>
        <div style="display:flex;">
            <h4>Receiver Name:&nbsp; </h4><span id="rec-name"><%=obj.getReceiverName() %></span>
        </div>
        <div style="display:flex;">
            <h4>Bill Amount:&nbsp; </h4><span id="booking-cost"><%=obj.getCost() %></span>
        </div>
	        <button type="submit">
	            Print
	        </button>
        </form>
        
    </div>
    <div class="home" onclick="goHome()">
        <svg xmlns="http://www.w3.org/2000/svg" width="64" height="64" fill="currentColor" class="bi bi-house" viewBox="0 0 16 16">
            <path d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L2 8.207V13.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V8.207l.646.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293zM13 7.207V13.5a.5.5 0 0 1-.5.5h-9a.5.5 0 0 1-.5-.5V7.207l5-5z"/>
        </svg>
        Go Home
    </div>
    <script src="/DropIt/Bill/script.js"></script>
</body>
</html>