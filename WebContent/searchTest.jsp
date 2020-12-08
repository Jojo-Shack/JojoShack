<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Volunteer Listings</title>
<link href ="main.css" type ="text/css" rel ="stylesheet"/>
</head>
<body>
	<header>
		<jsp:include page="navbar.jsp"></jsp:include>
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