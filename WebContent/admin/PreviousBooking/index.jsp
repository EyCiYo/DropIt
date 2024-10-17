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
    <title>Previous Bookings</title>
</head>
<body>
	<%
	    session = request.getSession(false);  
	    if (session == null && session.getAttribute("username") == null) {
	        // User is already logged in, redirect to home page
	        response.sendRedirect(request.getContextPath()+"/");
	    }
	    else if (session!=null && session.getAttribute("role").equals("user")){
	    	response.sendRedirect(request.getContextPath()+"/user/Home/index.jsp");
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
                    <form action="" name="update-form" onsubmit="return getHistory()">
                        <div class="input-data">
                            <input type="email" name="email" id="email" placeholder="Enter your Email Id" required>
                        </div>
                        <div class="track-btn">
                            <button type="submit">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                                </svg>&nbsp;
                                Search History
                            </button> 
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="previous-details" id="previous-details">
            <div class="user-details">
                <h3 class="hanging-text">User Details:</h3>
                <div style="display: flex; gap:0.25rem">
                    <h4>User ID:</h4><span id="uid">User_stEr43L9d</span>
                </div>
                <div style="display: flex; gap:0.25rem">
                    <h4>Name:</h4><span id="name">John Doe</span>
                </div>
                <div style="display: flex; gap:0.25rem">
                    <h4>Email ID:</h4><span id="email">john@gmail.com</span>
                </div>
                <div style="display: flex; gap:0.25rem">
                    <h4>Mobile:</h4><span id="mobile">1234567890</span>
                </div>
            </div>
            <h2>Previous Booking Details</h2>
            <div class="table-responsive">
                <table>
                    <thead>
                        <tr>
                            <th>Booking ID</th>
                            <th>Booking Date</th>
                            <th>Receiver Name</th>
                            <th>Delivery Address</th>
                            <th>Amount</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>#SO-12387</td>
                            <td>24-02-2024</td>
                            <td>Amal Ramesh</td>
                            <td>12, Avenue Street, Grover, 234567</td>
                            <td>$2674.00</td>
                            <td><span class="badge badge-success">Delivered</span></td>     
                        </tr>
                        <tr>
                            <td>#SO-13407</td>
                            <td>21-01-2024</td>
                            <td>Akhil Raju</td>
                            <td>12, Avenue Street, Grover, 234567</td>
                            <td>$2674.00</td>
                            <td><span class="badge badge-success">In Transit</span></td>  
                        </tr>
                        <tr>
                            <td>#SO-12487</td>
                            <td>14-02-2024</td>
                            <td>Anamya B</td>
                            <td>12, Avenue Street, Grover, 234567</td>
                            <td>$2674.00</td>
                            <td><span class="badge badge-success">Returned</span></td>  
                        </tr>
                        <tr>
                            <td>#SO-19887</td>
                            <td>19-12-2023</td>
                            <td>Arjun B</td>
                            <td>12, Avenue Street, Grover, 234567</td>
                            <td>$2674.00</td>
                            <td><span class="badge badge-success">Picked Up</span></td>  
                        </tr>
                    </tbody>
                </table>
            </div> 
        </div>
        
    </main>



    <jsp:include page="../../adminfooter.html"></jsp:include>
    <script src="script.js"></script>
</body>
</html>