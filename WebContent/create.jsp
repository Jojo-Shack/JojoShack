<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Volunteer Listings</title>
<link href ="main.css" type ="text/css" rel ="stylesheet"/>
<style>
	.smalltext{
		font-size: 14px;
	}
</style>
</head>
<body>
<header>
		<jsp:include page="navbar.jsp"></jsp:include>
		
 </header>
 	
 	<br>
	
	<form action="CreateListing" method="POST">
	  <label for="title">Title of listing:</label>
	  <input type="text" id="title" name="title" required><br><br>
	  
	  <label for="category">Category:</label>
	  <select name="category" id="category">
	  	<option value="indoor">Indoor</option>
	  	<option value="outdoor">Outdoor</option>
	  </select><br><br>
	  
	  <label for="desc">Description:</label><br>
	  <textarea rows = "5" cols = "60" name = "desc" id="desc"></textarea><br><br>
	  
	  
	  
	  <label for="tags">Tags:</label>
	  <input type="text" id="tags" name="tags">
	  <p class="smalltext">Please separate tags with a comma.</p><br><br>
	  
	  <input type="submit" value="Submit">
	</form>


	<footer id="footer">
		<a href="aboutus.html"> About Us</a><br>
		<a href="mailto:jojoshackteam@gmail.com">Send us an Email</a>
	</footer>
</body>
</html>