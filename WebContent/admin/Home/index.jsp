<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DropIt - Admin</title>
    <link rel="stylesheet" href="style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Archivo:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
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
                <h1>Track, Manage, Deliver</h1>
                <h2>All in One Place!</h2>
            </div>
            <div class="tracking-container">
                <div class="tracking-form">
                    <h1>Track your <span style="color: var(--primary-emp-text);">order</span></h1>
                    <form action="" name="track-form" onsubmit="return trackParcel()">
                        <div class="input-data">
                            <input type="text" name="tracking-id" id="tracking-id" placeholder="Enter your Tracking Id" required>       
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
    </main> 

    <jsp:include page="../../adminfooter.html"></jsp:include>


    <script src="script.js"></script>
</body>
</html>