<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Parcel Delivery - User Registration</title>
    <link rel="stylesheet" href="styles.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Archivo:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
</head>
<body>
	<%
	    session = request.getSession(false);  
	    if (session == null && session.getAttribute("username") == null) {
	        // User is already logged in, redirect to home page
	        response.sendRedirect(request.getContextPath()+"/");
	    }
	%>
   	<jsp:include page="../../homeheader.html"></jsp:include>
	<main>
        <div class="register">
            <div class="tracking-slogan">
                <h1>Register with us</h1>
                <h2>Unlock all features</h2>
            </div>
            <div class="container">
                <h2>User Registration</h2>
                <form name="registrationForm" action="../../UserReg" method="post" onSubmit="return validateForm(event)">
                    <!-- Full Name Field -->
                    <div class="form-group">
                        <label for="full_name">Full Name:</label>
                        <input type="text" id="full_name" name="full_name" required>
                    </div>
        
                    <!-- Hidden Username Field (Randomly Generated) -->
                    <div class="form-group">
                        <label for="generated_username">Generated Username:</label>
                        <input type="text" id="generated_username" name="username" readonly>
                    </div>
        
                    <!-- Email for Verification -->
                    <div class="form-group">
                        <label for="email">Email (For Verification):</label>
                        <input type="email" id="email" name="email" required>
                    </div>
        
                    <!-- Password Fields -->
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password" required>
                    </div>
        
                    <div class="form-group">
                        <label for="confirm_password">Confirm Password:</label>
                        <input type="password" id="confirm_password" name="confirm_password" required>
                    </div>
        
                    <!-- Delivery Address Field -->
                    <div class="form-group">
                        <label for="address">User Address:</label>
                        <textarea id="address" name="address" rows="3" required></textarea>
                    </div>
        
                    <!-- Phone Number Field -->
                    <div class="form-group">
                        <label for="phone">Phone Number:</label>
                        <div class="mobile-no">
                            <select name="code" id="code" required>
                                <option value="india">+91</option>
                                <option value="ksa">+966</option>
        
                            </select>
                            <input type="number" id="phone" name="phone" required>
                        </div>
                       
                    </div>
                    <div class="preferences">
                        <div style="display: flex;align-items: center;gap: 1rem;" class="text-overflow">
                            <input type="checkbox" name="preferences" id="notifications" class="prefchkbox" value="email">
                            <label for="notifications">Email Notifications</label>
                        </div>
                        <div style="display: flex;align-items: center;gap: 1rem;" class="text-overflow">
                            <input type="checkbox" name="preferences" id="whatsapp" class="prefchkbox" value="sms">
                            <label for="whatsapp">SMS alerts</label>
                        </div>
                    </div>
                    <!-- Preferred Delivery Type Dropdown -->
                    <!-- <div class="form-group">
                        <label for="delivery_type">Preferred Delivery Type:</label>
                        <select id="delivery_type" name="delivery_type" required>
                            <option value="standard">Standard</option>
                            <option value="express">Express</option>
                            <option value="same_day">Same Day</option>
                        </select>
                    </div> -->
        
                    <!-- Submit Button -->
                    <div class="btn-layout">
                        <button type="submit">Register</button>
                        <button type="button" onclick="resetForm()">Reset</button>
                    </div>
                    
                </form>
            </div>
        </div>
        
    </main>
    <jsp:include page="../../footer.html"></jsp:include>
    <script src="script.js"></script>
</body>
</html>