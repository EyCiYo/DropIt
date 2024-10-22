
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/DropIt/admin/Booking/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Archivo:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    <title>Booking</title>
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
        <h1>Package Details</h1><br>
        <div class="booking-form">
            <form action="/DropIt/BookParcel" method="post" >
                <div class="sender-info">
                    <div class="hanging-title">Pickup From:</div>
                    <label for="sendername">Name: </label><input type="text" name="sendername" id="sendername" placeholder="Sender Name" required >
                    <label for="senderaddress">Address: </label>
                    <textarea name="senderaddress" id="senderaddress" cols="30" rows="3" placeholder="Source Address" required></textarea>
                    <label for="senderemail">Email: </label><input type="email" name="senderemail" id="senderemail" placeholder="Sender Email" required>
                    <label for="mobilenumber">Mobile No:</label><input type="number" name="sendermobile" id="sendermobile" placeholder="Sender Mobile No." required>
                </div>
                <div class="receiver-info">
                    <div class="hanging-title">Drop To:</div>
                    <label for="recname">Name: </label> <input type="text" name="recname" id="recname" placeholder="Receiver Name" required>
                    <label for="recaddress">Address: </label>
                    <textarea name="recaddress" id="recaddress" cols="30" rows="3" placeholder="Destination Address" required></textarea>
                    <label for="recemail">Email: </label><input type="email" name="recemail" id="recemail" placeholder="Receiver Email" required>
                    <label for="mobilenumber">Mobile No:</label><input type="number" name="recmobile" id="recmobile" placeholder="Receiver Mobile No." required>
                </div>
                
                <div class="parcel-details">
                    <div class="hanging-title">Parcel Details:</div>
                    <div class="length cflex">
                        <label for="length">Length: </label><input type="number" name="length" step="0.01" min="0" id="length" placeholder="0cm" required>
                    </div>
                    <div class="breadth cflex">
                        <label for="breadth">Breadth: </label><input type="number" name="breadth" step="0.01" min="0" id="breadth" placeholder="0cm" required>
                    </div>
                    <div class="height cflex">
                        <label for="height">Height: </label><input type="number" name="height" step="0.01" min="0" id="height" placeholder="0cm" required>
                    </div>
                    <div class="weight cflex">
                        <label for="weight">Weight: </label><input type="number" name="weight" step="0.01" min="0" id="weight" placeholder="0kg" required>
                    </div>
                    <div class="description cflex">
                        <label for="description">Content Description: </label><textarea name="description" id="description" cols="30" rows="3" placeholder="Description about the contents of the parcel"></textarea>
                    </div>
                    
                </div>
                <div class="shipping-options">
                    <div class="hanging-title">Shipping Options: </div>
                    <div class="speed" style="display: flex;flex-direction: column;gap: 10px;">
                        <label for="shippingoption">Shipping Speed: </label>
                        <div style="display: flex;justify-content: space-evenly;">
                            <div>
                                <input type="radio" name="shippingoption" id="standard" value="standard" checked>
                                <label for="standard">Standard</label>
                            </div>
                            <div>
                                <input type="radio" name="shippingoption" id="express" value="express">
                                <label for="express">Express</label>
                            </div>
                            <div>
                                <input type="radio" name="shippingoption" id="bussiness" value="bussiness">
                                <label for="bussiness">Bussiness</label>
                            </div>
                        </div>
                    </div>
                    <div class="type" style="display: flex;flex-direction: column;gap: 10px;">
                        <label for="packagingtype">Packaging Type: </label>
                        <div style="display: flex;justify-content: space-evenly;">
                            <div>
                                <input type="radio" name="packagingtype" id="standard" value="standard" checked>
                                <label for="standard">Standard</label>
                            </div>
                            <div>
                                <input type="radio" name="packagingtype" id="eco" value="eco">
                                <label for="eco">Eco-Friendly</label>
                            </div>
                            <div>
                                <input type="radio" name="packagingtype" id="custom" value="custom">
                                <label for="custom">Custom</label>
                            </div>
                            <div>
                                <input type="radio" name="packagingtype" id="Fragile" value="fragile">
                                <label for="Fragile">Fragile</label>
                            </div>
                        </div>
                    </div>
                    <label for="date">Prefered Pickup Date: </label>
                    <input type="date" name="pickup-date" id="pickup-date" required>
                    <label for="date">Prefered Dropoff Date: </label>
                    <input type="date" name="drop-date" id="drop-date" required>
                </div>
                <div class="cost-display">
                    <label for="esimatedcost"><b>Estimated Cost:</b></label>
                    <input type="number" name="costval" id="costval" style="color: black;" readonly>
                </div>
                <div class="estimate-btn">
                    <button type="button" onclick="calculateCost()">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-currency-rupee" viewBox="0 0 16 16">
                            <path d="M4 3.06h2.726c1.22 0 2.12.575 2.325 1.724H4v1.051h5.051C8.855 7.001 8 7.558 6.788 7.558H4v1.317L8.437 14h2.11L6.095 8.884h.855c2.316-.018 3.465-1.476 3.688-3.049H12V4.784h-1.345c-.08-.778-.357-1.335-.793-1.732H12V2H4z"/>
                          </svg>&nbsp;
                        Estimate Cost
                    </button> 
                </div>
                <div class="book-btn">
                    <button type="submit">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-box-seam" viewBox="0 0 16 16">
                            <path d="M8.186 1.113a.5.5 0 0 0-.372 0L1.846 3.5l2.404.961L10.404 2zm3.564 1.426L5.596 5 8 5.961 14.154 3.5zm3.25 1.7-6.5 2.6v7.922l6.5-2.6V4.24zM7.5 14.762V6.838L1 4.239v7.923zM7.443.184a1.5 1.5 0 0 1 1.114 0l7.129 2.852A.5.5 0 0 1 16 3.5v8.662a1 1 0 0 1-.629.928l-7.185 2.874a.5.5 0 0 1-.372 0L.63 13.09a1 1 0 0 1-.63-.928V3.5a.5.5 0 0 1 .314-.464z"/>
                        </svg>&nbsp;
                        Book Parcel
                    </button> 
                </div>
            </form>
        </div>

    </main>

    <jsp:include page="../../adminfooter.html"></jsp:include>

    <script src="/DropIt/admin/Booking/script.js"></script>
</body>
</html>