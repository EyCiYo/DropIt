<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ErrorPage</title>
<style>
    body{
        width: 100vh;
        height:100dvh;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        gap:2rem;
    }
</style>
</head>
<body>
    <h1>An error has Occurred. Please return to Home Page</h1>
    <p>We apologize for the inconvenience. Please click the button below to return to the home page.</p>
    <form action="HandleError" method="post">
        <input type="submit" value="Go Home">
    </form>
</body>
</html>