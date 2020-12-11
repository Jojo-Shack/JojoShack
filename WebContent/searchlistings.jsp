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

	<c:forEach var="listing" items="${matchedListings}">
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

</body>
</html>