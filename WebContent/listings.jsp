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
 	
 	<br>
 	
 	<c:set var="listingVal" scope="request" value='${request.getAttribute("listListing")}'/>
 	
	<h1>Volunteer Listings</h1>
	
	<c:forEach var="listing" items="${listListing}">
		<table border="1">
			<caption><h2><c:out value="${listing.getName()}" /></h2></caption>
			<tr>
				<th>Creator</th>
				<th>Category</th>
				<th>Description</th>
				<th>Tags</th>
					<c:if test="${user != null && user.getType() == 'VOLUNTEER'}">
						<th>Volunteer!</th>
					</c:if>
			</tr>
			<tr>
				<td><c:out value="${listing.getOwner().getUsername()}" /></td>
				<td><c:out value="${listing.getCategory().getName()}" /></td>
				<td><c:out value="${listing.getDesc()}" /></td>
				<td><c:forEach var="tag" items="${listing.getTags()}"><c:out value="${tag.getName()}"/> | </c:forEach></td>
					<c:if test="${user != null && user.getType() == 'VOLUNTEER'}">
						<td>
						<form class="join" action="JoinListing" method="POST">
							<button id="joinbutton" type="submit" name="joinListing" value="${listing.getId()}">Join</button>
						</form>
						</td>
					</c:if>
			</tr>
		</table>
		<br>
	</c:forEach>
	
	
	


	<footer id="footer">
		<a href="aboutus.html"> About Us</a><br>
		<a href="mailto:jojoshackteam@gmail.com">Send us an Email</a>
	</footer>
</body>
</html>