<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Account</title>
</head>
<body>
	<div style="text-align: center">
		<h1>Create Account</h1>
		<form action="register" method="post">
			<label for="usertype">Account Type:</label>
			<label>
    			<input type="radio" name="usertype" value="VOLUNTEER" checked="checked"/>Volunteer
			</label>
			<label>
    			<input type="radio" name="usertype" value="ORGANIZATION"/>Organization
			</label>
			<br><br>
			<label for="email">Email:</label>
			<input name="email" size="30" />
			<br><br>
			<label for="username">Username:</label>
			<input name="username" size="30" />
			<br><br>
			<label for="password">Password:</label>
			<input type="password" name="password" size="30" />
			<br>${message}
			<br><br>
			<button type="submit">Register</button>
		</form>
	</div>
</body>
</html>