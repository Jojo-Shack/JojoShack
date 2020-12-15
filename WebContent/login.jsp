<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link href ="main.css" type ="text/css" rel ="stylesheet"/>
<link href="register.css" type="text/css" rel="stylesheet"/>
<script src="login.js"></script>
</head>
<body>
	<header>
		<jsp:include page="navbar.jsp"></jsp:include>
 	</header>
	<main class="registerForm">
		<form action="login" method="post">
	            <h2>Login</h2>
	            <p>
	                <label for="username" class="floatLabel" id="usernameLabel" >Username</label>
	                <input class="inputClass" id="username" name="username" type="text" onclick="addLabel()" oninput="hideLableUser()" onkeyup="check()">
	            </p>
	            <p>
	                <label for="password" class="floatLabel" id="pass1Label">Password</label>
	                <input class="inputClass" id="password" name="password" type="password" onclick="addLabel()" oninput="hideLablePass()" onkeyup="check()">
	            </p>
	            <p>
	                <input class="inputClass" type="submit" value="Login" id="submit">
	            </p>
	            <p id="message">
	            	<c:out value="${message}"/>
	            </p>
	     </form>
     </main>
</body>
</html>