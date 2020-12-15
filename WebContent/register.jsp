<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Account</title>
<link href ="main.css" type ="text/css" rel ="stylesheet"/>
<link href="register.css" type="text/css" rel="stylesheet"/>
<script src="register.js"></script>
</head>
<body>
	<header>
		<jsp:include page="navbar.jsp"></jsp:include>
 	</header>
 	<main class="registerForm">
		<form action="register" method="post">
	            <h2>Sign Up</h2>
	            <p>
	                <h3>Account Type</h3>
	                <input type="radio" id="radio1" name="usertype" value="VOLUNTEER" checked/>
	                <label class="rl" for="radio1">Volunteer</label>
	
	                <input type="radio" id="radio2" name="usertype" value="ORGANIZATION" />
	                <label class="rl" for="radio2">Organization</label>
	            </p>
	            <p>
	                <label for="email" class="floatLabel" id="emailLabel" >Email</label>
	                <input class="inputClass" id="Email" name="email" type="text" onclick="addLabel()" oninput="hideLabelEmail()">
	            </p>
	            <p>
	                <label for="username" class="floatLabel" id="usernameLabel" >Username</label>
	                <input class="inputClass" id="username" name="username" type="text" onclick="addLabel()" oninput="hideLableUser()">
	            </p>
	            <p>
	                <label for="password" class="floatLabel" id="pass1Label">Password</label>
	                <input class="inputClass" id="password" name="password" type="password" onclick="addLabel()" oninput="hideLablePass()">
	            </p>
	            <p>
	                <label for="confirm_password" class="floatLabel" id="pass2Label">Confirm Password</label>
	                <input class="inputClass" id="confirm_password" name="confirm_password" type="password" onclick="addLabel()" onkeyup="arePasswordsMatching()">
	                <span id="passwordMatchSpan">Your passwords do not match</span>
	            </p>
	            <p>
	                <input class="inputClass" type="submit" value="Create My Account" id="submit">
	            </p>
	            <p id="message">
	            	<c:out value="${message}"/>
	            </p>
	        </form>
        </main>
</body>
</html>