<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/DropIt/hometracking.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Archivo:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    <title>Tracking</title>
</head>
<body>
    <jsp:include page="./homeheader.html"></jsp:include>
	<%
		String bid = (String)request.getAttribute("bookingid");
		String deliverydate = (String)request.getAttribute("deliverydate");
		String status = (String)request.getAttribute("status");
		String bookingdate = (String)request.getAttribute("bookingdate");
		String errorMessage = (String)request.getAttribute("errorMessage");
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
			<%
                    	String err = (String)request.getAttribute("errorMessage");
                    	if(err!=null){%>
                    	<script>alert("<%=err %>")</script>
             <%} }
             %>
			

    <!-- <h1>Tracking for user</h1> -->

    <main>
        <div class="tracking-result" id="tracking-result">
            <div class="result-heading">
                <h1 style="color: rgb(20, 20, 20);">Order Status</h1>
                <h2 class="sub-text">Tracking ID: <%=bid!=null?bid:"" %></h2>
            </div>
            <div class="progress-bar">
                <div style="display: flex;width: 100%;justify-content: center;">
                    <span class="circle">
                        <div class="progress-marker text-overflow">Booking Placed</div>
                    </span>
                    <span class="bar"></span>
                    <span class="circle">
                        <div class="progress-marker text-overflow">Picked Up</div>
                    </span>
                    <span class="bar"></span>
                    <span class="circle">
                        <div class="progress-marker text-overflow">In Transit</div>
                    </span>
                    <span class="bar"></span>
                    <span class="circle">
                        <div class="progress-marker text-overflow">Out for Delivery</div>
                    </span>
                    <span class="bar"></span>
                    <span class="circle">
                        <div class="progress-marker text-overflow">Delivered</div>
                    </span>
                </div>
            </div>
            <div class="tracking-info">
                <p class="sub-text"><b>Booking Date:</b> <span id="booking-date"><%=bookingdate!=null?bookingdate:"" %></span></p>
                <p class="sub-text"><b>Estimated Arrival:</b> <span id="estimated-arrival"><%=deliverydate!=null?deliverydate:"" %></span></p>
        	</div>
        </div>
    </main>

    <jsp:include page="./userfooter.html"></jsp:include>

    <script src="/DropIt/hometracking.js"></script>
</body>
</html>