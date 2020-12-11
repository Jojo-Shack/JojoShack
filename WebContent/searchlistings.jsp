<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Matched Listings</title>
<link href ="main.css" type ="text/css" rel ="stylesheet"/>
</head>
<body>
<header>
		<jsp:include page="navbar.jsp"></jsp:include>
 </header>
 <c:choose>
 	<c:when test ="${not empty param.keyword}">
 		<H1>Search results for '${param.keyword }'</H1>
 	</c:when>
 	<c:otherwise>
 		<H1>Showing all listings</H1>
 	</c:otherwise>
 </c:choose>
	<c:forEach var="listing" items="${matchedListings}">
	<div>
		<b class="title"><c:out value="${listing.getName()}" /></b><br><br>
		<b class="subtitle">Creator:</b> <c:out value="${listing.getOwner().getUsername()}" /><br>
		<b class="subtitle">Category:</b> <c:out value="${listing.getCategory().getName()}" /><br>
		<b class="subtitle">Tags:</b> <c:forEach var="tag" items="${listing.getTags()}"><c:out value="${tag.getName()}"/> </c:forEach></td><br><br>
		<b class="subtitle">Description:</b><c:out value="${listing.getDesc()}" /><br>
		<c:if test="${user != null && user.getType() == 'VOLUNTEER'}">
			<br>
			<form class="join" action="JoinListing" method="POST">
				<button class="button" id="joinbutton" type="submit" name="joinListing" value="${listing.getId()}">Join this listing</button>
			</form>
		</c:if>
	</div>

		<br>
	</c:forEach>

</body>
</html>