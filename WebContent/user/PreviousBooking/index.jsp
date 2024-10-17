<%@page import="bean.User"%>
<%@page import="dao.UserDao"%>
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
    <link rel="stylesheet" href="style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Archivo:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    <title>Previous Booking</title>
</head>
<body>
	
    <jsp:include page="../../userheader.html"></jsp:include>
    <main>
        <div class="user-details">
            <h3 class="hanging-text">User Details:</h3>
            <%
		    	session = request.getSession(false);
		    	String name = null;
		    	String email = null;
		    	String mobile = null;
		    	String uid = null;
		    	if(session == null || session.getAttribute("username") == null){
		    		response.sendRedirect(request.getContextPath()+"/");
		    	}
		    	else if (session!=null && session.getAttribute("role").equals("admin")){
			    	response.sendRedirect(request.getContextPath()+"/admin/Home/index.jsp");
			    }
		    	else{
		    		email = (String)session.getAttribute("username");
		    		UserDao ud = new UserDao();
		    		User obj = ud.getUser(email);
		    		name = obj.getName();
		    		mobile = obj.getMobile();
		    		uid = obj.getUserID();
		    	}
		    %>
            <div style="display: flex; gap:0.25rem">
                <h4>User ID:</h4><span id="uid"><%= uid %></span>
            </div>
            <div style="display: flex; gap:0.25rem">
                <h4>Name:</h4><span id="name"><%= name %></span>
            </div>
            <div style="display: flex; gap:0.25rem">
                <h4>Email ID:</h4><span id="email"><%=email%></span>
            </div>
            <div style="display: flex; gap:0.25rem">
                <h4>Mobile:</h4><span id="mobile"><%=mobile %></span>
            </div>
        </div>
        <h2>Previous Booking Details</h2>
        <div class="table-responsive">
        
        <%
    	ArrayList<Booking> al = new ArrayList<>();
    	BookingDao bd = new BookingDao();
    	al = bd.getPreviousBooking(email);
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
    </main>

    <jsp:include page="../../userfooter.html"></jsp:include>

    <script src="${pageContext.request.contextPath}/user/Login/script.js"></script>
</body>
</html>

    
    

    