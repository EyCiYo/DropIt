<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="bean.User" %>
    <%@page import="dao.UserDao" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Archivo:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    <title>User Login</title>
    <link rel="stylesheet" href="/DropIt/user/Login/style.css">
</head>
<body>
	<%
	    session = request.getSession(false);  
		if(session != null){
			if (session.getAttribute("username") != null && session.getAttribute("role").equals("user")) {
		        // User is already logged in, redirect to home page
		        response.sendRedirect(request.getContextPath()+"/user/Home/index.jsp");
		    }
		    else if (session.getAttribute("username") != null && session.getAttribute("role").equals("admin")){
		    	response.sendRedirect(request.getContextPath()+"/admin/Home/index.jsp");
		    }
		}
	    
	%>
	<style>
		.register{
		    background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),url('${pageContext.request.contextPath}/resources/laptop.jpg');
		    background-size: cover; 
		    background-position: right 300px center; 
		    background-repeat: no-repeat;
		    border-bottom-left-radius: 8px;
		    border-top-left-radius: 8px;
		    display: flex;
		    justify-content: space-between;
		    align-items: center;
		    padding-left: 5rem;
		    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
		    min-height: 500px;
		}
	</style>
   	<jsp:include page="/homeheader.html"></jsp:include>
    <main>
        <div class="register">
            <div class="tracking-slogan">
                <h1>Welcome to DropIt</h1>
                <h2>Log-in to continue</h2>
            </div>
            <div class="container">
                <h2>User Login</h2>
                <form id="registrationForm" action="${pageContext.request.contextPath}/UserLogin" method="post">

                    <div class="form-group">
                        <label for="mobile">Email ID:</label>
                        <input type="email" id="email" name="email" required>
                    </div>
        
                    <!-- Password Fields -->
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password" required>
                    </div>
                    <div class="btn-layout">
                        <button type="submit">Log In</button>
                        <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/user/Registration/index.jsp'">Sign Up</button>
                        
                    </div>
                </form>
                <%
                    	String err = (String)request.getAttribute("errorMessage");
                    	if(err!=null){%>
                    	<script>alert("<%=err %>")</script><%} 
                %>
                
            </div>
        </div> 

    </main>
    

    <jsp:include page="/footer.html"></jsp:include>
    <script src="/DropIt/user/Login/script.js"></script>
</body>
</html>