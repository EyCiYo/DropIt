<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/DropIt/user/Tracking/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Archivo:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    <title>Tracking</title>
</head>
<body>
    <jsp:include page="../../userheader.html"></jsp:include>
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
			username = (String)session.getAttribute("username");
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
		    
			<%}}}%>
			
			

    <!-- <h1>Tracking for user</h1> -->

    <main>
        <div class="tracking-bg">
            <div class="tracking-slogan">
                <h1>Book and Track Order</h1>
                <h2>Anywhere, Anytime</h2>
            </div>
            <div class="tracking-container">
                <div class="tracking-form">
                    <h1>Track your <span style="color: var(--primary-emp-text);">order</span></h1>
                    <form action="${pageContext.request.contextPath}/TrackParcel" name="track-form" method="post">
                        <div class="input-data">
                            <input type="text" name="tracking-id" id="tracking-id" placeholder="Enter your Tracking Id" value="<%=bid!=null?bid:"" %>" required>
                            <p style="color:red;margin-top:1rem;"><%=errorMessage!=null?errorMessage:"" %></p>
                        </div>
                        <div class="track-btn">
                            <button type="submit">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-geo-alt" viewBox="0 0 16 16">
                                    <path d="M12.166 8.94c-.524 1.062-1.234 2.12-1.96 3.07A32 32 0 0 1 8 14.58a32 32 0 0 1-2.206-2.57c-.726-.95-1.436-2.008-1.96-3.07C3.304 7.867 3 6.862 3 6a5 5 0 0 1 10 0c0 .862-.305 1.867-.834 2.94M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10"/>
                                    <path d="M8 8a2 2 0 1 1 0-4 2 2 0 0 1 0 4m0 1a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
                                </svg>&nbsp;
                                Track Parcel
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

    <jsp:include page="../../userfooter.html"></jsp:include>

    <script src="/DropIt/user/Tracking/script.js"></script>
</body>
</html>