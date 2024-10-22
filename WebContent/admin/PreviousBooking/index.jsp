<%@page import="bean.*"%>
<%@page import="dao.UserDao"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/DropIt/admin/PreviousBooking/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Archivo:ital,wght@0,100..900;1,100..900&display=swap"
	rel="stylesheet">
<title>Previous Bookings</title>
</head>
<body>
	<%
	session = request.getSession(false);
	String name = null;
	String email = (String)request.getAttribute("email");
	String mobile = null;
	String uid = null;
	String errorMessage = (String)request.getAttribute("errorMessage");
	
	ArrayList<Booking> al = new ArrayList<Booking>();
	UserDao ud = new UserDao();
	if (session == null || session.getAttribute("username") == null) {
		response.sendRedirect(request.getContextPath() + "/");
	} else if (session != null && session.getAttribute("role").equals("user")) {
		response.sendRedirect(request.getContextPath() + "/user/Home/index.jsp");
	}else{
		if(request.getAttribute("bookingList")!= null && request.getAttribute("email")!=null){
			try{
				al = (ArrayList<Booking>)request.getAttribute("bookingList");
				if(al!=null){
					User obj = ud.getUser(email);
	                name = obj.getName();
	                mobile = obj.getMobile();
	                uid = obj.getUserID();
				%>
					<script>
					   window.onload = function(){
						   getHistory();
					   }
					</script>
				<%}
				else{
					errorMessage = "No Records found for this user";
					out.println(errorMessage);
				}
			}
			catch(ClassCastException e){
				e.printStackTrace();
				throw new ServletException(e);
			}
			
		}
	}
	%>
	<jsp:include page="../../adminheader.html"></jsp:include>
	<main>
		<div class="tracking-bg">
			<div class="tracking-slogan">
				<h1>Search for History</h1>
				<h2>It's all connected</h2>
			</div>
			<div class="tracking-container">
				<div class="tracking-form">
					<h1>Enter user details</h1>
					<form action="/DropIt/PreviousBookings" name="update-form"
						method="post">
						<div class="input-data">
							<input type="email" name="email" id="email"
								placeholder="Enter your Email Id" required> <label
								for="fromdate">From Date:</label> <input type="date"
								name="fromdate" id="fromdate" placeholder="Enter start date"
								required> <label for="todate">To Date:</label> <input
								type="date" name="todate" id="todate"
								placeholder="Enter to date" disabled required>
								<p id="error" style="color:red"><%=errorMessage!=null?errorMessage:"" %></p>
						</div>
						<div class="track-btn">
							<button type="submit">
								<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
									fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                    <path
										d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0" />
                                </svg>
								&nbsp; Search History
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="previous-details" id="previous-details">
			<div class="user-details">
				<h3 class="hanging-text">User Details:</h3>
				<div style="display: flex; gap: 0.25rem;">
					<h4>User ID:</h4>
					<span id="uid"><%=uid %></span>
				</div>
				<div style="display: flex; gap: 0.25rem">
					<h4>Name:</h4>
					<span id="name"><%=name %></span>
				</div>
				<div style="display: flex; gap: 0.25rem">
					<h4>Email ID:</h4>
					<span id="email"><%=email %></span>
				</div>
				<div style="display: flex; gap: 0.25rem">
					<h4>Mobile:</h4>
					<span id="mobile"><%=mobile %></span>
				</div>
			</div>
			<h2>Previous Booking Details</h2>
			<div class="table-responsive">
			<%
		       if(al == null || al.size() == 0){
		           out.println("<h1>No Previous Bookings found for your account");
		       }
		       else{
		           out.println("<table><thead><tr><th>Booking ID</th><th>Booking Date</th><th>Receiver Name</th><th>Delivery Address</th><th>Amount</th><th>Delivery Date</th><th>Status</th></tr></thead><tbody>");
		           for(Booking bk:al){
		               out.println("<tr><td>"+bk.getBookingId()+"</td><td>"+bk.getBookingDate()+"</td><td>"+bk.getReceiverName()+"</td><td>"+bk.getReceiverAddress()+"</td><td>"+bk.getCost()+"</td><td>"+bk.getDropoffDate()+"</tb><td>"+bk.getStatus()+"</td></tr>");
		           }
		           out.println(" </tbody></table>");
		       }
		      %>   
			</div>
		</div>

	</main>



	<jsp:include page="../../adminfooter.html"></jsp:include>
	<script src="/DropIt/admin/PreviousBooking/script.js"></script>
</body>
</html>