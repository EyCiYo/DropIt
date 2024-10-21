<%@page import="dao.BookingDao"%>
<%@page import="bean.Booking"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/DropIt/admin/Tracking/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Archivo:ital,wght@0,100..900;1,100..900&display=swap"
	rel="stylesheet">
<title>Tracking</title>
</head>
<body>
	<%
        session = request.getSession(false);
        String username = null;
        String bid = null;
        String deliverydate = null;
        String bookingdate = null;
        String status = null;
        String errorMessage = null;
        if(session == null || session.getAttribute("username") == null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher(request.getContextPath()+"/");
            rd.forward(request, response);
        }
        else if (session!=null && session.getAttribute("role").equals("admin")){
            response.sendRedirect(request.getContextPath()+"/admin/Home/index.jsp");
        }
        else{
            username = (String)request.getAttribute("username");
            bid = (String)request.getAttribute("bookingid");
            deliverydate = (String)request.getAttribute("deliverydate");
            status = (String)request.getAttribute("status");
            bookingdate = (String)request.getAttribute("bookingdate");
            errorMessage = (String)request.getAttribute("errorMessage");
            Map<String,String> progress = Map.of(
                    "Booked","1",
                    "Picked Up","2",
                    "In Transit","3",
                    "Out for Delivery","4",
                    "Delivered","5");
            if(errorMessage == null){
                if(bid!=null && deliverydate != null && status != null){
                    %>
                <script>
                    window.onload = function(){
                        startProgressBar(<%=progress.get(status)%>);
                    }
                </script>
            
            <%}}
            else{%>
            	<script>
                    window.onload = function(){
                        document.getElementById('error').innerHTML = errorMessage;
                    }
                </script>
            <%}
        }%>
	<jsp:include page="../../adminheader.html"></jsp:include>

	<main>
		<div class="tracking-bg">
			<div class="tracking-slogan">
				<h1>Book and Track Order</h1>
				<h2>Anywhere, Anytime</h2>
			</div>
			<div class="tracking-container">
				<div class="tracking-form">
					<h1>
						Track your <span style="color: var(--dark-blue);">order</span>
					</h1>
					<form action="../../HomeTrack" name="track-form">
						<div class="input-data">
							<input type="text" name="tracking-id"
								placeholder="Enter your Tracking Id"
								value="<%=request.getAttribute("tracking-id")!=null?request.getAttribute("tracking-id"):"" %>" required>
							<input
								type="email" name="email" id="email"
								placeholder="Enter your Email Id" 
								value="<%=request.getAttribute("email")!=null?request.getAttribute("email"):"" %>" required>
							<div class="underline"></div>
						</div><br>
						<p id="error" style="color:red;"></p>
						<div class="track-btn">
							<button type="submit">
								<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
									fill="currentColor" class="bi bi-geo-alt" viewBox="0 0 16 16">
                                    <path
										d="M12.166 8.94c-.524 1.062-1.234 2.12-1.96 3.07A32 32 0 0 1 8 14.58a32 32 0 0 1-2.206-2.57c-.726-.95-1.436-2.008-1.96-3.07C3.304 7.867 3 6.862 3 6a5 5 0 0 1 10 0c0 .862-.305 1.867-.834 2.94M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10" />
                                    <path
										d="M8 8a2 2 0 1 1 0-4 2 2 0 0 1 0 4m0 1a3 3 0 1 0 0-6 3 3 0 0 0 0 6" />
                                </svg>
								&nbsp; <span class="text-overflow">Track Parcel</span>
							</button>
							<button type="button" onclick="showAll()">
								<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
									fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">
                                    <path
										d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8M1.173 8a13 13 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5s3.879 1.168 5.168 2.457A13 13 0 0 1 14.828 8q-.086.13-.195.288c-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5s-3.879-1.168-5.168-2.457A13 13 0 0 1 1.172 8z" />
                                    <path
										d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5M4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0" />
                                </svg>
								&nbsp; Show All
							</button>
						</div>
					</form>
				</div>
				<!-- <div class="wr"></div> -->
			</div>
		</div>
		<div class="tracking-result" id="tracking-result">
			<div class="result-heading">
				<h1 style="color: rgb(20, 20, 20);">Order Status</h1>
				<h2 class="sub-text">
					Tracking ID: <span id="tracking-id"><%=bid %></span>
				</h2>
				<h2 class="sub-text">
					User ID: <span id="user-id"><%=username %></span>
				</h2>
			</div>
			<div class="progress-bar">
				<div style="display: flex; width: 100%; justify-content: center;">
					<span class="circle">
						<div class="progress-marker text-overflow">Booking Placed</div>
					</span> <span class="bar"></span> <span class="circle">
						<div class="progress-marker text-overflow">Picked Up</div>
					</span> <span class="bar"></span> <span class="circle">
						<div class="progress-marker text-overflow">In Transit</div>
					</span> <span class="bar"></span> <span class="circle">
						<div class="progress-marker text-overflow">Out for Delivery</div>
					</span> <span class="bar"></span> <span class="circle">
						<div class="progress-marker text-overflow">Delivered</div>
					</span>
				</div>
			</div>
			<div class="tracking-info">
				<p class="sub-text">
					<b>Booking Date:</b> <span id="booking-date"><%=bookingdate %></span>
				</p>
				<p class="sub-text">
					<b>Estimated Arrival:</b> <span id="estimated-arrival"><%=bookingdate %></span>
				</p>
			</div>
		</div>
		<div class="previous-details" id="previous-details">
		  <div class="table-responsive">
		  <%
			  ArrayList<Booking> al = new ArrayList<Booking>();
	          BookingDao bd = new BookingDao();
	          al = bd.getAllBooking();
	          if(al == null || al.size() == 0){
	        	  out.println("<p>No Records Found</p>");
	          }
	          else {
	        	  out.write("<table><thead><tr><th>Booking ID</th><th>Sender Name</th><th>Sender Email</th><th>Booking Date</th><th>Receiver Name</th><th>Delivery Address</th><th>Amount</th><th>Status</th></tr></thead><tbody>");
	        	  for(Booking bk:al){
	                   out.println("<tr><td>"+bk.getBookingId()+"</td><td>"+bk.getSenderName()+"</td><td>"+bk.getSenderEmail()+"</td><td>"+bk.getBookingDate()+"</td><td>"+bk.getReceiverName()+"</td><td>"+bk.getReceiverAddress()+"</td><td>"+bk.getCost()+"</tb><td>"+bk.getStatus()+"</td></tr>");
	               }
	        	  out.write("</tbody></table>");
	          }
		  %>
			</div>
		</div>
	</main>

	<jsp:include page="../../adminfooter.html"></jsp:include>
	<script src="/DropIt/admin/Tracking/"></script>
</body>
</html>