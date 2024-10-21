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
    <title>Delivery Status Update</title>
</head>
<body>
	<%
	session = request.getSession(false);
	if (session == null || session.getAttribute("username") == null) {
		response.sendRedirect(request.getContextPath() + "/");
	} else if (session != null) {
		if (session.getAttribute("role").equals("user")) {

			response.sendRedirect(request.getContextPath() + "/user/Home/index.jsp");
		}
	}
	%>
    <jsp:include page="../../adminheader.html"></jsp:include>

    <main>
        <div class="tracking-bg">
            <div class="tracking-slogan">
                <h1>Schedule Pick Up</h1>
                <h2>At the doorsteps</h2>
            </div>
            <div class="tracking-container">
                <div class="tracking-form">
                    <h1>Enter order details</h1>
                    <form action="" name="schedule-form" onsubmit="getParcel()">
                        <div class="input-data">
                            <input type="text" name="track-id" id="track-id" placeholder="Enter your Booking Id" required>
                        </div>
                        <div class="track-btn">
                            <button type="submit">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-building-check" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m1.679-4.493-1.335 2.226a.75.75 0 0 1-1.174.144l-.774-.773a.5.5 0 0 1 .708-.708l.547.548 1.17-1.951a.5.5 0 1 1 .858.514"/>
                                    <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                    <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                </svg>&nbsp;
                                Schedule Pickup
                            </button> 
                        </div>
                    </form>
                </div>
            </div>
        </div>   
        
        <div class="schedule-container" id="schedule-container">
            <div class="result-heading">
                <h1 style="color: rgb(20, 20, 20);">Order Status</h1>
                <h2 class="sub-text">Tracking ID: <span id="tracking-id-result"></span></h2>
                <h2 class="sub-text"><span id="title">User Preference: </span><span id="preference-result"></span></h2>
                <div class="update-status">
                    <h2>Pick Up Date: </h2>
                    <input type="datetime-local" name="pickup-time" id="pickup-time" placeholder="Enter Pickup Time" class="dateselect" required>
                </div>
            </div>
            <div class="change-status-btn">
                <button type="button" onclick="scheduleParcel()">
                    <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="currentColor"><path d="M440-82q-76-8-141.5-41.5t-114-87Q136-264 108-333T80-480q0-91 36.5-168T216-780h-96v-80h240v240h-80v-109q-55 44-87.5 108.5T160-480q0 123 80.5 212.5T440-163v81Zm-17-214L254-466l56-56 113 113 227-227 56 57-283 283Zm177 196v-240h80v109q55-45 87.5-109T800-480q0-123-80.5-212.5T520-797v-81q152 15 256 128t104 270q0 91-36.5 168T744-180h96v80H600Z"/></svg>&nbsp;
                    Update Details
                </button> 
            </div>
        </div>
    </main>
    <jsp:include page="../../adminfooter.html"></jsp:include>

    <script src="script.js"></script>
</body>
</html>