<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/DropIt/admin/DeliveryStatus/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link href="https://fonts.googleapis.com/css2?family=Archivo:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    <title>Delivery Status Update</title>
</head>
<body>
	<%
	session = request.getSession(false);
	String bookingid = (String)request.getAttribute("bookingid");
	String status = (String)request.getAttribute("status");
	String email = (String)request.getAttribute("email");
	String errorMessage = (String)request.getAttribute("errorMessage");
	if (session == null || session.getAttribute("username") == null) {
		response.sendRedirect("/DropIt/");
	} else if (session != null) {
		if (session.getAttribute("role").equals("user")) {
			response.sendRedirect(request.getContextPath() + "/user/Home/index.jsp");
		}
		if(errorMessage != null){%>
			<script>window.onload = function(){alert('<%=errorMessage%>');}</script>			
		<%}
		else{
			if(bookingid!=null && status != null){
		%>
			<script>window.onload = function(){showResult();}</script>
			<%bookingid = (String)request.getAttribute("bookingid");
			status = (String)request.getAttribute("status");
		}}
	}
	%>
    <jsp:include page="../../adminheader.html"></jsp:include>

    <main>
        <div class="tracking-bg">
            <div class="tracking-slogan">
                <h1>Update Parcel Status</h1>
                <h2>Seamless, Instant</h2>
            </div>
            <div class="tracking-container">
                <div class="tracking-form">
                    <h1>Enter order details</h1>
                    <form action="/DropIt/ChangeStatus" name="update-form">
                        <div class="input-data">
                            <input type="text" name="tracking-id" id="tracking-id" placeholder="Enter your Tracking Id" value="<%=bookingid!=null?bookingid:"" %>" required>
                            <input type="email" name="email" id="email" placeholder="Enter your Email Id" value="<%=email!=null?email:"" %>" required>
                        </div>
                        <div class="track-btn">
                            <button type="submit">
                                <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="currentColor"><path d="M440-82q-76-8-141.5-41.5t-114-87Q136-264 108-333T80-480q0-91 36.5-168T216-780h-96v-80h240v240h-80v-109q-55 44-87.5 108.5T160-480q0 123 80.5 212.5T440-163v81Zm-17-214L254-466l56-56 113 113 227-227 56 57-283 283Zm177 196v-240h80v109q55-45 87.5-109T800-480q0-123-80.5-212.5T520-797v-81q152 15 256 128t104 270q0 91-36.5 168T744-180h96v80H600Z"/></svg>&nbsp;
                                Update Delivery Details
                            </button> 
                        </div>
                    </form>
                </div>
                <!-- <div class="wr"></div> -->
            </div>
        </div>

        <form class="tracking-result" id="tracking-result" method="post" action="/DropIt/ChangeStatus">
            <div class="result-heading">
                <h1 style="color: rgb(20, 20, 20);">Order Status</h1>
                <h2 class="sub-text">Tracking ID: <span id="tracking-id-result"><%=bookingid!=null?bookingid:"" %></span></h2>
                <h2 class="sub-text">Current Status: <span id="status-result"><%=status!=null?status:"" %></span></h2>
                <div class="update-status">
                    <h2>Change Status: </h2>
                    <select name="status" id="status">
                        <option value="Booked">Booking Placed</option>
                        <option value="Picked Up">Picked Up</option>
                        <option value="In Transit">In Transit</option>
                        <option value="Out for Delivery">Out for Delivery</option>
                        <option value="Delivered">Delivered</option>                   
                    </select>
                </div>
            </div>
            <input type="hidden" value="<%=bookingid!=null?bookingid:"" %>" name="tracking-id">
            <div class="change-status-btn">
                <button type="submit">
                    <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="currentColor"><path d="M440-82q-76-8-141.5-41.5t-114-87Q136-264 108-333T80-480q0-91 36.5-168T216-780h-96v-80h240v240h-80v-109q-55 44-87.5 108.5T160-480q0 123 80.5 212.5T440-163v81Zm-17-214L254-466l56-56 113 113 227-227 56 57-283 283Zm177 196v-240h80v109q55-45 87.5-109T800-480q0-123-80.5-212.5T520-797v-81q152 15 256 128t104 270q0 91-36.5 168T744-180h96v80H600Z"/></svg>&nbsp;
                    Update Delivery Details
                </button> 
            </div>
        </form>
    </main>
  
    <jsp:include page="../../adminfooter.html"></jsp:include>

    <script src="/DropIt/admin/DeliveryStatus/script.js"></script>
</body>
</html>