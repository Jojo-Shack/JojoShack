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
	<div>
		<b class="title"><c:out value="${listing.getName()}" /></b><br><br>
		<b class="subtitle">Creator:</b> <c:out value="${listing.getOwner().getUsername()}" /><br>
		<b class="subtitle">Category:</b> <c:out value="${listing.getCategory().getName()}" /><br>
		<b class="subtitle">Tags:</b> <c:forEach var="tag" items="${listing.getTags()}"><c:out value="${tag.getName()}"/> </c:forEach><br><br>
		<b class="subtitle">Description:</b><c:out value="${listing.getDesc()}" /><br>
		<c:if test="${user != null && listing.getOwner().getId() == user.getId() }">
			<br>
			<form class="delete" action="DeleteListing" method="POST">
				<button class="button" id="deletebutton" type="submit" name="deleteListing" value="${listing.getId()}">Delete this listing</button>
			</form>
		</c:if>
	</div>

		<br>
	</c:forEach>
</body>
</html>