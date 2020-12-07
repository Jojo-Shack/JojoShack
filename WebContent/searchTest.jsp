<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Volunteer Listings</title>

<style>

* {
  box-sizing: border-box;
}

body {
  background-color: #eeeeee;
}

#searchbox {
  padding: 10px;
  font-size: 17px;
  border: 1px solid grey;
  float: left;
  width: 80%;
  background: #f1f1f1;
}

#searchbutton {
  width: 20%;
  padding: 10px;
  background: #2196F3;
  color: white;
  font-size: 17px;
  border: 1px solid grey;
  border-left: none;
  cursor: pointer;
}
p.text {
	font-size: 30px;
	text-align: center;
}
.search {
	padding: 71px 0;
	margin:auto;
	max-width:500px;
	text-align: center;
}

#footer {
	position: absolute;
	bottom: 0;
	width: 100%;
	height: 2.5rem; 
}

ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #333;
 
}
li {
	display: inline;
	float: left;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}
a {
	color:blue;
}

#create {
	float: right;
}

#account {
	float: right;
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}
	
</style>
</head>
<body>
	<header>
		<ul>
		  <li><a href="/JojoShack/AllListings">View Listings</a></li>
		  <li><a href="searchTest.jsp">Search Listings</a></li>
		  <li><a href="create.jsp">Create a Listing</a></li>
		  
		  
		  <c:set var="userVal" scope="session" value='${request.getSession().getAttribute("user")}'/>
		  
		  <c:choose>
		  	<c:when test="${user == null}">
		  		<li id="create"><a href="register.jsp">Create Account</a></li>
		  		<li id="create"><a href="login.jsp">Sign In</a></li>
		  	</c:when>
		  	<c:otherwise>
		  		<li id="create"><a href="${pageContext.request.contextPath}/logout">Log out</a></li>
		  		<li id="account">Welcome ${user.username}</li>
		  	</c:otherwise>
		  </c:choose>
		</ul>
 	</header>
	<p class="text"> Enter keyword here to search all available volunteer listings </p>
	<form class="search" action="SearchListing" method="POST">
		<input type="text" id="searchbox" name="keyword" placeholder="Search...">
		<button id="searchbutton" type="submit">Submit</button>
	</form>


	<footer id="footer">
		<a href="aboutus.html"> About Us</a><br>
		<a href="mailto:jojoshackteam@gmail.com">Send us an Email</a>
	</footer>
</body>
</html>