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