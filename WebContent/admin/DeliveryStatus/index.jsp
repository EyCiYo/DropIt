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
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
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
                <h1>Update Parcel Status</h1>
                <h2>Seamless, Instant</h2>
            </div>
            <div class="tracking-container">
                <div class="tracking-form">
                    <h1>Enter order details</h1>
                    <form action="" name="update-form" onsubmit="getParcel()">
                        <div class="input-data">
                            <input type="text" name="tracking-id" id="tracking-id" placeholder="Enter your Tracking Id" required>
                            <input type="number" name="mobile-no" id="mobile-no" placeholder="Enter your Mobile No." required>
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

        <div class="tracking-result" id="tracking-result">
            <div class="result-heading">
                <h1 style="color: rgb(20, 20, 20);">Order Status</h1>
                <h2 class="sub-text">Tracking ID: <span id="tracking-id-result"></span></h2>
                <h2 class="sub-text">Current Status: <span id="status-result"></span></h2>
                <div class="update-status">
                    <h2>Change Status: </h2>
                    <select name="status" id="status">
                        <option value="booking-placed">Booking Placed</option>
                        <option value="picked-up">Picked Up</option>
                        <option value="in-transit">In Transit</option>
                        <option value="delivery">Out for Delivery</option>
                        <option value="delivered">Delivered</option>
                        <option value="returned">Returned</option>
                    </select>
                </div>
            </div>
            <div class="change-status-btn">
                <button type="button" onclick="updateParcel()">
                    <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="currentColor"><path d="M440-82q-76-8-141.5-41.5t-114-87Q136-264 108-333T80-480q0-91 36.5-168T216-780h-96v-80h240v240h-80v-109q-55 44-87.5 108.5T160-480q0 123 80.5 212.5T440-163v81Zm-17-214L254-466l56-56 113 113 227-227 56 57-283 283Zm177 196v-240h80v109q55-45 87.5-109T800-480q0-123-80.5-212.5T520-797v-81q152 15 256 128t104 270q0 91-36.5 168T744-180h96v80H600Z"/></svg>&nbsp;
                    Update Delivery Details
                </button> 
            </div>
        </div>
    </main>
  
    <jsp:include page="../../adminfooter.html"></jsp:include>

    <script src="script.js"></script>
</body>
</html>