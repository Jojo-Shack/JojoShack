<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

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